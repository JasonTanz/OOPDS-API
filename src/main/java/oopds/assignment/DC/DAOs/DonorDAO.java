package oopds.assignment.DC.DAOs;

import java.util.List;
import oopds.assignment.DC.models.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DonorDAO extends JpaRepository<Donor, UUID> {

    List<Donor> findByEmail(String email); 
    List<Donor> findByName(String name);
}
