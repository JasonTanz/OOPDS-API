package oopds.assignment.DC.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Dc {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;
    private String email;
    private String name;
    private String password;

    /**
     * Constructs a Distribution Center Entity with all null values.
     */
    public Dc() {
    }

    /**
     * Constructs a Distribution Center Entity with specified values. Id is
     * automatically generated.
     * 
     * @param name     The name of the Distribution Center.
     * @param password The password used to login into Distribution Center's
     *                 account.
     */
    public Dc(String name, String password, String email) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Gets and Returns the ID of the Distribution Center.
     * 
     * @return A UUID-type ID of the Distribution Center.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Update and changes the ID of the Distribution Center based on parameter
     * given.
     * 
     * @param id The new id of the Distribution Center.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets and Returns the name of the Distribution Center.
     * 
     * @return a String value, storing the name of the Distribution Center.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Update and changes the Name of the Distribution Center based on parameter
     * given.
     * 
     * @param name The new name of the Distribution Center.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets and Returns the password of the Distribution Center.
     * 
     * @return a String value, storing the password of the Distribution Center.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Update and changes the Password of the Distribution Center based on parameter
     * given.
     * 
     * @param password The new password of the Distribution Center.
     */
    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of all values of the Distribution Center
     * class.
     * 
     * @return a String representation of the Distribution Center.
     */
    @Override
    public String toString() {
        return "Id: " + id + ", Name: " + name + ", Password: " + password;
    }

    

}
