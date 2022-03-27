package oopds.assignment.DC.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import oopds.assignment.DC.DAOs.NgoDAO;
import oopds.assignment.DC.models.Ngo;

@Service
/**
 * A Service Class to be used by the Spring API to do certain operations based
 * on the method called.
 * This service class contains the operations that involves the Ngo.
 */
public class NgoService {

    private NgoDAO ngoDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
    * The constructor for DonorService class based on the parameters given
    *
    * @param ngoDAO The Data Access Object for the Ngo class
    * @param bCryptPasswordEncoder The password encoder to encrypt passwords.
    */
    @Autowired
    public NgoService(NgoDAO ngoDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.ngoDAO = ngoDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Adds a new donor and save it into the database
     * 
     * @param ngo the Ngo to be added.
     * @return the new ngo that is saved.
     */
    public Ngo addNewNgo(Ngo ngo) {
        ngo.setPassword(bCryptPasswordEncoder.encode(ngo.getPassword()));
        return ngoDAO.save(ngo);
    }

    /**
     * Gets and Return all Ngos available in the Database.
     * 
     * @return a List Object storing all Ngos available in the database.
     */
    public List<Ngo> findAll() {
        return ngoDAO.findAll();
    }

    /**
     * Gets and Returns the Ngo Entity, based on id.
     * 
     * @param id The Ngo Id to be searched for.
     * @return an Ngo Object, containing the Ngo Entity based on
     *         id in the database.
     */
    public Ngo findById(UUID id) {
        return ngoDAO.findById(id).get();
    }

    /**
     * Gets and Returns the Ngo Entity, based on email.
     * 
     * @param email The Ngo Email to be searched for.
     * @return an Ngo Object, containing the Ngo Entity based on
     *         email in the database.
     */
    public Ngo findByEmail(String email) {
        return ngoDAO.findByEmail(email);
    }

    /**
     * Gets and Returns the Ngo Entity, based on name.
     * 
     * @param name The Ngo Name to be searched for.
     * @return an Ngo Object, containing the Ngo Entity based on
     *         name in the database.
     */
    public Ngo findByName(String name) {
        return ngoDAO.findByName(name);
    }

    // public void addDonationRequestedById(UUID id, DonationRequested donationRequested) {
    //     Ngo ngo = this.getById(id);
    //     // System.out.println(ngo);
    //     ngo.getDonationRequested().add(donationRequested);
    //     donationRequested.setNgo(ngo);
    //     ngoDAO.save(ngo);
    // }

}
