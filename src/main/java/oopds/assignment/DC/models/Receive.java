package oopds.assignment.DC.models;

import java.util.UUID;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

public class Receive {
    @Id
    private UUID receive_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ngo_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ngo ngo;

    public enum Receive_item_name {
        rice, blanket, clothes, water, canned_food
    }

    private int receive_quantity;
    private int receive_remaining;

    public Receive() {
    }

    public Receive(UUID receive_id, Ngo ngo, int receive_quantity, int receive_remaining) {
        this.receive_id = receive_id;
        this.ngo = ngo;
        this.receive_quantity = receive_quantity;
        this.receive_remaining = receive_remaining;
    }

    public Receive(Ngo ngo, int receive_quantity, int receive_remaining) {
        this.ngo = ngo;
        this.receive_quantity = receive_quantity;
        this.receive_remaining = receive_remaining;
    }

    public UUID getReceive_id() {
        return this.receive_id;
    }

    public void setReceive_id(UUID receive_id) {
        this.receive_id = receive_id;
    }

    public Ngo getNgo() {
        return this.ngo;
    }

    public void setNgo(Ngo ngo) {
        this.ngo = ngo;
    }

    public int getReceive_quantity() {
        return this.receive_quantity;
    }

    public void setReceive_quantity(int receive_quantity) {
        this.receive_quantity = receive_quantity;
    }

    public int getReceive_remaining() {
        return this.receive_remaining;
    }

    public void setReceive_remaining(int receive_remaining) {
        this.receive_remaining = receive_remaining;
    }

}
