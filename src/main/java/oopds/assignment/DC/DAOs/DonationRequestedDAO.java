package oopds.assignment.DC.DAOs;

import java.util.List;
import java.util.UUID;
import oopds.assignment.DC.models.DonationRequested;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * A Data Access Object (DAO) to be used by the Service Classes to access
 * Entities stored in the Database.
 * This DAO is used to access Donation Requested Entities in the Database.
 */
@Repository
public interface DonationRequestedDAO extends JpaRepository<DonationRequested, UUID> {
	/**
	 * Abstract method to allow the service to search for the Donation Requested based on their Item name
	 *
	 * @param item The item to be searched for.
	 * @return A List of Donation Requested items based on the name searched for
	 */
	@Query(
		value = "SELECT d.id, d.item, d.remaining, d.quantity, d.ngo_id FROM donation_requested d WHERE d.item = ?1 AND d.remaining > 0",
		nativeQuery = true
	)
	List<DonationRequested> findAllByItem(String item);

	/**
	 * Abstract method to allow the service to search for the Donation Requested based on their Remaining Items
	 *
	 * @return A list of Donations Requested Entity if remaining not equals to 0
	 */
	@Query(
		value = "SELECT * FROM donation_requested d WHERE d.remaining>0",
		nativeQuery = true
	)
	List<DonationRequested> findAllRemaining();

	/**
	 * Abstract method to allow the service to search for the Donation Requested based on their related Donor ID
	 *
	 * @param ngoId The Donor ID to be searched for
	 * @return A List of Donations Requested Entity based on the Donor ID Passed
	 */
	@Query(
		value = "SELECT * FROM donation_requested d where d.ngo_id = ?1 ",
		nativeQuery = true
	)
	public List<DonationRequested> findAllByNgoId(UUID ngoId);
}
