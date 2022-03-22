package oopds.assignment.DC.DAOs;

import java.util.List;
import java.util.UUID;
import oopds.assignment.DC.models.Ngo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A Data Access Object (DAO) to be used by the Service Classes to access Entities stored in the Database.
 * This DAO is used to access Ngo Entities in the Database.
 */
public interface NgoDAO extends JpaRepository<Ngo, UUID> {

    List<Ngo> findByEmail(String email); 
    List<Ngo> findByName(String name);
    
}
