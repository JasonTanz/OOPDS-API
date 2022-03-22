package oopds.assignment.DC.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A Controller is a Class that controls the operations of the web service by creating a REST API.
 * This Controller is responsible for Controlling operations for Donor Entities.
 */
@RestController
@RequestMapping("/dc")
public class DonorController {

    DonorService donorService;
	DonorDAO donorDAO;

	/**
	 * Gets and Sends all Donors available in the database as a resource to the web.
	 * If there's no Donors in the database (null), then it will return a HTTP error code.
	 * 
	 * @return a ResponseEntity Object, which contains a List of Donors and the appropriate HTTP Response Code or only a HTTP Response Code to the web.
	 * @throws Exception Any exceptions in operation will return a HTTP error code.
	 */
	@GetMapping("/donor")
	public ResponseEntity< DataResponse<List<Donor>> > getAllDonors() {
		try {
			List<Donor> list = donorService.getDonors();
			if (list == null)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			DataResponse<List<Donor>> dataResponse = new DataResponse<>(list, "Operation Completed");
			return new ResponseEntity<>(dataResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets and Sends the Donors in the database based on ID as a resource to the web.
	 * If there's no Donors with the specified ID, then it will return a HTTP error code.
	 * 
	 * @return a ResponseEntity Object, which contains the Donor and the appropriate HTTP Response Code or only a HTTP Response Code to the web.
	 * @throws Exception Any exceptions in operation will return a HTTP error code.
	 */
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

	/**
	 * Gets and Sends all Donors based on their Email in the database as a resource to the web.
	 * If there's no Donors matching the email specified in the database (null), then it will return a HTTP error code.
	 * 
	 * @return a ResponseEntity Object, which contains a List of Donors and the appropriate HTTP Response Code or only a HTTP Response Code to the web.
	 * @throws Exception Any exceptions in operation will return a HTTP error code.
	 */
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
	
	/**
	 * Gets and Sends all Donors based on their Name in the database as a resource to the web.
	 * If there's no Donors matching the name specified in the database (null), then it will return a HTTP error code.
	 * 
	 * @return a ResponseEntity Object, which contains a List of Donors and the appropriate HTTP Response Code or only a HTTP Response Code to the web.
	 * @throws Exception Any exceptions in operation will return a HTTP error code.
	 */
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
