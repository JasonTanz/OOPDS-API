package oopds.assignment.DC.DAOs;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import oopds.assignment.DC.models.DonationMade;

/**
 * A Data Access Object (DAO) to be used by the Service Classes to access
 * Entities stored in the Database.
 * This DAO is used to access Donation Requested Entities in the Database.
 */
@Repository
public interface DonationMadeDAO extends JpaRepository<DonationMade, UUID> {

    public List<DonationMade> findByQuantity(int quantity);

    public List<DonationMade> findByRemaining(int remaining);

    @Query(value = "SELECT d FROM donation_made d WHERE d.REMAINING > 0", nativeQuery = true)
    public List<DonationMade> findAllRemaining();

}
