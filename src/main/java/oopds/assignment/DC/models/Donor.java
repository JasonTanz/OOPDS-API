package oopds.assignment.DC.models;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

public class Donor {
    // --- Data Fields ---

    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Donor ID", nullable = false)
    private UUID donorId;
    
    @Column(name = "Donor Name")
    private String donorName;

    @Column(name = "Donor Phone")
    private String donorPhone;

    @Column(name = "Donor Password")
    private String donorPassword;

    // --- Constructor ---
    public Donor(){}

    public Donor(String donorName, String donorPhone, String donorPassword){
        this.donorName = donorName;
        this.donorPhone = donorPhone;
        this.donorPassword = donorPassword;
    }

    // --- Getters and Setter ---
    public UUID getDonorId(){
        return donorId;
    }

    public String getDonorName(){
        return donorName;
    }

    public void setDonorName(String donorName){
        this.donorName = donorName;
    }

    public String getDonorPhone(){
        return donorPhone;
    }

    public void setDonorPhone(String donorPhone){
        this.donorPhone = donorPhone;
    }

    public String getDonorPassword(String donorPassword){
        return donorPassword;
    }

    public void setDonorPassword(String donorPassword){
        this.donorPassword = donorPassword;
    }

}
