package oopds.assignment.DC.models;

import javax.persistence.Entity;

@Entity
public class DonationMade extends Donation {

    public DonationMade() {
    }

    public DonationMade(String item, int quantity, int remaining) {
        super(item, quantity, remaining);
    }
}
