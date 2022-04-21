package oopds.assignment.DC.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oopds.assignment.DC.models.DataResponse;
import oopds.assignment.DC.models.DonationDistributed;
import oopds.assignment.DC.models.DonationMade;
import oopds.assignment.DC.services.DonationDistributedService;
import oopds.assignment.DC.services.DonationMadeService;

@RestController
@RequestMapping("/api")
public class DonationDistributedController {
    private final DonationDistributedService donationDistributedService;
    private final DonationMadeService donationMadeService;

    public DonationDistributedController(DonationDistributedService donationDistributedService,
            DonationMadeService donationMadeService) {
        this.donationDistributedService = donationDistributedService;
        this.donationMadeService = donationMadeService;
    }

    @GetMapping("/donation-distributed")
    public ResponseEntity<DataResponse<?>> getAll() {
        List<DonationDistributed> donationDistributed = donationDistributedService.findAll();
        List<DonationMade> donationMade = donationMadeService.findAllRemaining();
        Map<String, List<?>> data = new HashMap<>();
        data.put("Donation-distributed", donationDistributed);
        data.put("Donation-made", donationMade);
        DataResponse<?> dataResponse = new DataResponse<>(
                data,
                "Fetched donation distributed successful");
        return new ResponseEntity<DataResponse<?>>(dataResponse, HttpStatus.OK);
    }

    // Update Status by getting the necessary ID and Status through parameter
    @PatchMapping("/donation-distributed/{id}")
    public ResponseEntity<DataResponse<?>> updateStatus(@RequestBody @PathVariable("id") UUID id, String newStatus){
        try {
            // Get the donation distributed needed using ID
            DonationDistributed donationDistributed = donationDistributedService.findById(id);
            
            // Then set the status to whichever correct one ("Available", "Reserved", and one more i forgot)
            donationDistributed.setStatus(newStatus);
            // Update the attribute and save it into database
            donationDistributedService.save(donationDistributed);
            
            // Return success message
            // Dunno if you'll use this or not
            DataResponse<?> dataResponse = new DataResponse<>("Update donation distributed status successful");
            return new ResponseEntity<DataResponse<?>>(dataResponse, HttpStatus.OK);

        } catch (Exception e) {
            // return error message
            // Dunno if you'll use this or not
            return new ResponseEntity<DataResponse<?>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Return a list of donation distributed based on the status 
    // Don't know if you'll use this or not
    @GetMapping("/donation-distributed/{status}")
    public List<DonationDistributed> getAllByStatus(@PathVariable("status") String status){
        return donationDistributedService.findByStatus(status);
    }

    

}
