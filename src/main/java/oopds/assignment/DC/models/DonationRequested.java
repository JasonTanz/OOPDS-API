package oopds.assignment.DC.models;

import javax.persistence.Entity;

@Entity
public class DonationRequested extends Donation {

	public DonationRequested() {}

	public DonationRequested(String item, int quantity, int remaining) {
		super(item, quantity, remaining);
	}
}
