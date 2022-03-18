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
		List<Donor> donors = donorDAO.findByEmail(email);
		if (donors.isEmpty())
			return null;
		else
			return donors;
	}

	public List<Donor> getDonorsByName(String name){
		List<Donor> donors = donorDAO.findByName(name);
		if (donors.isEmpty())
			return null;
		else
			return donors;
	}

}
