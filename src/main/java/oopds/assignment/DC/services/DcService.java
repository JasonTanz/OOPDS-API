package oopds.assignment.DC.services;

import java.util.List;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import oopds.assignment.DC.DAOs.DcDAO;
import oopds.assignment.DC.models.Dc;

/**
 * A Service Class to be used by the Spring API to do certain operations based
 * on the method called.
 * This service class contains the operations that involves the Dc
 * Entity.
 */
@Service
public class DcService {

    private DcDAO dcDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Constructor for DCService class based on the parameters given.
     * 
     * @param dcDAO the Data Access Object class of DC
     * @param bCryptPasswordEncoder The password encoder to encrypt passwords.
     */
    @Autowired
    public DcService(DcDAO dcDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.dcDAO = dcDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Gets and Returns the Dc Entity, based on id.
     * 
     * @return an Dc Object, containing the Dc Entity based on
     *         ID in the database.
     */
    public Dc findById(UUID id) {
        return dcDAO.findById(id).get();
    }

    /**
     * Gets and Returns the Dc Entity, based on email.
     * 
     * @return an Dc Object, containing the Dc Entity based on
     *         email in the database.
     */
    public Dc findByEmail(String email) {
        return dcDAO.findByEmail(email);
    }

    /**
     * Gets and Returns the Dc Entity, based on email.
     * 
     * @return an Dc Object, containing the Dc Entity based on
     *         email in the database.
     */
    public Dc findByName(String name) {
        return dcDAO.findByName(name);
    }

    /**
     * Save and Returns the Dc Entity, based on name.
     * 
     * @return an Dc Object, containing the Donation Requested Entity based on
     *         ID in the database.
     */
    public Dc save(Dc dc) {
        dc.setPassword(bCryptPasswordEncoder.encode(dc.getPassword()));
        return dcDAO.save(dc);
    }
}