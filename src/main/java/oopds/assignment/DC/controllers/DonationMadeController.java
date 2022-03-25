package oopds.assignment.DC.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import oopds.assignment.DC.models.DonationMade;
import oopds.assignment.DC.services.DonationMadeService;

@RestController
@RequestMapping("/api")
public class DonationMadeController {
    private final DonationMadeService donationMadeService;

    @Autowired
    public DonationMadeController(DonationMadeService donationMadeService) {
        this.donationMadeService = donationMadeService;
    }

    @GetMapping("/donation-made/by-donorId/{id}")
    public List<DonationMade> getByDonorId(@PathVariable("id") String id) {
        System.out.println(id);
        List<DonationMade> donationMade = donationMadeService.findByDonorId(UUID.fromString(id));
        return donationMade;
    }

}
