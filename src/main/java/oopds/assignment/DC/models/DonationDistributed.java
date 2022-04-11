package oopds.assignment.DC.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;

/**
 * A Database Entity in a database that stores the details of the transaction
 * between Donation Requested and Donation Made
 */
@Entity
public class DonationDistributed {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", nullable = false)
	private UUID id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "donationMade_id")
	private DonationMade donationMade;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "donationRequested_id")
	private DonationRequested donationRequested;

	@Column
	private int quantity;

	@Column
	private String status;

	/**
	 * Constructs a Donation Distributed Entity with all Null values;
	 */
	public DonationDistributed() {
	}

	/**
	 * Constructs a Donation Distributed Entity with specified values.
	 *
	 * @param id       The id of Donation Distributed.
	 * @param quantity The amount of item transacted.
	 */
	public DonationDistributed(DonationMade donationMade, DonationRequested donationRequested, int quantity, String status) {
		this.donationMade = donationMade;
		this.donationRequested = donationRequested;
		this.quantity = quantity;
		this.status = status;
	}

	/**
	 * Gets and Returns the ID of the Donation Distributed.
	 *
	 * @return The ID of the Donation Distributed Entity
	 */
	public UUID getId() {
		return this.id;
	}

	/**
	 * Update and changes the ID of the Donation Distributed based on parameter
	 * given.
	 *
	 * @param id The new id for the Donation Distributed Entity
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * Gets and Returns the amount of item transacted in the Donation Distributed
	 * Entity.
	 *
	 * @return An Integer value, storing the amount of item transacted in the
	 *         donation Distributed Entity.
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * Update and changes the amount of item transacted in the Donation Distributed
	 * based on parameter given.
	 *
	 * @param quantity The new amount of item transacted for the Donation
	 *                 Distributed Entity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Returns a string representation of all values of the Donation Distributed
	 * class.
	 *
	 * @return a String representation of the Donation Distributed.
	 */
	@Override
	public String toString() {
		return "Id: " + id + ", Quantity: " + quantity;
	}


	public DonationMade getDonationMade() {
		return this.donationMade;
	}

	public void setDonationMade(DonationMade donationMade) {
		this.donationMade = donationMade;
	}

	public DonationRequested getDonationRequested() {
		return this.donationRequested;
	}

	public void setDonationRequested(DonationRequested donationRequested) {
		this.donationRequested = donationRequested;
	}


}

