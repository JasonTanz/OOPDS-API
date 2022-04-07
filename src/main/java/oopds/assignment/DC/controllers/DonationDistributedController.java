package oopds.assignment.DC.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
