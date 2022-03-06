package oopds.assignment.DC.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class NGO {
    // --- Data Fields ---
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "NGO Id")
    private UUID NGOId;    

    @Column(name = "NGO Name")
    private String NGOName;
    
    @Column(name = "NGO Phone")
    private String NGOPhone;

    @Column(name = "NGO Password")
    private String NGOPassword;

    @Column(name = "NGO Manpower")
    private int NGOManpower;

    // --- Constructors ---
    public NGO(){}

    public NGO(String name, String phone, String password, int manpower){
        this.NGOName = name;
        this.NGOPhone = phone;
        this.NGOPassword = password;
        this.NGOManpower = manpower;
    }

    // --- Getters and Setter ---
    public UUID getNGOId(){
        return NGOId;
    }

    public String getNGOName(){
        return NGOName;
    }

    public void setNGOName(String NGOName){
        this.NGOName = NGOName;
    }

    public String getNGOPhone(){
        return NGOPhone;
    }

    public void setNGOPhone(String NGOPhone){
        this.NGOPhone = NGOPhone;
    }

    public String getNGOPassword(){
        return NGOPassword;
    }

    public void setNGOPassword(String NGOPassword){
        this.NGOPassword = NGOPassword;
    }

    public int getNGOManpower(){
        return NGOManpower;
    }

    public void setNGOManpower(int NGOManpower){
        this.NGOManpower = NGOManpower;
    }

}
