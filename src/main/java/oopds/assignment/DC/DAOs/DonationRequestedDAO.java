package oopds.assignment.DC.DAOs;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import oopds.assignment.DC.models.DonationRequested;

/**
 * A Data Access Object (DAO) to be used by the Service Classes to access
 * Entities stored in the Database.
 * This DAO is used to access Donation Requested Entities in the Database.
 */
@Repository
public interface DonationRequestedDAO extends JpaRepository<DonationRequested, UUID> {

    List<DonationRequested> findByQuantity(int quantity);

    List<DonationRequested> findByItem(String item);

    @Query(value = "SELECT d FROM DonationRequested d WHERE d.remaining>0", nativeQuery = true)
    List<DonationRequested> findByRemaining();
}
