package oopds.assignment.DC.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import oopds.assignment.DC.DAOs.NgoDAO;
import oopds.assignment.DC.models.Ngo;
import oopds.assignment.DC.services.NgoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dc")
public class NgoController {
	NgoService ngoService;
	NgoDAO ngoDAO;

	@GetMapping("/ngo")
	public ResponseEntity<List<Ngo>> getAllNgos() {
		try {
			return new ResponseEntity<>(ngoService.getNgos(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/ngo/{id}")
	public ResponseEntity<Ngo> getNgoById(@PathVariable("id") UUID id) {
		try {
			return new ResponseEntity<>(ngoService.getNgosById(id).get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// @GetMapping("/ngo/{email}")
	// public ResponseEntity<List<Ngo>> getNgoByEmail(@PathVariable("email") String email) {
	// 	try {
	// 		return new ResponseEntity<>(ngoService.getNgosByEmail(email), HttpStatus.OK);
	// 	} catch (Exception e) {
	// 		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// }

	@GetMapping("/ngo/{name}")
	public ResponseEntity<List<Ngo>> getNgoByName(@PathVariable("name") String name) {
		try {
			return new ResponseEntity<>(ngoService.getNgosByName(name), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
