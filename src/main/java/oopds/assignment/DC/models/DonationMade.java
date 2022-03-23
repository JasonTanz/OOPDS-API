package oopds.assignment.DC.models;

import javax.persistence.Entity;

/**
 * A Database Entity that stores details of the Donation Made by the Donors.
 */
@Entity
public class DonationMade extends Donation {

	/**
	 * Constructs a Donation Made Entity with all null values.
	 */
	public DonationMade() {
	}

	/**
	 * Constructs a Donation Made Entity with specified values. Id is automatically
	 * generated.
	 * 
	 * @param item      The Item that has been Donated.
	 * @param quantity  The total amount of item donated.
	 * @param remaining The remaining amount of item donated that are not
	 *                  used/requested by Ngos.
	 */
	public DonationMade(String item, int quantity, int remaining) {
		super(item, quantity, remaining);
	}
}
