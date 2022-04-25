package oopds.assignment.DC.models;

import java.util.List;

import java.util.UUID;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;

/**
 * A Database Entity that stores Queue of Ngos data into the database.
 * Used to update and show the result of the queue.
 */
@Entity
public class NgoQueue {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;
    
    @ManyToMany
    @JsonIgnoreProperties({ "donationRequested", "password" })
    private List<Ngo> ngoList;

    private String type;

    /**
     * Default constructor that create an Empty NgoQueue object.
    */
    public NgoQueue() {

    }


    /**
     * Construct an NgoQueue object with the specified values. Id is automatically generated.
     * 
     * @param ngoList The Queue of Ngos stored as a list.
     * @param type The type of Queue that is passed into the constructor.
    */
    public NgoQueue(List<Ngo> ngoList, String type) {
        this.ngoList = ngoList;
        this.type = type;
    }


    /**
	 * Gets and Returns the ID of the NgoQueue.
	 *
	 * @return A UUID-type ID of the NgoQueue.
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
	 * Gets and Returns the List of Ngo, representing the Queue.
	 *
	 * @return A List of Ngos.
	 */
    public List<Ngo> getNgoList() {
        return this.ngoList;
    }


    /**
	 * Update and changes the Queue by replacing the old list of Ngos with a new one.
	 *
	 * @param ngoList The new list of Ngos, representing the new state of the queue.
	 */
    public void setNgoList(List<Ngo> ngoList) {
        this.ngoList = ngoList;
    }


    /**
	 * Gets and Returns the Type of Queue stored.
	 *
	 * @return A String representing the queue's type.
	 */
    public String getType() {
        return this.type;
    }


    /**
	 * Update and changes the Type of Queue according to what is passed in.
	 *
	 * @param type The new type of the Queue.
	 */
    public void setType(String type) {
        this.type = type;
    }

}
