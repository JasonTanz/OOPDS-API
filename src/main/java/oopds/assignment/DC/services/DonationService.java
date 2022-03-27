package oopds.assignment.DC.services;

import java.util.List;
import java.util.UUID;


/**
*  This is an interface that will be inherit by DonationMadeService and DonationRequestedService.
*/
public interface DonationService<T> {

    /**
    * Abstract method to find a T-type entity by its id.
    * 
    * @param id The id to be searched for.
    * @return The entity that was specified.
    */
    public T findById(UUID id);

    /**
    * Abstract method to get all entity of Type T from the database.
    * 
    * @return A List Object, storing all T-Type Entity available in the database.
    */
    public List<T> findAll();

    /**
    * Abstract method to get T-Type entities based on the itemName.
    *
    * @param item The item name to be searched for.
    * @return A List Object, storing the T-Type entities based on the itemName searched for. 
    */
    public List<T> findAllByItem(String item);

    /**
    * Abstract method to get T-Type entities that contains any Remaining Amount of Items.
    * 
    * @return A List Object, storing the T-Type Entities based on if there's any remaining amount of items left in entity.
    */
    public List<T> findAllRemaining();

    /**
    *  Saves all the data/value into the T-Type Entity in the database.
    * 
    *  @param donation the new entity to replace the old entity.
    *  @return the saved entity.
    */
    public T save(T donation);

    /**
    * Update the remaining items left in T-Type entity with the new amount.
    *
    * @param id the id of the T-Type entity to be updated.
    * @param remaining the new value for the remaining data attribute.
    * @return the newly updated entity
    */
    public T updateRemainingById(UUID id, int remaining);
}
