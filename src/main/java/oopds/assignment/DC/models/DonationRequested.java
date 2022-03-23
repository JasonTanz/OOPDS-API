package oopds.assignment.DC.models;

import javax.persistence.Entity;

/**
 * A Database Entity that stores details of the Donation Requested by the Ngos.
 */
@Entity
public class DonationRequested extends Donation {

	/**
	 * Constructs a Donation Requested Entity with all null values.
	 */
	public DonationRequested() {
	}

	/**
	 * Constructs a Donation Requested Entity with specified values. Id is
	 * automatically generated.
	 * 
	 * @param item      The Item that has been Requested.
	 * @param quantity  The total amount of item requested.
	 * @param remaining The remaining amount of item requested that are not
	 *                  fulfilled.
	 */
	public DonationRequested(String item, int quantity, int remaining) {
		super(item, quantity, remaining);
	}
}
