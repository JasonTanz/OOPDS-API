package oopds.assignment.DC.controllers;

import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oopds.assignment.DC.models.DataResponse;
import oopds.assignment.DC.models.Ngo;
import oopds.assignment.DC.models.NgoQueue;
import oopds.assignment.DC.services.NgoQueueService;
import oopds.assignment.DC.services.NgoService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class NgoQueueController {
    private final NgoService ngoService;
    private final NgoQueueService ngoQueueService;
    private final NgoManPowerComparator ngoManPowerComparator;

    @Autowired
    public NgoQueueController(NgoService ngoService, NgoManPowerComparator ngoManPowerComparator,
            NgoQueueService ngoQueueService) {
        this.ngoService = ngoService;
        this.ngoManPowerComparator = ngoManPowerComparator;
        this.ngoQueueService = ngoQueueService;
    }

    @PostMapping("/NgoQueue")
    public ResponseEntity<DataResponse<NgoQueue>> sortAndSaveQueue(@RequestBody Map<String, Object> request) {
        try {

            List<String> idList = (List<String>) request.get("queue");

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

                NgoQueue newNgoQueue = new NgoQueue(ngoList, "priority");

                ngoQueueService.addNewQueue(newNgoQueue);

                DataResponse<NgoQueue> dataResponse = new DataResponse<>(newNgoQueue,
                        "Sorted and saved ngo queue successful");

                return new ResponseEntity<DataResponse<NgoQueue>>(dataResponse, HttpStatus.OK);

            } else if (request.get("type").toString().equalsIgnoreCase("fifo")) {
                Queue<Ngo> ngoQueue = new LinkedList<Ngo>();
                for (String id : idList) {
                    ngoQueue.add(ngoService.findById(UUID.fromString(id)));
                }
                System.out.println(ngoQueue.toString());
                NgoQueue newNgoQueue = new NgoQueue((List) ngoQueue, "fifo");
                ngoQueueService.addNewQueue(newNgoQueue);
                DataResponse<NgoQueue> dataResponse = new DataResponse<>(newNgoQueue,
                        "Sorted and saved ngo queue successful");

                return new ResponseEntity<DataResponse<NgoQueue>>(dataResponse, HttpStatus.OK);
            }

            DataResponse<NgoQueue> dataResponse = new DataResponse<>("Invalid Type");
            return new ResponseEntity<DataResponse<NgoQueue>>(dataResponse, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            DataResponse<NgoQueue> dataResponse = new DataResponse<>(e.getMessage());
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