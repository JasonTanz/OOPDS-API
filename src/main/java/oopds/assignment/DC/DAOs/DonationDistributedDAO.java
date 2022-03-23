package oopds.assignment.DC.DAOs;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import oopds.assignment.DC.models.DonationDistributed;

/**
 * A Data Access Object (DAO) to be used by the Service Classes to access
 * Entities stored in the Database.
 * This DAO is used to access Donation Requested Entities in the Database.
 */
@Repository
public interface DonationDistributedDAO extends JpaRepository<DonationDistributed, UUID> {
    List<DonationDistributed> findByQuantity(int quantity);

    // List<DonationDistributed> findByRemaining(int remaining);
}