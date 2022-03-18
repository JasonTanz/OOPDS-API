package oopds.assignment.DC.models;

import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

// import org.springframework.data.annotation.Id;

@Entity
public class Donor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column
	private String name;

	@Column
	private String password;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "donor_id", referencedColumnName = "id")
	private List<DonationMade> donationMade;

	public Donor() {}

	public Donor(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<DonationMade> getDonationMade() {
		return this.donationMade;
	}

	public void setDonationMade(List<DonationMade> donationMade) {
		this.donationMade = donationMade;
	}

	@Override
	public String toString(){
		return "Id: " + id + ", Name: " + name + ", Password: " + password;
	}
}
