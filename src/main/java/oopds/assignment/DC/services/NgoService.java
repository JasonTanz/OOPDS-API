package oopds.assignment.DC.services;

import java.util.*;
import oopds.assignment.DC.DAOs.NgoDAO;
import oopds.assignment.DC.models.Ngo;

public class NgoService {
	private NgoDAO ngoDAO;

	public List<Ngo> getNgos() {
		return ngoDAO.findAll();
	}

	public Optional<Ngo> getNgosById(UUID id) {
		return ngoDAO.findById(id);
	}

	public List<Ngo> getNgosByEmail(String email){
		return ngoDAO.findByEmail(email);
	}

	public List<Ngo> getNgosByName(String name){
		return ngoDAO.findByName(name);
	}
	
}
