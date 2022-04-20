package oopds.assignment.DC.DAOs;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import oopds.assignment.DC.models.NgoQueue;
import java.util.UUID;
@Repository
public interface NgoQueueDAO extends JpaRepository<NgoQueue, UUID>{

}
