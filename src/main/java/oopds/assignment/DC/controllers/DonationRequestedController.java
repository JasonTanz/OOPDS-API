package oopds.assignment.DC.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import oopds.assignment.DC.models.DataResponse;
import oopds.assignment.DC.models.DonationRequested;
import oopds.assignment.DC.services.DonationRequestedService;

/**
 * A Controller is a Class that controls the operations of the web service by
 * creating a REST API.
 * This Controller is responsible for Controlling operations for Donation
 * Requested Entities.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin

public class DonationRequestedController {

    private DonationRequestedService donationRequestedService;

    public DonationRequestedController(DonationRequestedService donationRequestedService) {
        this.donationRequestedService = donationRequestedService;
    }

    /**
     * Gets and Sends all Donations Requested available in the database as a
     * resource to the web.
     * If there's no Donations Requested in the database (null), then it will return
     * a HTTP error code.
     * 
     * @return a ResponseEntity Object, which contains a List of Donations Requested
     *         and the appropriate HTTP Response Code or only a HTTP Response Code
     *         to the web.
     * @throws Exception Any exceptions in operation will return a HTTP error code.
     */
    @GetMapping("/donationRequested")
    public ResponseEntity<DataResponse<List<DonationRequested>>> getAllDonationRequested() {
        try {
            List<DonationRequested> donationRequestedList = donationRequestedService.getAllDonationsRequested();
            if (donationRequestedList == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            DataResponse<List<DonationRequested>> dataResponse = new DataResponse<>(donationRequestedList,
                    "Operation Completed");
            return new ResponseEntity<>(dataResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets and Sends the Donation Requested based on ID in the database as a
     * resource to the web.
     * If there's no Donation Requested with the specified ID, then it will return a
     * HTTP error code.
     * 
     * @return a ResponseEntity Object, which contains the Donation Requested and
     *         the appropriate HTTP Response Code or only a HTTP Response Code to
     *         the web.
     * @throws Exception Any exceptions in operation will return a HTTP error code.
     */
    @GetMapping("/donationRequested/{id}")
    public ResponseEntity<DataResponse<DonationRequested>> getDonationRequestedById(@PathVariable("id") UUID id) {
        try {
            Optional<DonationRequested> donationRequested = donationRequestedService.getDonationsRequestedById(id);
            if (donationRequested.isPresent()) {
                DataResponse<DonationRequested> dataResponse = new DataResponse<>(donationRequested.get(),
                        "Operation completed");
                return new ResponseEntity<>(dataResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets and Sends all Donations Requested based on their total quantity of items
     * requested in the database as a resource to the web.
     * If there's no onations Requested matching the total quantity of items
     * requested specified in the database (null), then it will return a HTTP error
     * code.
     * 
     * @return a ResponseEntity Object, which contains a List of Donations Requested
     *         and the appropriate HTTP Response Code or only a HTTP Response Code
     *         to the web.
     * @throws Exception Any exceptions in operation will return a HTTP error code.
     */
    @GetMapping("/donationRequested/{quantity}")
    public ResponseEntity<DataResponse<List<DonationRequested>>> getDonationRequestedByQuantity(
            @PathVariable("quantity") int quantity) {
        try {
            List<DonationRequested> donationRequestedList = donationRequestedService
                    .getDonationsRequestedByQuantity(quantity);
            if (donationRequestedList == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            DataResponse<List<DonationRequested>> dataResponse = new DataResponse<>(donationRequestedList,
                    "Operation Completed");
            return new ResponseEntity<>(dataResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets and Sends all Donations Requested based on their remaining quantity of
     * items requested in the database as a resource to the web.
     * If there's no onations Requested matching the remaining quantity of items
     * requested specified in the database (null), then it will return a HTTP error
     * code.
     * 
     * @return a ResponseEntity Object, which contains a List of Donations Requested
     *         and the appropriate HTTP Response Code or only a HTTP Response Code
     *         to the web.
     * @throws Exception Any exceptions in operation will return a HTTP error code.
     */
    @GetMapping("/donationRequested/{remaining}")
    public ResponseEntity<DataResponse<List<DonationRequested>>> getDonationRequestedByRemaining(
            @PathVariable("remaining") int remaining) {
        try {
            List<DonationRequested> donationRequestedList = donationRequestedService
                    .getDonationsRequestedByRemaining(remaining);
            if (donationRequestedList == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            DataResponse<List<DonationRequested>> dataResponse = new DataResponse<>(donationRequestedList,
                    "Operation Completed");
            return new ResponseEntity<>(dataResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/donation-requested/by-item/{item}")
    public List<DonationRequested> findByItem(@PathVariable("item") String item) {
        List<DonationRequested> donationMade = donationRequestedService.findByItem(item);
        return donationMade;
    }

}
