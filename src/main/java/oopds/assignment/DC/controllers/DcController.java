package oopds.assignment.DC.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import oopds.assignment.DC.models.DonationDistributed;
import oopds.assignment.DC.models.DonationMade;
import oopds.assignment.DC.models.DonationRequested;
import oopds.assignment.DC.services.DonationDistributedService;
import oopds.assignment.DC.services.DonationMadeService;
import oopds.assignment.DC.services.DonationRequestedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A Controller is a Class that controls the operations of the web service by
 * creating a REST API.
 * This Controller is responsible for Controlling operations for DC
 * Operations such as transactions between donors and ngos.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class DcController {
	private final DonationMadeService donationMadeService;
	private final DonationRequestedService donationRequestedService;
	private final DonationDistributedService donationDistributedService;

	/**
	 * This is a constructor for the DcController controller with the specified
	 * parameter passed.
	 *
	 * 
	 * @param donationMadeService      The service class for Donations Made.
	 * @param donationRequestedService The service class for Donations Requested.
	 */
	@Autowired
	public DcController(
			DonationMadeService donationMadeService,
			DonationRequestedService donationRequestedService, DonationDistributedService donationDistributedService) {
		this.donationMadeService = donationMadeService;
		this.donationRequestedService = donationRequestedService;
		this.donationDistributedService = donationDistributedService;
	}

	/**
	 * This is a HTTP Patch method to update the remaining item left for donation
	 * made and donation requested.A
	 *
	 * @param data The full data representing the database.
	 * @return The new Donation Made data, representing the new value.
	 */
	@PatchMapping("/dc")
	public List<DonationDistributed> updateRemaining(@RequestBody Map<String, String> data) {
		DonationMade donationMade = donationMadeService.findById(
				UUID.fromString(data.get("donation_made_id")));
		donationMade.setRemaining(
				donationMade.getRemaining() - Integer.parseInt(data.get("quantity")));

		DonationRequested donationRequested = donationRequestedService.findById(
				UUID.fromString(data.get("donation_requested_id")));
		donationRequested.setRemaining(
				donationRequested.getRemaining() - Integer.parseInt(data.get("quantity")));

		donationRequestedService.save(donationRequested);
		donationMadeService.save(donationMade);

		DonationDistributed donationDistributed = new DonationDistributed(donationMade, donationRequested,
				Integer.parseInt(data.get("quantity")), data.get("status"));
		donationDistributedService.save(donationDistributed);

		return donationDistributedService.findAll();
	}


	// @PostMapping("/dc")
	// public DonationDistributed find(@RequestBody Map<String, String> data) {
	// 	return donationDistributedService.findById(UUID.fromString(data.get("donation_made_id")), UUID.fromString(data.get("donation_requested_id")));
	// }
}
