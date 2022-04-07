package oopds.assignment.DC.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oopds.assignment.DC.DAOs.DonationDistributedDAO;
import oopds.assignment.DC.models.DonationDistributed;

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
}
