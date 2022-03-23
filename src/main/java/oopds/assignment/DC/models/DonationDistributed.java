package oopds.assignment.DC.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * A Database Entity in a database that stores the details of the transaction
 * between Donation Requested and Donation Made
 */
@Entity
public class DonationDistributed {
	@EmbeddedId
	private DonationDistributedId id;

	@Column
	private int quantity;

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
	public DonationDistributed(DonationDistributedId id, int quantity) {
		this.id = id;
		this.quantity = quantity;
	}

	/**
	 * Gets and Returns the ID of the Donation Distributed.
	 * 
	 * @return The ID of the Donation Distributed Entity
	 */
	public DonationDistributedId getId() {
		return this.id;
	}

	/**
	 * Update and changes the ID of the Donation Distributed based on parameter
	 * given.
	 * 
	 * @param id The new id for the Donation Distributed Entity
	 */
	public void setId(DonationDistributedId id) {
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
}

/**
 * A Composite Entity to connect Donation Made and Donation Requested together.
 * This class will be used by Donation Distributed entity
 */
@Embeddable
class DonationDistributedId implements Serializable {
	// @Column(name = "donationMade_id")
	@ManyToOne
	@JoinColumn(name = "donationMade_id", referencedColumnName = "id")
	private DonationMade donationMade;

	// @Column(name = "donationRequested_id")
	@ManyToOne
	@JoinColumn(name = "donationRequested_id", referencedColumnName = "id")
	private DonationRequested donationRequested;

	public DonationDistributedId() {
	}

	/**
	 * Gets and Returns the Donation Made transaction (Giver)
	 * 
	 * @return a Donation Made Transaction
	 */
	public DonationMade getDonationMade() {
		return this.donationMade;
	}

	/**
	 * Sets the new Donation Made based on parameter given
	 * 
	 * @param donationMade The new Donation Made Transaction
	 */
	public void setDonationMade(DonationMade donationMade) {
		this.donationMade = donationMade;
	}

	/**
	 * Gets and Returns the Donation Requested Transaction (Receiver)
	 * 
	 * @return a Donation Requested Transaction
	 */
	public DonationRequested getDonationRequested_id() {
		return this.donationRequested;
	}

	/**
	 * Sets the new Donation Requested based on parameter given
	 * 
	 * @param donationRequested The new Donation Requested Transaction
	 */
	public void setDonationRequested_id(DonationRequested donationRequested) {
		this.donationRequested = donationRequested;
	}
}
