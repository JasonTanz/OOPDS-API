package oopds.assignment.DC.DAOs;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import oopds.assignment.DC.models.DonationRequested;

public interface DonationRequestedDAO extends JpaRepository<DonationRequested, UUID> {
    List<DonationRequested> findByQuantity(int quantity);
    List<DonationRequested> findByRemaining(int remaining);
}