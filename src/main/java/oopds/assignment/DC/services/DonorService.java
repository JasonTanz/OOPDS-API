package oopds.assignment.DC.services;

import java.util.List;
import java.util.UUID;
import oopds.assignment.DC.DAOs.DonorDAO;
import oopds.assignment.DC.models.Donor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * A Service Class to be used by the Spring API to do certain operations based
 * on the method called.
 * This service class contains the operations that involves the Donor.
 */
@Service
public class DonorService {
	private final DonorDAO donorDAO;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * The constructor for DonorService class based on the parameters given
	 *
	 * @param donorDAO The Data Access Object for the Donor class
	 * @param bCryptPasswordEncoder The password encoder to encrypt passwords.
	 */
	@Autowired
	public DonorService(DonorDAO donorDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.donorDAO = donorDAO;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	/**
	 * Adds a new donor and save it into the database
	 *
	 * @param donor the Donor to be added.
	 * @return the new donor that is saved.
	 */
	public Donor addNewDonor(Donor donor) {
		donor.setPassword(bCryptPasswordEncoder.encode(donor.getPassword()));
		return donorDAO.save(donor);
	}

	/**
	 * Gets and Return all Donors available in the Database.
	 *
	 * @return a List Object storing all Donors available in the database.
	 */
	public List<Donor> findAll() {
		return donorDAO.findAll();
	}

	/**
	 * Gets and Returns the Donor based on id of the donor.
	 *
	 * @param id The id to be searched for.
	 * @return An Donor object based on ID in database.
	 */
	public Donor findById(UUID id) {
		return donorDAO.findById(id).get();
	}

	/**
	 * Gets and Returns the Donor based on email of the donor.
	 *
	 * @param email The email to be searched for
	 * @return an Donor object based on email in database.
	 */
	public Donor findByEmail(String email) {
		return donorDAO.findByEmail(email);
	}

	/**
	 * Gets and Returns the Donor based on name of the donor.
	 *
	 * @param name The name of the donor to be searched for
	 * @return an Donor object based on name in database.
	 */
	public Donor findByName(String name) {
		return donorDAO.findByName(name);
	}
}
