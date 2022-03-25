package oopds.assignment.DC.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import oopds.assignment.DC.DAOs.NgoDAO;
import oopds.assignment.DC.models.DonationRequested;
import oopds.assignment.DC.models.Ngo;

@Service
/**
 * A Service Class to be used by the Spring API to do certain operations based
 * on the method called.
 * This service class contains the operations that involves the Ngo.
 */
public class NgoService {
    @Autowired
    private NgoDAO ngoDAO;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public NgoService(NgoDAO ngoDAO) {
        this.ngoDAO = ngoDAO;
    }

    public Ngo findByEmail(String email) {
        return ngoDAO.findByEmail(email);
    }

    public Ngo addNewNgo(Ngo ngo) {
        ngo.setPassword(bCryptPasswordEncoder.encode(ngo.getPassword()));
        return ngoDAO.save(ngo);
    }

    /**
     * Gets and Return all Ngos available in the Database.
     * 
     * @return a List Object storing all Ngos available in the database.
     */
    public List<Ngo> getNgos() {
        return ngoDAO.findAll();
    }

    /**
     * Gets and Returns the Ngo based on id.
     * 
     * @return an Optional Object, containing the Ngo based on ID in database.
     */
    public Ngo getNgoById(UUID id) {
        return ngoDAO.findById(id).get();
    }

    /**
     * Gets and Returns the Ngos, based on the email of the Ngos.
     * 
     * @return a List Object, containing all Ngos based on their email.
     */
    public Ngo getNgoByEmail(String email) {
        return ngoDAO.findByEmail(email);
    }

    /**
     * Gets and Returns the Ngos, based on the name of the Ngos.
     * 
     * @return a List Object, containing all Ngos based on their name.
     */
    public Ngo getNgoByName(String name) {
        return ngoDAO.findByName(name);
    }

    public void addDonationRequestedById(UUID id, DonationRequested donationRequested) {
        Ngo ngo = this.getNgoById(id);
        ngo.getDonationRequested().add(donationRequested);
        ngoDAO.save(ngo);
    }

}
