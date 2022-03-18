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
		List<Ngo> ngos = ngoDAO.findByEmail(email);
		if (ngos.isEmpty())
			return null;
		else
			return ngos;
	}

	public List<Ngo> getNgosByName(String name){
		List<Ngo> ngos = ngoDAO.findByName(name);
		if (ngos.isEmpty())
			return null;
		else
			return ngos;
	}
	
}
