package oopds.assignment.DC.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oopds.assignment.DC.DAOs.DonationDistributedDAO;
import oopds.assignment.DC.models.DonationDistributed;


/**
 * A Service Class to be used by the Spring API to do certain operations based
 * on the method called.
 * This service class contains the operations that involves the DonationDistributed.
 */
@Service
public class DonationDistributedService {
    private final DonationDistributedDAO donationDistributedDAO;


    /**
	 * Constructor for the DonationDistributedService class based on the parameters passed.
	 *
	 * @param donationDistributedDAO the Data Access Object of the DonationDistributed model.
	 */
    @Autowired
    public DonationDistributedService(DonationDistributedDAO donationDistributedDAO) {
        this.donationDistributedDAO = donationDistributedDAO;
    }


    /**
	 * Gets and Returns all of the Donation Distributed objects stored in the database.
	 *
	 * @return A list of all Donation Distributed objects in the database.
	 */
    public List<DonationDistributed> findAll() {
        return donationDistributedDAO.findAll();
    }


    /**
	 * Save and Returns the DonationDistributed Entity.
	 *
	 * @param donationDistributed The DonationDistributed object to be saved.
	 * @return The saved donationDistributed object.
	 *
	 */
    public DonationDistributed save(DonationDistributed donationDistributed) {
        return donationDistributedDAO.save(donationDistributed);
    }


    /**
	 * Gets and Returns the DonationDistributed Entity, based on id.
	 *
	 * @param id The id of the DonationDistributed to be searched for.
	 * @return An DonationDistributed Object that matches the id.
	 */
    public DonationDistributed findById(UUID id) {
        // return donationDistributedDAO.find(donationMade, donationRequested);
        return donationDistributedDAO.findById(id).get();
    }


    /**
	 * Gets and Returns the DonationDistributed Entity, based on current status.
	 *
	 * @param status The status of the DonationDistributed to be searched for.
	 * @return A List of DonationDistributed Object that matches the status.
	 */
    public List<DonationDistributed> findByStatus(String status){
        return donationDistributedDAO.findByStatus(status);
    }



    /**
	 * Gets and Returns the DonationDistributed Entity, based on the associated Donor ID.
	 *
	 * @param id The donor id associated with the DonationDistributed to be searched for.
	 * @return A List of DonationDistributed Object that matches the Donor Id related.
	 */
    public List<DonationDistributed> findByDonorId(UUID id){
        List<DonationDistributed> donationDistributed = this.findAll();
        List<DonationDistributed> donationDistributedThatMatchDonorId = new ArrayList<>();
        for ( DonationDistributed dd : donationDistributed ){
            if ( dd.getDonationMade().getDonor().getId().equals(id) ){
                donationDistributedThatMatchDonorId.add(dd);
            }
        }
        return donationDistributedThatMatchDonorId;
    }

    /**
	 * Gets and Returns the DonationDistributed Entity, based on ngo id.
	 *
	 * @param id The ngo id of the DonationDistributed to be searched for.
	 * @return A List of DonationDistributed Object that matches the ngo id.
	 */
    public List<DonationDistributed> findByNgoId(UUID id){
        List<DonationDistributed> donationDistributed = this.findAll();
        List<DonationDistributed> donationDistributedThatMatchNgoId = new ArrayList<>();
        for ( DonationDistributed dd : donationDistributed ){
            if ( dd.getDonationRequested().getNgo().getId().equals(id) ){
                donationDistributedThatMatchNgoId.add(dd);
            }
        }
        return donationDistributedThatMatchNgoId;
    }
}
