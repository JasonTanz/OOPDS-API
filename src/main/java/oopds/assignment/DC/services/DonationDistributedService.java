package oopds.assignment.DC.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oopds.assignment.DC.DAOs.DonationDistributedDAO;
import oopds.assignment.DC.models.DonationDistributed;
import oopds.assignment.DC.models.Ngo;


@Service
public class DonationDistributedService {
    private final DonationDistributedDAO donationDistributedDAO;


    @Autowired
    public DonationDistributedService(DonationDistributedDAO donationDistributedDAO) {
        this.donationDistributedDAO = donationDistributedDAO;
    }

    public List<DonationDistributed> findAll() {
        return donationDistributedDAO.findAll();
    }

    public void save(DonationDistributed donationDistributed) {
        donationDistributedDAO.save(donationDistributed);
    }

    public DonationDistributed findById(UUID id) {
        // return donationDistributedDAO.find(donationMade, donationRequested);
        return donationDistributedDAO.findById(id).get();
    }

    // Don't know if you'll use this or not
    public List<DonationDistributed> findByStatus(String status){
        return donationDistributedDAO.findByStatus(status);
    }

    public List<DonationDistributed> findByNgo(Ngo ngo){
        List<DonationDistributed> donationDistributed = this.findAll();
        List<DonationDistributed> donationDistributedThatMatchNgo = new ArrayList<>();
        for ( DonationDistributed dd : donationDistributed ){
            if ( dd.getDonationRequested().getNgo().getId().equals(ngo.getId()) ){
                donationDistributedThatMatchNgo.add(dd);
            }
        }
    
        return donationDistributedThatMatchNgo;
    }
}
