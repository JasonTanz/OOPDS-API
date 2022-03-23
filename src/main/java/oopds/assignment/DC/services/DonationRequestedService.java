package oopds.assignment.DC.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import oopds.assignment.DC.DAOs.DonationRequestedDAO;
import oopds.assignment.DC.models.DonationRequested;
import org.springframework.stereotype.Service;

/**
 * A Service Class to be used by the Spring API to do certain operations based
 * on the method called.
 * This service class contains the operations that involves the Donation
 * Requested Entity.
 */
@Service
public class DonationRequestedService {

    private DonationRequestedDAO donationRequestedDAO;

    /**
     * Gets and Return all Donation Requested Entity available in the Database.
     * 
     * @return a List Object storing all Donation Requested Entity available in the
     *         database.
     */
    public List<DonationRequested> getAllDonationsRequested() {
        return donationRequestedDAO.findAll();
    }

    /**
     * Gets and Returns the Donation Requested Entity, based on id.
     * 
     * @return an Optional Object, containing the Donation Requested Entity based on
     *         ID in the database.
     */
    public Optional<DonationRequested> getDonationsRequestedById(UUID id) {
        return donationRequestedDAO.findById(id);
    }

    /**
     * Gets and Returns the Donation Requested Entity, based on the total amount of
     * item requested.
     * 
     * @return a List Object, containing all Donation Requested Entity based on the
     *         total amount of item requested.
     */
    public List<DonationRequested> getDonationsRequestedByQuantity(int quantity) {
        return donationRequestedDAO.findByQuantity(quantity);
    }

    /**
     * Gets and Returns the Donation Requested Entity, based on the remaining amount
     * of item requested.
     * 
     * @return a List Object, containing all Donation Requested Entity based on the
     *         remaining amount of item requested.
     */
    public List<DonationRequested> getDonationsRequestedByRemaining(int remaining) {
        return donationRequestedDAO.findByRemaining(remaining);
    }

}
