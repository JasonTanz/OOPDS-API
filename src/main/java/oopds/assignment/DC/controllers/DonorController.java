package oopds.assignment.DC.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import oopds.assignment.DC.DAOs.DonorDAO;
import oopds.assignment.DC.models.DataResponse;
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
	public ResponseEntity< DataResponse<List<Donor>> > getAllDonors() {
		try {
			DataResponse<List<Donor>> dataResponse = new DataResponse<>(donorService.getDonors(), "Operation Completed");
			return new ResponseEntity<>(dataResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/donor/{id}")
	public ResponseEntity< DataResponse<Donor> > getDonorById(@PathVariable("id") UUID id) {
		try {
			Optional<Donor> donor = donorService.getDonorsById(id);
			if (donor.isPresent()){
				DataResponse<Donor> dataResponse = new DataResponse<>(donor.get(), "Operation Completed");
				return new ResponseEntity<>(dataResponse, HttpStatus.OK);
			}
			else{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/donor/{email}")
	public ResponseEntity< DataResponse<List<Donor>> > getDonorsByEmail(@PathVariable("email") String email) {
		try {
			List<Donor> list = donorService.getDonorsByEmail(email);
			if (list == null)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			DataResponse<List<Donor>> dataResponse = new DataResponse<>(list, "Operation Completed");
			return new ResponseEntity<>(dataResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/donor/{name}")
	public ResponseEntity< DataResponse<List<Donor>> > getDonorsByName(@PathVariable("name") String name) {
		try {
			List<Donor> list = donorService.getDonorsByName(name);
			if (list == null)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			DataResponse<List<Donor>> dataResponse = new DataResponse<>(list, "Operation Completed");
			return new ResponseEntity<>(dataResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
