package oopds.assignment.DC.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "donation")
public class Donation {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator"

    )
    @Column(name = "donation_id", nullable = false)
    private UUID donation_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "donor_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Donor donor;

    @Enumerated(EnumType.ORDINAL)
    private DonationItemName donation_item_name;

    @Column(name = "donation_quantity")
    private int donation_quantity;

    @Column(name = "donation_remaining")
    private int donation_remaining;

    public enum DonationItemName {
        rice, blanket, clothes, water, canned_food
    }

    public Donation() {
    }

    public Donation(DonationItemName donation_item_name, int donation_quantiy, int donation_remaining) {
        setDonation_item_name(donation_item_name);
        setDonation_quantity(donation_quantity);
        setDonation_remaining(donation_remaining);
    }

    public UUID getDonation_id() {
        return this.donation_id;
    }

    public void setDonation_id(UUID donation_id) {
        this.donation_id = donation_id;
    }

    public Donor getDonor() {
        return this.donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public DonationItemName getDonation_item_name() {
        return this.donation_item_name;
    }

    public void setDonation_item_name(DonationItemName donation_item_name) {
        this.donation_item_name = donation_item_name;
    }

    public int getDonation_quantity() {
        return this.donation_quantity;
    }

    public void setDonation_quantity(int donation_quantity) {
        this.donation_quantity = donation_quantity;
    }

    public int getDonation_remaining() {
        return this.donation_remaining;
    }

    public void setDonation_remaining(int donation_remaining) {
        this.donation_remaining = donation_remaining;
    }

}
