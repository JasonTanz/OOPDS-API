package oopds.assignment.DC.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import oopds.assignment.DC.DAOs.DonationMadeDAO;
import oopds.assignment.DC.models.DonationMade;
import oopds.assignment.DC.services.DonationMadeService;
import oopds.assignment.DC.services.DonorService;
// import oopds.assignment.DC.DAOs.donationMadeDAO;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DonationMadeController {
    private final DonationMadeService donationMadeService;

    // @Autowired
    private final DonationMadeDAO donationMadeDAO;

    // @Autowired
    private final DonorService donorService;

    @Autowired
    public DonationMadeController(DonationMadeService donationMadeService, DonationMadeDAO donationMadeDAO,
            DonorService donorService) {
        this.donationMadeService = donationMadeService;
        this.donationMadeDAO = donationMadeDAO;
        this.donorService = donorService;
    }

    @GetMapping("/donation-made/by-donorId/{id}")
    public List<DonationMade> getByDonorId(@PathVariable("id") String id) {
        System.out.println(id);
        List<DonationMade> donationMade = donationMadeService.findByDonorId(UUID.fromString(id));
        return donationMade;
    }

    @GetMapping("/donation-made")
    public List<DonationMade> getall() {
        List<DonationMade> donationMade = donationMadeDAO.findAll();
        return donationMade;
    }

    @GetMapping("/donation-made/by-item/{item}")
    public List<DonationMade> findByItem(@PathVariable("item") String item) {
        List<DonationMade> donationMade = donationMadeService.findByItem(item);
        return donationMade;
    }

    @PostMapping("donation-made/add")
    public List<DonationMade> addDonationMade(@RequestBody Map<String, String> data) {
        DonationMade donationMade = new DonationMade();
        donationMade.setDonor(donorService.getDonorsById(UUID.fromString(data.get("donorId"))));
        donationMade.setItem(data.get("item"));
        donationMade.setQuantity(Integer.parseInt(data.get("quantity")));
        donationMade.setRemaining(Integer.parseInt(data.get("remaining")));
        donationMadeService.save(donationMade);
        return donationMadeDAO.findAll();
    }
}
