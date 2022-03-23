package oopds.assignment.DC.DAOs;

import java.util.List;
import oopds.assignment.DC.models.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

/**
 * A Data Access Object (DAO) to be used by the Service Classes to access Entities stored in the Database.
 * This DAO is used to access Donor Entities in the Database.
 */
public interface DonorDAO extends JpaRepository<Donor, UUID> {

    List<Donor> findByEmail(String email); 
    List<Donor> findByName(String name);
}
