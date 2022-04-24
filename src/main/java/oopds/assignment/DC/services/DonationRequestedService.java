package oopds.assignment.DC.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import oopds.assignment.DC.DAOs.DonationRequestedDAO;
import oopds.assignment.DC.models.DonationRequested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A Service Class to be used by the Spring API to do certain operations based
 * on the method called.
 * This service class contains the operations that involves the Donation
 * Requested Entity.
 */
@Service
public class DonationRequestedService implements DonationService<DonationRequested> {

	private final DonationRequestedDAO donationRequestedDAO;

	/**
	 * Constructor for the DonationRequestedService class based on the parameters
	 * passed
	 *
	 * @param donationRequestedDAO the Data Access Object of the DonationRequested
	 *                             class
	 */
	@Autowired
	public DonationRequestedService(DonationRequestedDAO donationRequestedDAO) {
		this.donationRequestedDAO = donationRequestedDAO;
	}

	/**
	 * Gets and Returns the Donation Requested Entity, based on id.
	 *
	 * @param id the Id to be searched for
	 * @return a DonationRequested Object, containing the Donation Requested Entity
	 *         based on
	 *         ID in the database.
	 */
	@Override
	public DonationRequested findById(UUID id) {
		return donationRequestedDAO.findById(id).get();
	}

	/**
	 * Gets and Return all Donation Requested Entity available in the Database.
	 *
	 * @return a List Object storing all Donation Requested Entity available in the
	 *         database.
	 */
	@Override
	public List<DonationRequested> findAll() {
		return donationRequestedDAO.findAll();
	}

	/**
	 * Gets and Return Donation Requested Entity based on their Item Name in the
	 * Database.
	 *
	 * @param item the item name to be searched for
	 * @return a List Object storing Donation Requested Entity based on their Item
	 *         Name in the
	 *         database.
	 */
	@Override
	public List<DonationRequested> findAllByItem(String item) {
		return donationRequestedDAO.findAllByItem(item);
	}

	/**
	 * Gets and Returns the Donations Requested if there's any Remaining Amount of
	 * items left.
	 *
	 * @return a List of Donations Requested object based on Remaining Amount of
	 *         items left.
	 */
	@Override
	public List<DonationRequested> findAllRemaining() {
		return donationRequestedDAO.findAllRemaining();
	}

	/**
	 * Saves all the data/value into the Donations Requested Entity in the database.
	 *
	 * @param donationRequested the new donation Requested entity to replace the old
	 *                          entity.
	 * @return the saved entity.
	 */
	@Override
	public DonationRequested save(DonationRequested donationRequested) {
		return donationRequestedDAO.save(donationRequested);
	}

	/**
	 * Update the remaining items left in donation requested entity with the new
	 * amount.
	 *
	 * @param id        the id of the donation requested entity to be updated.
	 * @param remaining the new value for the remaining data attribute.
	 * @return the newly updated entity.
	 */
	@Override
	public DonationRequested updateRemainingById(UUID id, int remaining) {
		DonationRequested donationRequested = this.findById(id);
		donationRequested.setRemaining(remaining);
		return donationRequestedDAO.save(donationRequested);
	}

	/**
	 * Gets and Returns the Donations Requested associated with the Ngo Id passed.
	 *
	 * @param id The Ngo Id associated to be searched for.
	 * @return a List of Donations Requested object based on Remaining Amount of
	 *         items left.
	 */
	public List<DonationRequested> findAllByNgoId(UUID id) {
		List<DonationRequested> donationRequested = donationRequestedDAO.findAllByNgoId(id);
		return donationRequested;
	}


	/**
	 * Gets and Returns the Donations Requested associated with the Ngo Id passed and The 
	 * Donation Requested haven't been collected/reserved (Available) .
	 *
	 * @param id The Ngo Id associated to be searched for.
	 * @return a List of Donations Requested object based on the searched value passed
	 */
	public List<DonationRequested> findRemainingByNgoId(UUID id) {
		List<DonationRequested> donationRequestedList = new ArrayList<DonationRequested>();
		for (DonationRequested dr : this.findAllRemaining()) {
			if (dr.getNgo().getId().equals(id)) {
				donationRequestedList.add(dr);
			}
		}

		return donationRequestedList;
	}
}
