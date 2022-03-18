package oopds.assignment.DC.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import oopds.assignment.DC.DAOs.DonationRequestedDAO;
import oopds.assignment.DC.models.DonationRequested;

public class DonationRequestedService {
    
    public DonationRequestedDAO donationRequestedDAO;

    public List<DonationRequested> getAllDonationsRequested(){
        return donationRequestedDAO.findAll();
    }

    public Optional<DonationRequested> getDonationsRequestedById(UUID id){
        return donationRequestedDAO.findById(id);
    }

    public List<DonationRequested> getDonationsRequestedByQuantity(int quantity){
        return donationRequestedDAO.findByQuantity(quantity);
    }

    public List<DonationRequested> getDonationsRequestedByRemaining(int remaining){
        return donationRequestedDAO.findByRemaining(remaining);
    }

}
