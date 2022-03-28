package oopds.assignment.DC.controllers;

import com.auth0.jwt.algorithms.Algorithm;
import java.util.List;
import java.util.UUID;
import oopds.assignment.DC.models.DataResponse;
import oopds.assignment.DC.models.Donor;
import oopds.assignment.DC.services.DonationMadeService;
import oopds.assignment.DC.services.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	/**
	 * Constructor for the donor controller based on the perimeter passed.
	 *
	 * @param donorService The service class for the Donor.
	 * @param bCryptPasswordEncoder The password encoder to encrypt passwords.
	 * @param donationMadeService The service class for the Donation Made.
	 */
	@Autowired
	public DonorController(
		DonorService donorService,
		BCryptPasswordEncoder bCryptPasswordEncoder,
		DonationMadeService donationMadeService
	) {
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
	 */
	@GetMapping("/donor")
	public ResponseEntity<DataResponse<List<Donor>>> getAllDonors() {
		try {
			List<Donor> list = donorService.findAll();
			if (list == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			DataResponse<List<Donor>> dataResponse = new DataResponse<>(
				list,
				"Operation Completed"
			);
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
	 */
	@GetMapping("/donor/by-id/{id}")
	// @PreAuthorize("hasRole('ROLE_WRITE')")
	public ResponseEntity<DataResponse<Donor>> getDonorById(@PathVariable("id") UUID id) {
		try {
			Donor donor = donorService.findById(id);
			if (donor != null) {
				DataResponse<Donor> dataResponse = new DataResponse<>(
					donor,
					"Operation Completed"
				);
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
	 */
	@GetMapping("/donor/by-email/{email}")
	public ResponseEntity<DataResponse<Donor>> getDonorsByEmail(
		@PathVariable("email") String email
	) {
		try {
			Donor donor = donorService.findByEmail(email);
			if (donor == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

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
	 */
	@GetMapping("/donor/by-name/{name}")
	public ResponseEntity<DataResponse<Donor>> getDonorsByName(
		@PathVariable("name") String name
	) {
		try {
			Donor donor = donorService.findByName(name);
			if (donor == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT); else {
				DataResponse<Donor> dataResponse = new DataResponse<>(
					donor,
					"Operation Completed"
				);
				return new ResponseEntity<>(dataResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
