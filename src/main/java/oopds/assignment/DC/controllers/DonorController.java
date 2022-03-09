package oopds.assignment.DC.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import oopds.assignment.DC.DAOs.DonorDAO;
import oopds.assignment.DC.models.Donor;
import oopds.assignment.DC.services.DonorService;
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

public class DonorController {

    DonorService donorService;
	DonorDAO donorDAO;

	@GetMapping("/donor")
	public ResponseEntity<List<Donor>> getAllDonors() {
		try {
			return new ResponseEntity<>(donorService.getDonors(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/donor/{id}")
	public ResponseEntity<Donor> getDonorById(@PathVariable("id") UUID id) {
		try {
			return new ResponseEntity<>(donorService.getDonorsById(id).get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
