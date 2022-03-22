package oopds.assignment.DC.DAOs;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import oopds.assignment.DC.models.DonationRequested;

/**
 * A Data Access Object (DAO) to be used by the Service Classes to access Entities stored in the Database.
 * This DAO is used to access Donation Requested Entities in the Database.
 */
public interface DonationRequestedDAO extends JpaRepository<DonationRequested, UUID> {
    List<DonationRequested> findByQuantity(int quantity);
    List<DonationRequested> findByRemaining(int remaining);
}
