package oopds.assignment.DC.models;

import java.util.UUID;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Donation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String item;
	private int quantity;
	private int remaining;

	public Donation() {}

	public Donation(String item, int quantity, int remaining) {
		this.item = item;
		this.quantity = quantity;
		this.remaining = remaining;
	}

	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getRemaining() {
		return this.remaining;
	}

	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}

	@Override
	public String toString(){
		return "Id: " + id + ", Item: " + item + ", Quantity: " + quantity + ", Remaining: " + remaining;
	}
}
