package oopds.assignment.DC.DAOs;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import oopds.assignment.DC.models.NgoQueue;
import java.util.UUID;


/**
 * A Data Access Object (DAO) to be used by the Service Classes to access
 * Entities stored in the Database.
 * This DAO is used to access NgoQueue Entities in the Database.
 */
@Repository
public interface NgoQueueDAO extends JpaRepository<NgoQueue, UUID>{

}
