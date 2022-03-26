package oopds.assignment.DC.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A Database Entity that stores details of the Donation Requested by the Ngos.
 */

@Entity
public class DonationRequested extends Donation {

	@ManyToOne
	@JoinColumn(name = "ngo_id")
	@JsonIgnoreProperties("donationRequested")
	private Ngo ngo;

	@Column
	@ElementCollection(targetClass = String.class)
	private List<String> donorName;

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

	public List<String> getDonorName() {
		return this.donorName;
	}

	public void setDonorName(List<String> donorName) {
		this.donorName = donorName;
	}

	public Ngo getNgo() {
		return this.ngo;
	}

	public void setNgo(Ngo ngo) {
		this.ngo = ngo;
	}

}
