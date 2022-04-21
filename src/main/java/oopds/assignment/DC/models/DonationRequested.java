package oopds.assignment.DC.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * A Database Entity that stores details of the Donation Requested by the Ngos.
 */
@Entity
public class DonationRequested extends Donation {
	@ManyToOne
	@JoinColumn(name = "ngo_id")
	@JsonIgnoreProperties({ "donationRequested", "password" })
	private Ngo ngo;

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

	/**
	 * Gets and Returns the Ngo that associates with Donation Requested
	 *
	 * @return the Ngo related to this Donation Requested
	 */
	public Ngo getNgo() {
		return this.ngo;
	}

	/**
	 * Updates and changes the Ngo associated with Donation Requested based on
	 * parameter
	 * given.
	 *
	 * @param ngo The new Ngo associated with Donation Requested
	 */
	public void setNgo(Ngo ngo) {
		this.ngo = ngo;
	}
}
