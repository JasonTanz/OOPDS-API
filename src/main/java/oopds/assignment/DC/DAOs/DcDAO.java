package oopds.assignment.DC.DAOs;

import java.util.UUID;
import oopds.assignment.DC.models.Dc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A Data Access Object (DAO) to be used by the Service Classes to access
 * Entities stored in the Database.
 * This DAO is used to access Dc Entities in the Database.
 */
@Repository
public interface DcDAO extends JpaRepository<Dc, UUID> {
	/**
	 * Abstract method to allow the service to search for the DC based on the Email.
	 *
	 * @param email The email parameter to be searched.
	 * @return The DC entity according to the email passed.
	 */
	public Dc findByEmail(String email);

	/**
	 * Abstract method to allow the service to search for the DC based on the Name.
	 *
	 * @param name The name parameter to be searched.
	 * @return The DC entity according to the name passed.
	 */
	public Dc findByName(String name);
}
