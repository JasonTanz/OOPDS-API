package oopds.assignment.DC.services;

import java.util.List;
import java.util.UUID;
import oopds.assignment.DC.DAOs.DonationMadeDAO;
import oopds.assignment.DC.models.DonationMade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A Service Class to be used by the Spring API to do certain operations based
 * on the method called.
 * This service class contains the operations that involves the Donation Made
 * Entity.
 */
@Service
public class DonationMadeService implements DonationService<DonationMade> {
	private final DonationMadeDAO donationMadeDAO;

	/**
	 * Constructor for the DonationMadeService class based on the parameters passed.
	 *
	 * @param donationMadeDAO the Data Access Object of the DonationMade class.
	 */
	@Autowired
	public DonationMadeService(DonationMadeDAO donationMadeDAO) {
		this.donationMadeDAO = donationMadeDAO;
	}

	/**
	 * Gets and Returns the DonationMade Entity, based on id.
	 *
	 * @param id id of the DonationMade Entity to be searched for.
	 * @return an DonationMade Object, containing the Donation Made Entity based on
	 *         ID in the database.
	 */
	@Override
	public DonationMade findById(UUID id) {
		return donationMadeDAO.findById(id).get();
	}

	/**
	 * Gets and Return all Donations Made available in the Database.
	 *
	 * @return a List Object storing all Donations Made available in the database.
	 */
	@Override
	public List<DonationMade> findAll() {
		return donationMadeDAO.findAll();
	}

	/**
	 * Gets and Returns the Donations Made based on Item Name of the Donations Made.
	 *
	 * @param item The item to be searched for.
	 * @return a List of Donations Made object based on Item Name specified in database.
	 */
	@Override
	public List<DonationMade> findAllByItem(String item) {
		return donationMadeDAO.findAllByItem(item);
	}

	/**
	 * Gets and Returns the Donations Made if there's any Remaining Amount of items left.
	 *
	 * @return a List of Donations Made object based on Remaining Amount of items left.
	 */
	@Override
	public List<DonationMade> findAllRemaining() {
		return donationMadeDAO.findAllRemaining();
	}

	/**
	 * Gets and Returns the Donations Made related to the Donor Id.
	 *
	 * @param id Id of donor to be searched for.
	 * @return a List of Donations Made based on the Donor Id specified in database.
	 */
	public List<DonationMade> findAllByDonorId(UUID id) {
		List<DonationMade> donationMade = donationMadeDAO.findAllByDonorId(id);
		return donationMade;
	}

	/**
	 * Saves all the data/value into the Donations Made Entity in the database.
	 *
	 * @param donationMade the new value to replace the old value.
	 * @return the saved entity, return null if error has been occurred.
	 */
	@Override
	public DonationMade save(DonationMade donationMade) {
		try {
			return donationMadeDAO.save(donationMade);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 *  Update the remaining items left in donation made entity with the new amount.
	 *
	 *  @param id the id of the donation made entity to be updated.
	 *  @param remaining the new value for the remaining data attribute.
	 *  @return the newly updated entity.
	 */
	@Override
	public DonationMade updateRemainingById(UUID id, int remaining) {
		DonationMade donationMade = this.findById(id);
		donationMade.setRemaining(remaining);
		return donationMadeDAO.save(donationMade);
	}
}
