package oopds.assignment.DC.DAOs;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import oopds.assignment.DC.models.Dc;

/**
 * A Data Access Object (DAO) to be used by the Service Classes to access
 * Entities stored in the Database.
 * This DAO is used to access Dc Entities in the Database.
 */
@Repository
public interface DcDAO extends JpaRepository<Dc, UUID> {
    public Dc findByEmail(String email);

    public Dc findByName(String name);
}
