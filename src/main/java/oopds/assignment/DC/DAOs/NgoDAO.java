package oopds.assignment.DC.DAOs;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import oopds.assignment.DC.models.Ngo;

@Repository
public interface NgoDAO extends JpaRepository<Ngo, UUID> {
    public Ngo findByEmail(String email);
}