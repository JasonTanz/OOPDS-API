package oopds.assignment.DC.services;

import java.util.*;
import oopds.assignment.DC.DAOs.DonorDAO;
import oopds.assignment.DC.models.Donor;

public class DonorService {

    private DonorDAO donorDAO;

	public List<Donor> getDonors() {
		return donorDAO.findAll();
	}

	public Optional<Donor> getDonorsById(UUID id) {
		return donorDAO.findById(id);
	}

	public List<Donor> getDonorsByEmail(String email){
		return donorDAO.findByEmail(email);
	}

	public List<Donor> getDonorsByName(String name){
		return donorDAO.findByName(name);
	}

}
