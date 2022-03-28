package oopds.assignment.DC.DAOs;

import java.util.UUID;
import oopds.assignment.DC.models.Ngo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A Data Access Object (DAO) to be used by the Service Classes to access
 * Entities stored in the Database.
 * This DAO is used to access Ngo Entities in the Database.
 */
@Repository
public interface NgoDAO extends JpaRepository<Ngo, UUID> {
	/**
	 * Abstract method that allows the service to search for the Ngo based on their Emails
	 
	 *
	 * @param email The email to be searched for.
	 * @return The Ngo entity according to the email passed.
	 */
	public Ngo findByEmail(String email);

	/**
	 * Abstract method that allows the service to search for the Ngo based on their Names
	 *
	 * @param name The name to be searched for.
	 * @return The Ngo entity according to the name passed.
	 */
	public Ngo findByName(String name);
}
