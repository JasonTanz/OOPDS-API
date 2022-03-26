package oopds.assignment.DC.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oopds.assignment.DC.DAOs.DonationDistributedDAO;
import oopds.assignment.DC.models.DonationDistributed;
import oopds.assignment.DC.models.DonationMade;
import oopds.assignment.DC.models.DonationRequested;
import oopds.assignment.DC.services.DonationMadeService;
import oopds.assignment.DC.services.DonationRequestedService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DcController {
    private DonationMadeService donationMadeService;
    private DonationRequestedService donationRequestedService;
    private DonationDistributedDAO donationDistributedDAO;

    @Autowired
    public DcController(DonationMadeService donationMadeService, DonationRequestedService donationRequestedService,
            DonationDistributedDAO donationDistributedDAO) {
        this.donationMadeService = donationMadeService;
        this.donationRequestedService = donationRequestedService;
        this.donationDistributedDAO = donationDistributedDAO;
    }

    @PatchMapping("/dc")
    public List<DonationMade> updateRemaining(@RequestBody Map<String, String> data) {
        DonationMade donationMade = donationMadeService.getById(UUID.fromString(data.get("donation_made_id")));
        donationMade.setRemaining(donationMade.getQuantity() - Integer.parseInt(data.get("quantity")));

        DonationRequested donationRequested = donationRequestedService
                .getById(UUID.fromString(data.get("donation_requested_id")));
        donationRequested
                .setRemaining(donationRequested.getQuantity() - Integer.parseInt(data.get("quantity")));
        donationMade.getNgoName().add(donationRequested.getNgo().getName());
        donationRequested.getDonorName().add(donationMade.getDonor().getName());

        List<DonationMade> updatedDonationMade = donationMadeService.getAll();
        donationRequestedService.addDonationRequested(donationRequested);
        donationMadeService.addDonationMade(donationMade);
        return updatedDonationMade;

    }

    @GetMapping("/dc/get")
    public List<DonationDistributed> getAll() {
        List<DonationDistributed> donationDistributed = donationDistributedDAO.findAll();
        // donationDistributed.
        return donationDistributed;
    }

}
