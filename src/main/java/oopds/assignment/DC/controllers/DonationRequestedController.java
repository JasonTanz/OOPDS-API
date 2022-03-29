package oopds.assignment.DC.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import oopds.assignment.DC.models.DataResponse;
import oopds.assignment.DC.models.DonationRequested;
import oopds.assignment.DC.services.DonationRequestedService;
import oopds.assignment.DC.services.NgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * This Controller is responsible for Controlling operations for Donation
 * Requested Entities.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class DonationRequestedController {
	private final DonationRequestedService donationRequestedService;
	private final NgoService ngoService;

	/**
	 * This is a constructor for the DonationRequestedController controller with the specified parameters
	 *
	 * @param donationRequestedService The service class for Donation Requested.
	 * @param ngoService The service class for Ngo.
	 */
	@Autowired
	public DonationRequestedController(
		DonationRequestedService donationRequestedService,
		NgoService ngoService
	) {
		this.donationRequestedService = donationRequestedService;
		this.ngoService = ngoService;
	}

	/**
	 * Gets and Sends all Donations Requested available in the database as a
	 * resource to the web.
	 * If there's no Donations Requested in the database (null), then it will return
	 * a HTTP error code.
	 *
	 * @return a ResponseEntity Object, which contains a List of Donations Requested
	 *         and the appropriate HTTP Response Code or only a HTTP Response Code
	 *         to the web.
	 */
	@GetMapping("/donationRequested")
	public ResponseEntity<DataResponse<List<DonationRequested>>> getAllDonationRequested() {
		try {
			List<DonationRequested> donationRequestedList = donationRequestedService.findAll();
			if (donationRequestedList == null) return new ResponseEntity<>(
				HttpStatus.NO_CONTENT
			);

			DataResponse<List<DonationRequested>> dataResponse = new DataResponse<>(
				donationRequestedList,
				"Operation Completed"
			);
			return new ResponseEntity<>(dataResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets and Sends the Donation Requested based on ID in the database as a
	 * resource to the web.
	 * If there's no Donation Requested with the specified ID, then it will return a
	 * HTTP error code.
	 *
	 * @param id The id to be searched for.
	 * @return a ResponseEntity Object, which contains the Donation Requested and
	 *         the appropriate HTTP Response Code or only a HTTP Response Code to
	 *         the web.
	 */
	@GetMapping("/donation_requested/{id}")
	public ResponseEntity<DataResponse<DonationRequested>> getDonationRequestedById(
		@PathVariable("id") UUID id
	) {
		try {
			DonationRequested donationRequested = donationRequestedService.findById(id);
			if (donationRequested == null) {
				DataResponse<DonationRequested> dataResponse = new DataResponse<>(
					donationRequested,
					"Operation completed"
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
	 * Gets and Sends the Donation Requested based on NGO related to it in the database
	 * as a resource to the web.
	 * If there's no Donation Requested with the specified ID, then it will return a
	 * HTTP error code.
	 *
	 * @param id The ngos id to be searched for.
	 * @return a ResponseEntity Object, which contains the Donation Requested List and
	 *         the appropriate HTTP Response Code or only a HTTP Response Code to
	 *         the web.
	 */
	@GetMapping("/donation-requested/by-ngoId/{id}")
	public ResponseEntity<DataResponse<List<DonationRequested>>> getDonationRequestedByNgoId(
		@PathVariable("id") UUID id
	) {
		try {
			List<DonationRequested> donationRequested = donationRequestedService.findAllByNgoId(
				id
			);
			if (donationRequested != null) {
				DataResponse<List<DonationRequested>> dataResponse = new DataResponse<>(
					donationRequested,
					"Operation completed"
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
	 * Gets and Sends the Donation Requested based on item in the database as a
	 * resource to the web.
	 * If there's no Donation Requested with the specified item(null), then it will return a
	 * HTTP error code.
	 *
	 * @param item The item to be searched for.
	 * @return a ResponseEntity Object, which contains list of Donation Requested and
	 *         the appropriate HTTP Response Code or only a HTTP Response Code to
	 *         the web.
	 */
	@GetMapping("/donation-requested/by-item/{item}")
	public List<DonationRequested> getByItem(@PathVariable("item") String item) {
		List<DonationRequested> donationMade = donationRequestedService.findAllByItem(item);
		return donationMade;
	}

	/**
	 * Adds a new Donation Requested into the database.
	 * If there's no Donation Requested with the specified ID, then it will return a
	 * HTTP error code.
	 *
	 * @param data The data to be stored into the Donation Requested
	 * @return a ResponseEntity Object, which contains list of Donation Requested and
	 *         the appropriate HTTP Response Code or only a HTTP Response Code to
	 *         the web.
	 */
	@PostMapping("request_donation")
	public DonationRequested addDonationRequested(@RequestBody Map<String, String> data) {
		DonationRequested donationRequested = new DonationRequested();
		donationRequested.setNgo(ngoService.findById(UUID.fromString(data.get("ngo_id"))));
		donationRequested.setItem(data.get("item"));
		donationRequested.setQuantity(Integer.parseInt(data.get("quantity")));
		donationRequested.setRemaining(Integer.parseInt(data.get("remaining")));
		return donationRequestedService.save(donationRequested);
	}
}
