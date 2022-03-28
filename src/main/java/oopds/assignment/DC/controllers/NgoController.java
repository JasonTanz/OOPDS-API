package oopds.assignment.DC.controllers;

import java.util.List;
import java.util.UUID;
import oopds.assignment.DC.models.DataResponse;
import oopds.assignment.DC.models.Ngo;
import oopds.assignment.DC.services.DonationRequestedService;
import oopds.assignment.DC.services.NgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A Controller is a Class that controls the operations of the web service by
 * creating a REST API.
 * This Controller is responsible for Controlling operations for Ngo Entities.
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class NgoController {
	@Autowired
	NgoService ngoService;

	DonationRequestedService donationRequestedService;

	/**
	 * This is a constructor for the NgoController controller with the specified parameters.
	 *
	 * @param ngoService The service class for the Ngo.
	 * @param donationRequestedService The service class for the Donation Requested.S
	 */
	@Autowired
	public NgoController(
		NgoService ngoService,
		DonationRequestedService donationRequestedService
	) {
		this.ngoService = ngoService;
		this.donationRequestedService = donationRequestedService;
	}

	/**
	 * Gets and Sends all Ngos available in the database as a resource to the web.
	 * If there's no Ngos in the database (null), then it will return a HTTP error
	 * code.
	 *
	 * @return a ResponseEntity Object, which contains a List of Ngos and the
	 *         appropriate HTTP Response Code or only a HTTP Response Code to the
	 *         web.
	 */
	@GetMapping("/ngo")
	public ResponseEntity<DataResponse<List<Ngo>>> getAllNgos() {
		try {
			List<Ngo> ngos = ngoService.findAll();
			if (ngos == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			DataResponse<List<Ngo>> dataResponse = new DataResponse<>(
				ngos,
				"Operation Completed"
			);
			return new ResponseEntity<>(dataResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets and Sends the Ngos in the database based on ID as a resource to the web.
	 * If there's no Ngos with the specified ID, then it will return a HTTP error
	 * code.
	 *
	 * @param id The id to be searched for.
	 * @return a ResponseEntity Object, which contains the Ngo and the appropriate
	 *         HTTP Response Code or only a HTTP Response Code to the web.
	 */
	@GetMapping("/ngo/by-id/{id}")
	public ResponseEntity<DataResponse<Ngo>> getNgoById(@PathVariable("id") UUID id) {
		try {
			Ngo ngo = ngoService.findById(id);

			if (ngo != null) {
				DataResponse<Ngo> dataResponse = new DataResponse<>(ngo, "Operation Completed");
				return new ResponseEntity<>(dataResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets and Sends all Ngos based on their Email in the database as a resource to
	 * the web.
	 * If there's no Ngos matching the email specified in the database (null), then
	 * it will return a HTTP error code.
	 *
	 * @param email The email to be searched for.
	 * @return a ResponseEntity Object, which contains a List of Ngos and the
	 *         appropriate HTTP Response Code or only a HTTP Response Code to the
	 *         web.
	 */
	@GetMapping("/ngo/by-email/{email}")
	public ResponseEntity<DataResponse<Ngo>> getNgoByEmail(
		@PathVariable("email") String email
	) {
		try {
			Ngo ngo = ngoService.findByEmail(email);
			if (ngo == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			DataResponse<Ngo> dataResponse = new DataResponse<>(ngo, "Operation Completed");
			return new ResponseEntity<>(dataResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets and Sends all Ngos based on their Name in the database as a resource to
	 * the web.
	 * If there's no Ngos matching the name specified in the database (null), then
	 * it will return a HTTP error code.
	 *
	 * @param name The name to be searched for.
	 * @return a ResponseEntity Object, which contains a List of Ngos and the
	 *         appropriate HTTP Response Code or only a HTTP Response Code to the
	 *         web.
	 */
	@GetMapping("/ngo/by-name/{name}")
	public ResponseEntity<DataResponse<Ngo>> findNgoByName(
		@PathVariable("name") String name
	) {
		try {
			Ngo ngo = ngoService.findByName(name);
			if (ngo == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			DataResponse<Ngo> dataResponse = new DataResponse<>(ngo, "Operation Completed");
			return new ResponseEntity<>(dataResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
