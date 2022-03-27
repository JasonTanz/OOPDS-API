package oopds.assignment.DC.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import oopds.assignment.DC.models.DataResponse;
import oopds.assignment.DC.models.DonationMade;
import oopds.assignment.DC.models.Donor;
import oopds.assignment.DC.services.DonationMadeService;
import oopds.assignment.DC.services.DonorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A Controller is a Class that controls the operations of the web service by
 * creating a REST API.
 * This Controller is responsible for Controlling operations for Donor Entities.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin

public class DonorController {
	
	@Autowired
	DonorService donorService;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
	// private final DonationMadeService donationMadeService;

	@Autowired
	public DonorController(DonorService donorService, BCryptPasswordEncoder bCryptPasswordEncoder,
			DonationMadeService donationMadeService) {
		this.donorService = donorService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	/**
	 * Gets and Sends all Donors available in the database as a resource to the web.
	 * If there's no Donors in the database (null), then it will return a HTTP error
	 * code.
	 * 
	 * @return a ResponseEntity Object, which contains a List of Donors and the
	 *         appropriate HTTP Response Code or only a HTTP Response Code to the
	 *         web.
	 * @throws Exception Any exceptions in operation will return a HTTP error code.
	 */
	@GetMapping("/donor")
	public ResponseEntity<DataResponse<List<Donor>>> getAllDonors() {
		try {
			List<Donor> list = donorService.findAll();
			if (list == null)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			DataResponse<List<Donor>> dataResponse = new DataResponse<>(list, "Operation Completed");
			return new ResponseEntity<>(dataResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets and Sends the Donors in the database based on ID as a resource to the
	 * web.
	 * If there's no Donors with the specified ID, then it will return a HTTP error
	 * code.
	 * 
	 * @param id The id to be searched for.
	 * @return a ResponseEntity Object, which contains the Donor and the appropriate
	 *         HTTP Response Code or only a HTTP Response Code to the web.
	 * @throws Exception Any exceptions in operation will return a HTTP error code.
	 */
	@GetMapping("/donor/by-id/{id}")
	// @PreAuthorize("hasRole('ROLE_WRITE')")
	public ResponseEntity<DataResponse<Donor>> getDonorById(@PathVariable("id") UUID id) {
		try {
			Donor donor = donorService.findById(id);
			if (donor != null) {
				DataResponse<Donor> dataResponse = new DataResponse<>(donor, "Operation Completed");
				return new ResponseEntity<>(dataResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets and Sends all Donors based on their Email in the database as a resource
	 * to the web.
	 * If there's no Donors matching the email specified in the database (null),
	 * then it will return a HTTP error code.
	 * 
	 * @param email The email to be searched for.
	 * @return a ResponseEntity Object, which contains a List of Donors and the
	 *         appropriate HTTP Response Code or only a HTTP Response Code to the
	 *         web.
	 * @throws Exception Any exceptions in operation will return a HTTP error code.
	 */
	@GetMapping("/donor/by-email/{email}")
	public ResponseEntity<DataResponse<Donor>> getDonorsByEmail(@PathVariable("email") String email) {
		try {
			Donor donor = donorService.findByEmail(email);
			if (donor == null)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			DataResponse<Donor> dataResponse = new DataResponse<>(donor, "Operation Completed");
			return new ResponseEntity<>(dataResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets and Sends all Donors based on their Name in the database as a resource
	 * to the web.
	 * If there's no Donors matching the name specified in the database (null), then
	 * it will return a HTTP error code.
	 * 
	 * @param name The name to be searched for
	 * @return a ResponseEntity Object, which contains a List of Donors and the
	 *         appropriate HTTP Response Code or only a HTTP Response Code to the
	 *         web.
	 * @throws Exception Any exceptions in operation will return a HTTP error code.
	 */
	@GetMapping("/donor/by-name/{name}")
	public ResponseEntity<DataResponse<Donor>> getDonorsByName(@PathVariable("name") String name) {
		try {
			Donor donor = donorService.findByName(name);
			if (donor == null)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else{
				DataResponse<Donor> dataResponse = new DataResponse<>(donor, "Operation Completed");
				return new ResponseEntity<>(dataResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// @PostMapping("/donor/donate")
	// // @PreAuthorize("hasRole('ROLE_WRITE')")
	// public DonationMade addDonationMade(@RequestBody Map<String, String> json) {

	// 	System.out.println(json.get("donor_id"));
	// 	DonationMade donationMade = donationMadeService
	// 			.add(new DonationMade(json.get("item"), Integer.parseInt(json.get("quantity")),
	// 					Integer.parseInt(json.get("remaining"))));

	// 	// donorService.addDonationMadeById(UUID.fromString(json.get("donor_id")), donationMade);
	// 	return donationMade;
	// }

	// @GetMapping("/donor/donation_made/{id}")
	// public Donor getDonationMade(@PathVariable("id") UUID id) {
	// 	Donor donor = donorService.getById(id);
	// 	donor.setPassword("");
	// 	return donor;
	// };

}
