package oopds.assignment.DC.DAOs;

import java.util.List;
import java.util.UUID;
import oopds.assignment.DC.models.DonationMade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * A Data Access Object (DAO) to be used by the Service Classes to access
 * Entities stored in the Database.
 * This DAO is used to access Donation Requested Entities in the Database.
 */
@Repository
public interface DonationMadeDAO extends JpaRepository<DonationMade, UUID> {
	/**
	 * Abstract method to allow the service to search for the Donation Made based on
	 * their Item name
	 *
	 * @param item The item to be searched for.
	 * @return A List of Donation Made items based on the name searched for
	 */
	@Query(value = "SELECT d.id, d.item, d.remaining, d.quantity, d.donor_id FROM donation_made d WHERE d.item = ?1 AND d.remaining > 0", nativeQuery = true)
	public List<DonationMade> findAllByItem(String item);

	/**
	 * Abstract method to allow the service to search for the Donation Made based on
	 * their Remaining Items
	 *
	 * @return A list of Donations Made Entity if remaining not equals to 0
	 */
	@Query(value = "SELECT * FROM donation_made d WHERE d.REMAINING > 0", nativeQuery = true)
	public List<DonationMade> findAllRemaining();

	/**
	 * Abstract method to allow the service to search for the Donation Made based on
	 * their related Donor ID
	 *
	 * @param donorId The Donor ID to be searched for
	 * @return A List of Donations Made Entity based on the Donor ID Passed
	 */
	@Query(value = "SELECT * FROM donation_made d where d.donor_id = ?1 ", nativeQuery = true)
	public List<DonationMade> findAllByDonorId(UUID donorId);
}
