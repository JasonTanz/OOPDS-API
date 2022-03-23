package oopds.assignment.DC.services;

import java.util.*;
import oopds.assignment.DC.DAOs.DonorDAO;
import oopds.assignment.DC.models.Donor;

/**
 * A Service Class to be used by the Spring API to do certain operations based on the method called.
 * This service class contains the operations that involves the Donor.
 */
public class DonorService {

    private DonorDAO donorDAO;

	/**
     * Gets and Return all Donors available in the Database.
     * 
     * @return a List Object storing all Donors available in the database.
     */
	public List<Donor> getDonors() {
		return donorDAO.findAll();
	}

	/**
     * Gets and Returns the Donor based on id.
     * 
     * @return an Optional Object, containing the Donor based on ID in database.
     */
	public Optional<Donor> getDonorsById(UUID id) {
		return donorDAO.findById(id);
	}
	
	/**
     * Gets and Returns the Donor, based on the email of the donor.
     * 
     * @return a List Object, containing all Donors based on their email.
     */
	public List<Donor> getDonorsByEmail(String email){
		return donorDAO.findByEmail(email);
	}

	/**
     * Gets and Returns the Donor, based on the name of the donor.
     * 
     * @return a List Object, containing all Donors based on their name.
     */
	public List<Donor> getDonorsByName(String name){
		return donorDAO.findByName(name);
	}

}
