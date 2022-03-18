package oopds.assignment.DC.DAOs;

import java.util.List;
import java.util.UUID;
import oopds.assignment.DC.models.Ngo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NgoDAO extends JpaRepository<Ngo, UUID> {

    List<Ngo> findByEmail(String email); 
    List<Ngo> findByName(String name);
    
}
