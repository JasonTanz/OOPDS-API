package oopds.assignment.DC.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A Database Entity that stores details of the Donation Made by the Donors.
 */
@Entity
public class DonationMade extends Donation {

	@ManyToOne
	@JoinColumn(name = "donor_id")
	@JsonIgnoreProperties({"donationMade","password"})
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

	/**
	 * Gets and Returns the Ngo Name that associates with Donation Made
	 * 
	 * @return a List of Ngo names related to this Donation Made
	 */
	public List<String> getNgoName() {
		return this.ngoName;
	}

	/**
     * Updates and changes the Ngo Name associated with Donation Made based on parameter
     * given.
     * 
     * @param ngoName The new list of ngo name associated with donation made 
     */
	public void setNgoName(List<String> ngoName) {
		this.ngoName = ngoName;
	}


	/**
	* Gets and Returns the Donor that associates with Donation Made based on
	* parameters given.
	*
	* @return the Donor related to the Donation Made
	*/
	public  Donor getDonor() {
		return this.donor;
	}

	/**
     * Updates and changes the Donor associated with Donation Made based on parameter
     * given.
     * 
     * @param donor The new donor associated with donation made 
     */
	public void setDonor(Donor donor) {
		this.donor = donor;
	}

}
