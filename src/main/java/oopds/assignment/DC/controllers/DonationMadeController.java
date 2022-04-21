package oopds.assignment.DC.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import oopds.assignment.DC.models.DonationMade;
import oopds.assignment.DC.services.DonationMadeService;
import oopds.assignment.DC.services.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * This Controller is responsible for Controlling operations for Donation Made
 * Operations such as getting items in database.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class DonationMadeController {
	private final DonationMadeService donationMadeService;
	private final DonorService donorService;

	/**
	 * This is a constructor for the DonationMadeController controller with the
	 * specified parameters
	 *
	 * @param donationMadeService The service class for Donations Made.
	 * @param donorService        The service class for Donors.
	 */
	@Autowired
	public DonationMadeController(
		DonationMadeService donationMadeService,
		DonorService donorService
	) {
		this.donationMadeService = donationMadeService;
		this.donorService = donorService;
	}

	/**
	 * This is a HTTP Get method to get donation made by their ids
	 *
	 * @param id The id to be searched for.
	 * @return A List of Donation Made based on the id passed.
	 */
	@GetMapping("/donation-made/by-donorId/{id}")
	public List<DonationMade> getByDonorId(@PathVariable("id") UUID id) {
		List<DonationMade> donationMade = donationMadeService.findAllByDonorId(id);
		return donationMade;
	}

	/**
	 * This is a HTTP Get method to get all donation made available in the
	 * database.
	 *
	 * @return A List of all Donations Made in the database.
	 */
	@GetMapping("/donation-made")
	public List<DonationMade> getAll() {
		List<DonationMade> donationMade = donationMadeService.findAll();
		return donationMade;
	}

	/**
	 * This is a HTTP Get method to get donations made based on the item name.
	 *
	 * @param item The item name to be searched for.
	 * @return A List of Donations Made based on the item name in the database.
	 */
	@GetMapping("/donation-made/by-item/{item}")
	public List<DonationMade> getAllByItem(@PathVariable("item") String item) {
		List<DonationMade> donationMade = donationMadeService.findAllByItem(item);
		return donationMade;
	}

	/**
	 * This is a HTTP Post method to add new donations made into the database.
	 *
	 * @param data The full data representing the database.
	 * @return The new Donation Made entity with the newly saved data.
	 */
	@PostMapping("/make-donation")
	public DonationMade addDonationMade(@RequestBody Map<String, String> data) {
		DonationMade donationMade = new DonationMade();
		donationMade.setDonor(donorService.findById(UUID.fromString(data.get("donor_id"))));
		donationMade.setItem(data.get("item"));
		donationMade.setQuantity(Integer.parseInt(data.get("quantity")));
		donationMade.setRemaining(Integer.parseInt(data.get("remaining")));
		return donationMadeService.save(donationMade);
	}
}
