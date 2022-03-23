package oopds.assignment.DC.DAOs;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import oopds.assignment.DC.models.Donor;

/**
 * A Data Access Object (DAO) to be used by the Service Classes to access
 * Entities stored in the Database.
 * This DAO is used to access Donor Entities in the Database.
 */
@Repository
public interface DonorDAO extends JpaRepository<Donor, UUID> {
    public Donor findByEmail(String email);

    public Donor findByName(String name);

    public Boolean existsByEmail(String email);

    public Boolean existsByName(String name);

}
