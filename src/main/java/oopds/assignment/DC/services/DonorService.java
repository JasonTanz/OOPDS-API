package oopds.assignment.DC.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import oopds.assignment.DC.DAOs.DonorDAO;
import oopds.assignment.DC.models.Donor;
import oopds.assignment.DC.models.DonationMade;

/**
 * A Service Class to be used by the Spring API to do certain operations based
 * on the method called.
 * This service class contains the operations that involves the Donor.
 */
@Service
public class DonorService {
    @Autowired
    private final DonorDAO donorDAO;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DonorService(DonorDAO donorDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.donorDAO = donorDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Donor findByEmail(String email) {
        return donorDAO.findByEmail(email);
    }

    public Donor addNewDonor(Donor donor) {

        donor.setPassword(bCryptPasswordEncoder.encode(donor.getPassword()));
        return donorDAO.save(donor);
    }

    /**
     * Gets and Return all Donors available in the Database.
     * 
     * @return a List Object storing all Donors available in the database.
     */
    public List<Donor> getDonors() {
        return donorDAO.findAll();
    }

    /**
     * Gets and Returns the Donor based on id.
     * 
     * @return an Optional Object, containing the Donor based on ID in database.
     */
    public Donor getDonorsById(UUID id) {
        return donorDAO.findById(id).get();
    }

    /**
     * Gets and Returns the Donor, based on the email of the donor.
     * 
     * @return a List Object, containing all Donors based on their email.
     */
    public Donor getDonorsByEmail(String email) {
        return donorDAO.findByEmail(email);
    }

    /**
     * Gets and Returns the Donor, based on the name of the donor.
     * 
     * @return a List Object, containing all Donors based on their name.
     */
    public Donor getDonorsByName(String name) {
        return donorDAO.findByName(name);
    }

    public void addDonationMadeById(UUID id, DonationMade donationMade) {
        Donor donor = this.getDonorsById(id);
        donor.getDonationMade().add(donationMade);
        donorDAO.save(donor);
    }
}
