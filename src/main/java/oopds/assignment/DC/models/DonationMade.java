package oopds.assignment.DC.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * A Database Entity that stores details of the Donation Made by the Donors.
 */
@Entity
public class DonationMade extends Donation {

	@ManyToOne
	@JoinColumn(name = "donor_id")
	@JsonIgnoreProperties("donationMade")
	private Donor donor;

	@Column
	@ElementCollection(targetClass = String.class)
	private List<String> ngoName;

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

	public List<String> getNgoName() {
		return this.ngoName;
	}

	public void setNgoName(List<String> ngoName) {
		this.ngoName = ngoName;
	}

	public Donor getDonor() {
		return this.donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

}
