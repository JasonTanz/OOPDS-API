package oopds.assignment.DC.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

/**
 * A Database Entity that stores Donor's data values to be used for the Spring
 * RESTful APIs operations.
 */
@Entity
public class Donor {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", nullable = false)
	private UUID id;

	@Column
	private String name;

	@Column
	private String password;

	@Column
	private String email;

	@Column
	private String phoneNumber;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "donor")
	@JsonIgnoreProperties("donor")
	private List<DonationMade> donationMade;

	/**
	 * Constructs a Donor Entity with all null values.
	 */
	public Donor() {
	}

	/**
	 * Constructs a Donor Entity with specified values. Id is automatically
	 * generated.
	 *
	 * @param name     The name of the Donor.
	 * @param password The password used to login into Donor's account.
	 * @param email    The email address of the Donor.
	 */
	public Donor(String name, String password, String email) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/**
	 * Gets and Returns the ID of the donor.
	 *
	 * @return A UUID-type ID of the donor.
	 */
	public UUID getId() {
		return this.id;
	}

	/**
	 * Update and changes the ID of the donor based on parameter given.
	 *
	 * @param id The new id of the donor.
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * Gets and Returns the name of the donor.
	 *
	 * @return a String value, storing the name of the donor.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Update and changes the Name of the donor based on parameter given.
	 *
	 * @param name The new name of the donor.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets and Returns the password of the Donor.
	 *
	 * @return a String value, storing the password of the donor.
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Update and changes the Password of the donor based on parameter given.
	 *
	 * @param password The new password of the donor.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets and Returns the email of the Donor.
	 *
	 * @return a String value, storing the email of the donor.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Update and changes the Email of the donor based on parameter given.
	 *
	 * @param email The new email of the donor.
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * Gets and Returns the phone number of the Donor.
	 *
	 * @return a String value, storing the phone number of the donor.
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Update and changes the Phone Number of the donor based on parameter given.
	 *
	 * @param phoneNumber The new Phone Number of the donor.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets and Returns the List of Donation Made by the donor.
	 *
	 * @return a List object, storing multiple Donation Made by the Donor.
	 */
	public List<DonationMade> getDonationMade() {
		return this.donationMade;
	}

	/**
	 * Update and changes the List of Donation Made with a new List of Donation
	 * Made.
	 *
	 * @param donationMade the new List of Donation Made by the Donor.
	 */
	public void setDonationMade(List<DonationMade> donationMade) {
		this.donationMade = donationMade;
	}

	/**
	 * Returns a string representation of all values of the Donor class.
	 *
	 * @return a String representation of the Donor.
	 */
	@Override
	public String toString() {
		return "Id: " + id + ", Name: " + name + ", Password: " + password;
	}
}
