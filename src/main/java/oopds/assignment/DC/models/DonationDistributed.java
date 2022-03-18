package oopds.assignment.DC.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DonationDistributed {
	@EmbeddedId
	private DonationDistributedId id;

	@Column
	private int quantity;

	public DonationDistributed() {}

	public DonationDistributed(DonationDistributedId id, int quantity) {
		this.id = id;
		this.quantity = quantity;
	}

	public DonationDistributedId getId() {
		return this.id;
	}

	public void setId(DonationDistributedId id) {
		this.id = id;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString(){
		return "Id: " + id + ", Quantity: " + quantity;
	}
}

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

	public DonationDistributedId() {}

	public DonationMade getDonationMade() {
		return this.donationMade;
	}

	public void setDonationMade(DonationMade donationMade) {
		this.donationMade = donationMade;
	}

	public DonationRequested getDonationRequested_id() {
		return this.donationRequested;
	}

	public void setDonationRequested_id(DonationRequested donationRequested) {
		this.donationRequested = donationRequested;
	}
}
