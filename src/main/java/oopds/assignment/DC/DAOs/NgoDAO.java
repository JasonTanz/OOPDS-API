package oopds.assignment.DC.DAOs;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import oopds.assignment.DC.models.Ngo;

/**
 * A Data Access Object (DAO) to be used by the Service Classes to access
 * Entities stored in the Database.
 * This DAO is used to access Ngo Entities in the Database.
 */
@Repository
public interface NgoDAO extends JpaRepository<Ngo, UUID> {
    public Ngo findByEmail(String email);

    public Ngo findByName(String name);

}
