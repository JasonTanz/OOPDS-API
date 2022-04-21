package oopds.assignment.DC.models;

import java.util.List;
import java.util.Queue;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class NgoQueue {
    @Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", nullable = false)
	private UUID id;

    @ManyToMany
    @JsonIgnoreProperties({"donationRequested", "password"})
    private List<Ngo> ngoList;

    private String type;

    public NgoQueue() {

    }

    public NgoQueue(List<Ngo> ngoList, String type) {
        this.ngoList = ngoList;
        this.type = type;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Ngo> getNgoList() {
        return this.ngoList;
    }

    public void setNgoList(List<Ngo> ngoList) {
        this.ngoList = ngoList;
    }


    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
