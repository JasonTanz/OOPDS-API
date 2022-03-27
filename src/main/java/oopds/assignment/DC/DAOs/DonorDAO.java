package oopds.assignment.DC.DAOs;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import oopds.assignment.DC.models.Donor;

/**
 * A Data Access Object (DAO) to be used by the Service Classes to access
 * Entities stored in the Database.
 * This DAO is used to access Donor Entities in the Database.
 */
@Repository
public interface DonorDAO extends JpaRepository<Donor, UUID> {

    /**
    * Abstract method that allows the service to search for the Donor based on their Emails
    * 
    * @param email The email to be searched for.
    * @return The Donor entity according to the email passed.
    */
    public Donor findByEmail(String email);

    /**
    * Abstract method that allows the service to search for the Donor based on their Names
    * 
    * @param name The name to be searched for.
    * @return The Donor entity according to the name passed.
    */
    public Donor findByName(String name);

}
