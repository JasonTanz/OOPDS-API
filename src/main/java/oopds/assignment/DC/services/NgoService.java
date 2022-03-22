package oopds.assignment.DC.services;

import java.util.*;
import oopds.assignment.DC.DAOs.NgoDAO;
import oopds.assignment.DC.models.Ngo;

/**
 * A Service Class to be used by the Spring API to do certain operations based on the method called.
 * This service class contains the operations that involves the Ngo.
 */
public class NgoService {
	
	private NgoDAO ngoDAO;

	/**
     * Gets and Return all Ngos available in the Database.
     * 
     * @return a List Object storing all Ngos available in the database.
     */
	public List<Ngo> getNgos() {
		return ngoDAO.findAll();
	}

	/**
     * Gets and Returns the Ngo based on id. 
     * 
     * @return an Optional Object, containing the Ngo based on ID in database.
     */
	public Optional<Ngo> getNgosById(UUID id) {
		return ngoDAO.findById(id);
	}

	/**
     * Gets and Returns the Ngos, based on the email of the Ngos.
     * 
     * @return a List Object, containing all Ngos based on their email.
     */
	public List<Ngo> getNgosByEmail(String email){
		return ngoDAO.findByEmail(email);
	}

	/**
     * Gets and Returns the Ngos, based on the name of the Ngos.
     * 
     * @return a List Object, containing all Ngos based on their name.
     */
	public List<Ngo> getNgosByName(String name){
		return ngoDAO.findByName(name);
	}
	
}
