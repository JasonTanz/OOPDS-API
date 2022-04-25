package oopds.assignment.DC.models;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

/**
 * A Superclass Entity in a database that acts as a template for other classes.
 */
@MappedSuperclass
public class Donation {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", nullable = false)
	private UUID id;

	private String item;
	private int quantity;
	private int remaining;

	/**
	 * Constructs a Donation Entity with all null values.
	 */
	public Donation() {
	}

	/**
	 * Constructs a Donation Entity with specified values. Id is automatically
	 * generated.
	 *
	 * @param item      The item name.
	 * @param quantity  The total amount of item.
	 * @param remaining The remaining amount of item.
	 */
	public Donation(String item, int quantity, int remaining) {
		this.item = item;
		this.quantity = quantity;
		this.remaining = remaining;
	}

	/**
	 * Gets and Returns the ID of the Donation.
	 *
	 * @return A UUID-type ID of the Donation.
	 */
	public UUID getId() {
		return this.id;
	}

	/**
	 * Update and changes the ID of the Donation based on parameter given.
	 *
	 * @param id The new id of the Donation.
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * Gets and Returns the Name of the Item.
	 *
	 * @return a String value, storing the name of the Item.
	 */
	public String getItem() {
		return this.item;
	}

	/**
	 * Update and changes the Name of the Item in Donation based on parameter given.
	 *
	 * @param item The new name of the item in Donation.
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * Gets and Returns the total amount of item in Donation.
	 *
	 * @return an Integer value, storing the total amount of item in Donation.
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * Update and changes the total amount of item in Donation based on parameter
	 * given.
	 *
	 * @param quantity The new total amount of item in Donation.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets and Returns the remaining amount of item in Donation.
	 *
	 * @return an Integer value, storing the remaining amount of item in Donation.
	 */
	public int getRemaining() {
		return this.remaining;
	}

	/**
	 * Update and changes the remaining amount of item in Donation based on
	 * parameter given.
	 *
	 * @param remaining The new remaining amount of item in Donation.
	 */
	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}

	/**
	 * Returns a string representation of all values of the Donation class.
	 *
	 * @return a String representation of the Donation.
	 */
	@Override
	public String toString() {
		return ("Id: " +
				id +
				", Item: " +
				item +
				", Quantity: " +
				quantity +
				", Remaining: " +
				remaining);
	}
}
