package oopds.assignment.DC.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oopds.assignment.DC.models.DataResponse;
import oopds.assignment.DC.models.DonationDistributed;
import oopds.assignment.DC.models.Ngo;
import oopds.assignment.DC.models.NgoQueue;
import oopds.assignment.DC.services.DonationDistributedService;
import oopds.assignment.DC.services.NgoQueueService;
import oopds.assignment.DC.services.NgoService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class NgoQueueController {
    private final NgoService ngoService;
    private final NgoQueueService ngoQueueService;
    private final NgoManPowerComparator ngoManPowerComparator;
    private final DonationDistributedService donationDistributedService;

    @Autowired
    public NgoQueueController(NgoService ngoService, NgoManPowerComparator ngoManPowerComparator,
            NgoQueueService ngoQueueService, DonationDistributedService donationDistributedService) {
        this.ngoService = ngoService;
        this.ngoManPowerComparator = ngoManPowerComparator;
        this.ngoQueueService = ngoQueueService;
        this.donationDistributedService = donationDistributedService;
    }

    @PostMapping("/NgoQueue/queue")
    public ResponseEntity<DataResponse<NgoQueue>> sortAndSaveQueue(@RequestBody Map<String, Object> request) {
        try {

            List<String> idList = (List<String>) request.get("queue");

            NgoQueue newNgoQueue = null;
            
            if(ngoQueueService.findAll().isEmpty()) {
                newNgoQueue = new NgoQueue();
            } else {
                newNgoQueue = ngoQueueService.findAll().get(0);
            }

            if (request.get("type").toString().equalsIgnoreCase("priority")) {
                System.out.println("inside");

                Queue<Ngo> ngoQueue = new PriorityQueue<Ngo>(ngoManPowerComparator);
                for (String id : idList) {
                    ngoQueue.add(ngoService.findById(UUID.fromString(id)));
                }

                List<Ngo> ngoList = new ArrayList<Ngo>();
                while (!ngoQueue.isEmpty()) {
                    ngoList.add(ngoQueue.remove());
                }

                newNgoQueue.setNgoList(ngoList);
                newNgoQueue.setType("priority");

                ngoQueueService.save(newNgoQueue);

                DataResponse<NgoQueue> dataResponse = new DataResponse<>(newNgoQueue,
                        "Saved fifo ngo queue successful");

                return new ResponseEntity<DataResponse<NgoQueue>>(dataResponse, HttpStatus.OK);

            } else if (request.get("type").toString().equalsIgnoreCase("fifo")) {
                Queue<Ngo> ngoQueue = new LinkedList<Ngo>();
                for (String id : idList) {
                    ngoQueue.add(ngoService.findById(UUID.fromString(id)));
                }
                System.out.println(ngoQueue.toString());
                // NgoQueue newNgoQueue = new NgoQueue((List) ngoQueue, "fifo");
                newNgoQueue.setNgoList((List)ngoQueue);
                newNgoQueue.setType("fifo");
                ngoQueueService.save(newNgoQueue);
                DataResponse<NgoQueue> dataResponse = new DataResponse<>(newNgoQueue,
                        "Sorted and saved priority ngo queue successful");

                return new ResponseEntity<DataResponse<NgoQueue>>(dataResponse, HttpStatus.OK);
            }

            DataResponse<NgoQueue> dataResponse = new DataResponse<>("Invalid Type");
            return new ResponseEntity<DataResponse<NgoQueue>>(dataResponse, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            DataResponse<NgoQueue> dataResponse = new DataResponse<>(e.getMessage());
            return new ResponseEntity<DataResponse<NgoQueue>>(dataResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get the queue from database
    @GetMapping("/NgoQueue")
    public ResponseEntity<?> getNgoQueue(){
        try {
            // To get the queue ( There's only one, so I did ngoQueueService.findAll().get(0) instead )
            
            List<NgoQueue> queue = ngoQueueService.findAll();
            if (queue.isEmpty()){
                DataResponse<List<NgoQueue>> dataResponse = new DataResponse<>(new LinkedList<>(), "Error: Queue is empty/not found");
                return new ResponseEntity<DataResponse<List<NgoQueue>>>(dataResponse, HttpStatus.OK);
            }

            NgoQueue ngoQueue = queue.get(0);

            // Return the full NgoQueue, together with the type
            DataResponse<NgoQueue> dataResponse = new DataResponse<>(ngoQueue, "Successfully get ngo queue");
            return new ResponseEntity<DataResponse<NgoQueue>>(dataResponse, HttpStatus.OK);

        } catch (Exception e) {
            DataResponse<?> dataResponse = new DataResponse<>(e.getMessage());
            return new ResponseEntity<DataResponse<?>>(dataResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update the queue and save it to database
    @PatchMapping("/NgoQueue/dequeue")
    public ResponseEntity<?> updateNgoQueue(){
        try {
            // Find the ngoQueue (The only one)
            List<NgoQueue> queues = ngoQueueService.findAll();

            if (queues.isEmpty()){
                DataResponse<List<NgoQueue>> dataResponse = new DataResponse<>(new LinkedList<>(), "Error: Queue is empty/not found");
                return new ResponseEntity<DataResponse<List<NgoQueue>>>(dataResponse, HttpStatus.OK);
            }


            NgoQueue ngoQueue = queues.get(0);
            // Set values to new values
            List<Ngo> ngoList = ngoQueue.getNgoList();
            if (ngoList.isEmpty()){
                return new ResponseEntity<DataResponse<NgoQueue>>( new DataResponse<>("Error, Invalid Action"), HttpStatus.BAD_REQUEST );
            }

            Ngo removedNgo = ngoList.remove(0);
            List<DonationDistributed> donationDistributed = donationDistributedService.findByNgo(removedNgo);
            for (DonationDistributed dd : donationDistributed){
                dd.setStatus("Collected");
                donationDistributedService.save(dd);
            }
            
            // Save
            ngoQueue.setNgoList(ngoList);
            ngoQueueService.save(ngoQueue);

            // Return success msg
            DataResponse<NgoQueue> dataResponse = new DataResponse<>(ngoQueue, "NgoQueue has been successfully updated");
            return new ResponseEntity<DataResponse<NgoQueue>>(dataResponse, HttpStatus.OK); 

        } catch (Exception e) {
            // Catch error
            DataResponse<NgoQueue>  dataResponse = new DataResponse<>(e.getMessage());
            return new ResponseEntity<DataResponse<NgoQueue>>(dataResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

@Service
class NgoManPowerComparator implements Comparator<Ngo> {
    @Override
    public int compare(Ngo o1, Ngo o2) {
        return o2.getManpower() - o1.getManpower();
    }
}