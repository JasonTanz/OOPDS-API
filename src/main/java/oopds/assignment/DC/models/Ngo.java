package oopds.assignment.DC.models;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Ngo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String password;
    private int manpower;

    @OneToMany
    @JoinColumn(name = "ngo_id", referencedColumnName = "id")
    private List<DonationRequested> donationRequested;

    public Ngo() {
    }

    public Ngo(String name, String password, int manpower) {
        this.name = name;
        this.password = password;
        this.manpower = manpower;
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

    public int getManpower() {
        return this.manpower;
    }

    public void setManpower(int manpower) {
        this.manpower = manpower;
    }

    public List<DonationRequested> getDonationRequested() {
        return this.donationRequested;
    }

    public void setDonationRequested(List<DonationRequested> donationRequested) {
        this.donationRequested = donationRequested;
    }

}
