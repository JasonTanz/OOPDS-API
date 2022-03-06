package oopds.assignment.DC.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class DC {
    // --- Data Fields ---
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DC Id", nullable = false)
    private UUID DCId;

    @Column(name = "DC Name")
    private String DCName;

    @Column(name = "DC Password")
    private String DCPassword;

    // --- Constructors ---
    public DC(){}

    public DC(String DCName, String DCPassword){
        this.DCName = DCName;
        this.DCPassword = DCPassword;
    }

    // --- Getters and Setter ---
    public UUID getDCId(){
        return DCId;
    }

    public String getDCName(){
        return DCName;
    }

    public void setDCName(String DCName){
        this.DCName = DCName;
    }

    public String getDCPassword(){
        return DCPassword;
    }

    public void setDCPassword(String DCPassword){
        this.DCPassword = DCPassword;
    }

}
