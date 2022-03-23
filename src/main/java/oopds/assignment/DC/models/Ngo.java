package oopds.assignment.DC.models;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * A Database Entity that stores Ngo's data values to be used for the Spring
 * RESTful APIs operations.
 */
@Entity
public class Ngo implements UserDetails {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", nullable = false)
	private UUID id;

	private String name;
	private String password;
	private int manpower;

	@Column
	private String email;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "ngo_id", referencedColumnName = "id")
	private List<DonationRequested> donationRequested;

	/**
	 * Constructs a Ngo Entity with all null values.
	 */
	public Ngo() {
	}

	/**
	 * Constructs a Ngo Entity with the specified values. Id is automatically
	 * generated.
	 * 
	 * @param name     The name of the Ngo
	 * @param password The password used to login into Ngo's account.
	 * @param manpower The amount of manpower available for the Ngo
	 * @param email    The Email address of the Ngo
	 */
	public Ngo(String name, String password, int manpower, String email) {
		this.name = name;
		this.password = password;
		this.manpower = manpower;
		this.email = email;
	}

	/**
	 * Gets and Returns the ID of the Ngo.
	 * 
	 * @return A UUID-type ID of the Ngo.
	 */
	public UUID getId() {
		return this.id;
	}

	/**
	 * Update and changes the ID of the Ngo based on parameter given.
	 * 
	 * @param id The new id of the Ngo.
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * Gets and Returns the name of the Ngo.
	 * 
	 * @return a String value, storing the name of the Ngo.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Update and changes the Name of the Ngo based on parameter given.
	 * 
	 * @param name The new name of the Ngo.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets and Returns the password of the Ngo.
	 * 
	 * @return a String value, storing the password of the Ngo.
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Update and changes the Password of the Ngo based on parameter given.
	 * 
	 * @param password The new password of the Ngo.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets and Returns the available manpower of the Ngo.
	 * 
	 * @return an integer value, storing the available manpower of the Ngo.
	 */
	public int getManpower() {
		return this.manpower;
	}

	/**
	 * Update and changes the Available Manpower of the Ngo based on parameter
	 * given.
	 * 
	 * @param manpower The new amount of manpower available for the Ngo.
	 */
	public void setManpower(int manpower) {
		this.manpower = manpower;
	}

	/**
	 * Gets and Returns the email of the Ngo.
	 * 
	 * @return a String value, storing the email of the Ngo.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Update and changes the Email of the Ngo based on parameter given.
	 * 
	 * @param email The new email of the Ngo.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets and Returns the List of Donation Requested by the Ngo.
	 * 
	 * @return a List object, storing multiple Donation Requested by the Ngo.
	 */
	public List<DonationRequested> getDonationRequested() {
		return this.donationRequested;
	}

	/**
	 * Update and changes the List of Donation Requested with a new List of Donation
	 * Requested.
	 * 
	 * @param donationRequested the new List of Donation Requested by the Ngo.
	 */
	public void setDonationRequested(List<DonationRequested> donationRequested) {
		this.donationRequested = donationRequested;
	}

	/**
	 * Returns a string representation of all values of the Ngo class.
	 * 
	 * @return a String representation of the Ngo.
	 */
	@Override
	public String toString() {
		return "Id: " + id + ", Name: " + name + ", Password: " + password + ", Manpower: " + manpower;
	}

	@Override
	public String getUsername() {
		return this.getEmail();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ngo"));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
