package oopds.assignment.DC.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oopds.assignment.DC.models.DataResponse;
import oopds.assignment.DC.models.DonationDistributed;
import oopds.assignment.DC.models.DonationMade;
import oopds.assignment.DC.models.DonationRequested;
import oopds.assignment.DC.services.DonationDistributedService;
import oopds.assignment.DC.services.DonationRequestedService;
import oopds.assignment.DC.services.DonationMadeService;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * A Controller is a Class that controls the operations of the web service by
 * creating a REST API.
 * This Controller is responsible for Controlling operations for
 * DonationDistributed objects.
 * Operations such as changing status of the Donation Distributed is found here.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class DonationDistributedController {
    private final DonationDistributedService donationDistributedService;
    private final DonationMadeService donationMadeService;
    private final DonationRequestedService donationRequestedService;

    /**
     * This is a constructor for the DonationDistributedController controller with
     * the
     * specified parameters.
     *
     * @param donationDistributedService The service class for Donations
     *                                   Distributed.
     * @param donationMadeService        The service class for Donations
     *                                   Made.
     * @param donationRequestedService   The service class for Donation Requested.
     */
    public DonationDistributedController(DonationDistributedService donationDistributedService,
            DonationMadeService donationMadeService, DonationRequestedService donationRequestedService) {
        this.donationDistributedService = donationDistributedService;
        this.donationMadeService = donationMadeService;
        this.donationRequestedService = donationRequestedService;
    }

    /**
     * This is a HTTP Get method to get all donation distributed available in the
     * database.
     *
     * @return A List of all Donations Distributed in the database.
     */
    @GetMapping("/donation-distributed")
    public ResponseEntity<DataResponse<?>> getAll() {
        try {
            List<DonationDistributed> donationDistributed = donationDistributedService.findAll();
            List<DonationMade> donationMade = donationMadeService.findAllRemaining();
            List<DonationRequested> donationRequested = donationRequestedService.findAllRemaining();
            Map<String, List<?>> data = new HashMap<>();
            data.put("Donation_distributed", donationDistributed);
            data.put("Donation_made", donationMade);
            data.put("Donation_requested", donationRequested);
            DataResponse<?> dataResponse = new DataResponse<>(
                    data,
                    "Fetched donation distributed successful");
            return new ResponseEntity<DataResponse<?>>(dataResponse, HttpStatus.OK);

        } catch (Exception e) {
            // return error message
            return new ResponseEntity<DataResponse<?>>(new DataResponse<>(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This is a HTTP Patch Request to update the status of a donation distributed
     * item.
     * 
     * @param id        The id of the Donation Distributed object.
     * @param newStatus The new status of the object to be updated.
     * 
     * @return A Response Entity object containing a message indicating the success
     *         or failure of the operation.
     */
    @PatchMapping("/donation-distributed/{id}")
    public ResponseEntity<DataResponse<?>> updateStatus(@RequestBody @PathVariable("id") UUID id, String newStatus) {

        try {
            // Get the donation distributed needed using ID
            DonationDistributed donationDistributed = donationDistributedService.findById(id);

            // Then set the status to whichever correct one ("Available", "Reserved", and
            // one more i forgot)
            donationDistributed.setStatus(newStatus);
            // Update the attribute and save it into database
            donationDistributedService.save(donationDistributed);

            // Return success message
            DataResponse<?> dataResponse = new DataResponse<>("Update donation distributed status successful");
            return new ResponseEntity<DataResponse<?>>(dataResponse, HttpStatus.OK);

        } catch (Exception e) {
            // return error message
            return new ResponseEntity<DataResponse<?>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * This is a HTTP GET method to get a List of Donation Distributed based on the
     * specific status requested.
     * 
     * @param status The status to be searched for.
     * 
     * @return A List of Donation Distributed that matched the request.
     */
    @GetMapping("/donation-distributed/{status}")
    public List<DonationDistributed> getAllByStatus(@PathVariable("status") String status) {
        return donationDistributedService.findByStatus(status);
    }


    /**
     * This is a HTTP GET method to get a List of Donation Distributed/Made with the specified Donor Id.
     *
     * @param id The Donor Id to be searched for.
     * 
     * @return A Response Entity object that contains all of the donations distributed/made related with the Donor Id passed.
     */
    @GetMapping("/donation-distributed/by-donor-id/{id}")
    public ResponseEntity<DataResponse<?>> getAllByDonorId(@PathVariable("id") UUID id) {
        try {
            List<DonationMade> donationMadeList = donationMadeService.findRemainingByDonorId(id);
            List<DonationDistributed> donationDistributedList = donationDistributedService.findByDonorId(id);

            Map<String, List<?>> response = new HashMap<>();
            response.put("Donation_distributed", donationDistributedList);
            response.put("Donation_made", donationMadeList);
            DataResponse<?> dataResponse = new DataResponse<>(
                    response,
                    "Get donation made successful");
            return new ResponseEntity<DataResponse<?>>(dataResponse, HttpStatus.OK);

        } catch (Exception e) {
            // return error message
            return new ResponseEntity<DataResponse<?>>(new DataResponse<>(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * This is HTTP GET method to get a List of Donation Distributed/Requested with the specified Ngo Id.
     * 
     * @param id The Ngo Id to be searched for.
     * 
     * @return A Response Entity object that contains all of the donations distributed/requested related with the Ngo Id passed.
     */
    @GetMapping("/donation-distributed/by-ngo-id/{id}")
    public ResponseEntity<DataResponse<?>> getAllByNgoId(@PathVariable("id") UUID id) {
        try {
            List<DonationRequested> donationRequestedList = donationRequestedService.findRemainingByNgoId(id);
            List<DonationDistributed> donationDistributedList = donationDistributedService.findByNgoId(id);

            Map<String, List<?>> response = new HashMap<>();
            response.put("Donation_distributed", donationDistributedList);
            response.put("Donation_requested", donationRequestedList);
            DataResponse<?> dataResponse = new DataResponse<>(
                    response,
                    "Get donation requested successful");
            return new ResponseEntity<DataResponse<?>>(dataResponse, HttpStatus.OK);

        } catch (Exception e) {
            // return error message
            return new ResponseEntity<DataResponse<?>>(new DataResponse<>(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
