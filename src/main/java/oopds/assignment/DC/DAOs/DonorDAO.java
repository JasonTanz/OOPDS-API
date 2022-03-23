package oopds.assignment.DC.DAOs;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import oopds.assignment.DC.models.Donor;

@Repository
public interface DonorDAO extends JpaRepository<Donor, UUID> {
    Donor findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByName(String name);
}
