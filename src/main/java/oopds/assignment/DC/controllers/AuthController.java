package oopds.assignment.DC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oopds.assignment.DC.models.Donor;
import oopds.assignment.DC.services.DonorService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final DonorService donorService;

    @Autowired
    public AuthController(DonorService donorService) {
        this.donorService = donorService;
    }

    @PostMapping("/donor/signup")
    public void addNewDonor(@RequestBody Donor donor) {
        Donor donorExists = donorService.findByEmail(donor.getEmail());

        if (donorExists == null) {
            donorService.addNewDonor(donor);
        } else {
            throw new IllegalStateException("Email already taken");
        }

    }

}
