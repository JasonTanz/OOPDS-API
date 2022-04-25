package oopds.assignment.DC.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
import oopds.assignment.DC.models.DonationMade;
import oopds.assignment.DC.models.DonationRequested;
import oopds.assignment.DC.models.Ngo;
import oopds.assignment.DC.models.NgoQueue;
import oopds.assignment.DC.services.DonationDistributedService;
import oopds.assignment.DC.services.DonationMadeService;
import oopds.assignment.DC.services.DonationRequestedService;
import oopds.assignment.DC.services.NgoQueueService;
import oopds.assignment.DC.services.NgoService;

/**
 * A Controller is a Class that controls the operations of the web service by
 * creating a REST API.
 * This Controller is responsible for Controlling operations for NgoQueue
 * objects.
 * Operations regarding changing the queue, sorting the priority queue and etc.
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class NgoQueueController {
    private final NgoService ngoService;
    private final NgoQueueService ngoQueueService;
    private final NgoManPowerComparator ngoManPowerComparator;
    private final DonationDistributedService donationDistributedService;
    private final DonationMadeService donationMadeService;
    private final DonationRequestedService donationRequestedService;

    /**
     * This is a constructor for the NgoQueueController controller with the
     * specified parameters.
     *
     * @param ngoService                 The service class for the Ngo.
     * @param ngoManPowerComparator      The comparator for comparing two different
     *                                   Ngos' manpower.
     * @param ngoQueueService            The service class for the NgoQueue.
     * @param donationDistributedService The service class for DonationDistributed.
     * @param donationMadeService        The service class for DonationMade.
     * @param donationRequestedService   The service class for DonationRequested.
     */
    @Autowired
    public NgoQueueController(NgoService ngoService, NgoManPowerComparator ngoManPowerComparator,
            NgoQueueService ngoQueueService, DonationDistributedService donationDistributedService,
            DonationMadeService donationMadeService, DonationRequestedService donationRequestedService) {
        this.ngoService = ngoService;
        this.ngoManPowerComparator = ngoManPowerComparator;
        this.ngoQueueService = ngoQueueService;
        this.donationDistributedService = donationDistributedService;
        this.donationRequestedService = donationRequestedService;
        this.donationMadeService = donationMadeService;
    }

    /**
     * This is a HTTP Get method to get the information of the NgoQueue from the
     * database.
     * 
     * @return A Response Entity object storing the NgoQueue, or an error message if
     *         it fails.
     */
    @GetMapping("/NgoQueue")
    public ResponseEntity<?> getNgoQueue() {
        try {
            // To get the queue ( There's only one, so I did
            // ngoQueueService.findAll().get(0) instead )

            List<NgoQueue> queue = ngoQueueService.findAll();
            if (queue.isEmpty()) {
                DataResponse<List<NgoQueue>> dataResponse = new DataResponse<>(new LinkedList<>(),
                        "Error: Queue is empty/not found");
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


    /**
     * This is a POST Request to create or sort an existing queue and save it into
     * the database.
     * 
     * @param request The request passed into the method containing the queue
     *                information.
     * 
     * @return A Response Entity object containing the new queue information or an
     *         error message if failed.
     */
    @PostMapping("/NgoQueue/queue")
    public ResponseEntity<DataResponse<NgoQueue>> sortAndSaveQueue(@RequestBody Map<String, Object> request) {
        try {

            List<String> idList = (List<String>) request.get("queue");

            NgoQueue newNgoQueue = null;

            if (ngoQueueService.findAll().isEmpty()) {
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
                        "Sorted and saved priority ngo queue successful");

                return new ResponseEntity<DataResponse<NgoQueue>>(dataResponse, HttpStatus.OK);

            } else if (request.get("type").toString().equalsIgnoreCase("fifo")) {
                Queue<Ngo> ngoQueue = new LinkedList<Ngo>();
                for (String id : idList) {
                    ngoQueue.add(ngoService.findById(UUID.fromString(id)));
                }
                System.out.println(ngoQueue.toString());
                // NgoQueue newNgoQueue = new NgoQueue((List) ngoQueue, "fifo");
                newNgoQueue.setNgoList((List<Ngo>) ngoQueue);
                newNgoQueue.setType("fifo");
                ngoQueueService.save(newNgoQueue);
                DataResponse<NgoQueue> dataResponse = new DataResponse<>(newNgoQueue,
                        "Saved fifo ngo queue successful");

                return new ResponseEntity<DataResponse<NgoQueue>>(dataResponse, HttpStatus.OK);
            }

            DataResponse<NgoQueue> dataResponse = new DataResponse<>("Invalid Type");
            return new ResponseEntity<DataResponse<NgoQueue>>(dataResponse, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            DataResponse<NgoQueue> dataResponse = new DataResponse<>(e.getMessage());
            return new ResponseEntity<DataResponse<NgoQueue>>(dataResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    /**
     * This is a HTTP Patch Request to update the queue.
     * 
     * @return A Response Entity object containing the updated values or an error
     *         message if failed.
     */
    @PatchMapping("/NgoQueue/dequeue")
    public ResponseEntity<?> updateNgoQueue() {
        try {
            // Find the ngoQueue (The only one)
            List<NgoQueue> queues = ngoQueueService.findAll();

            if (queues.isEmpty()) {
                DataResponse<List<NgoQueue>> dataResponse = new DataResponse<>(new LinkedList<>(),
                        "Error: Queue is empty/not found");
                return new ResponseEntity<DataResponse<List<NgoQueue>>>(dataResponse, HttpStatus.OK);
            }

            NgoQueue ngoQueue = queues.get(0);
            // Set values to new values
            List<Ngo> ngoList = ngoQueue.getNgoList();
            if (ngoList.isEmpty()) {
                return new ResponseEntity<DataResponse<NgoQueue>>(new DataResponse<>("Error, Invalid Action"),
                        HttpStatus.BAD_REQUEST);
            }

            Map<String, Object> data = new HashMap<>();
            // List<DonationDistributed> updatedDonationDistributed = new ArrayList<>();

            Ngo removedNgo = ngoList.remove(0);
            List<DonationDistributed> donationDistributed = donationDistributedService.findByNgoId(removedNgo.getId());
            for (DonationDistributed dd : donationDistributed) {
                dd.setStatus("Collected");
                donationDistributedService.save(dd);
            }

            // Save
            ngoQueue.setNgoList(ngoList);
            ngoQueueService.save(ngoQueue);

            List<DonationDistributed> donationDistributedList = donationDistributedService.findAll();
            List<DonationMade> donationMadeList = donationMadeService.findAllRemaining();
            List<DonationRequested> donationRequestedList = donationRequestedService.findAllRemaining();
            data.put("Ngo_queue", ngoQueue);
            data.put("Donation_distributed", donationDistributedList);
            data.put("Donation_made", donationMadeList);
            data.put("Donation_requested", donationRequestedList);

            // Return success msg
            DataResponse<Map<String, Object>> dataResponse = new DataResponse<>(data,
                    "NgoQueue has been successfully updated");
            return new ResponseEntity<DataResponse<Map<String, Object>>>(dataResponse, HttpStatus.OK);

        } catch (Exception e) {
            // Catch error
            DataResponse<?> dataResponse = new DataResponse<>(e.getMessage());
            return new ResponseEntity<DataResponse<?>>(dataResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

/**
 * This is a Comparator Class for Ngo. This allows user to compare the Manpower
 * of 2 Ngos.
 */
@Service
class NgoManPowerComparator implements Comparator<Ngo> {

    /**
     * Compare and returns an integer to represent which Ngo is larger.
     *
     * @param o1 The first Ngo to compare.
     * @param o2 The second Ngo to compare.
     *
     * @return The integer representation of which Ngo is larger.
     */
    @Override
    public int compare(Ngo o1, Ngo o2) {
        return o2.getManpower() - o1.getManpower();
    }
}