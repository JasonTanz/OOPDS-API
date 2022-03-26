package oopds.assignment.DC.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oopds.assignment.DC.DAOs.DonationMadeDAO;
import oopds.assignment.DC.models.DonationMade;

@Service
public class DonationMadeService {
    @Autowired
    private DonationMadeDAO donationMadeDAO;

    public DonationMade getById(UUID id) {
        return donationMadeDAO.findById(id).get();
    }

    public List<DonationMade> getAll() {
        return donationMadeDAO.findAll();
    }

    public List<DonationMade> getAllRemaining() {
        return donationMadeDAO.findAllRemaining();
    }

    public DonationMade addDonationMade(DonationMade donationMade) {
        return donationMadeDAO.save(donationMade);
    }

    public DonationMade updateRemainingById(UUID id, int remaining) {
        DonationMade donationMade = this.getById(id);
        donationMade.setRemaining(remaining);
        return donationMadeDAO.save(donationMade);
    }

    public List<DonationMade> findByDonorId(UUID id) {
        return donationMadeDAO.findByDonorId(id);
    }

    public List<DonationMade> findByItem(String item) {
        return donationMadeDAO.findByItem(item);
    }

    public DonationMade save(DonationMade donationMade) {

        try {
            return donationMadeDAO.save(donationMade);
        } catch (Exception e) {
            return null;
        }
    }

}