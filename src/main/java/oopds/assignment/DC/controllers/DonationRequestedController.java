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

import oopds.assignment.DC.DAOs.DonationRequestedDAO;
import oopds.assignment.DC.models.DataResponse;
import oopds.assignment.DC.models.DonationRequested;
import oopds.assignment.DC.services.DonationRequestedService;

@RestController
@RequestMapping("/dc")
public class DonationRequestedController {
    
    private DonationRequestedDAO donationRequestedDAO;
    private DonationRequestedService donationRequestedService;

    @GetMapping("/donationRequested")
    public ResponseEntity< DataResponse<List<DonationRequested>> > getAllDonationRequested (){
        try{
            List<DonationRequested> donationRequestedList = donationRequestedService.getAllDonationsRequested();
            if (donationRequestedList == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            DataResponse<List<DonationRequested>> dataResponse = new DataResponse<>(donationRequestedList, "Operation Completed");
            return new ResponseEntity<>(dataResponse, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/donationRequested/{id}")
    public ResponseEntity< DataResponse<DonationRequested> > getDonationRequestedById (@PathVariable("id")  UUID id){
        try{
            Optional<DonationRequested> donationRequested = donationRequestedService.getDonationsRequestedById(id);
            if (donationRequested.isPresent()){
                DataResponse<DonationRequested> dataResponse = new DataResponse<>(donationRequested.get(), "Operation completed");
                return new ResponseEntity<>(dataResponse, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/donationRequested/{quantity}")
    public ResponseEntity< DataResponse<List<DonationRequested>> > getDonationRequestedByQuantity (@PathVariable("quantity") int quantity) {
        try{
            List<DonationRequested> donationRequestedList = donationRequestedService.getDonationsRequestedByQuantity(quantity);
            if (donationRequestedList == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            DataResponse<List<DonationRequested>> dataResponse = new DataResponse<>(donationRequestedList, "Operation Completed");
            return new ResponseEntity<>(dataResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/donationRequested/{remaining}")
    public ResponseEntity< DataResponse<List<DonationRequested>> > getDonationRequestedByRemaining(@PathVariable("remaining") int remaining){
        try{
            List<DonationRequested> donationRequestedList = donationRequestedService.getDonationsRequestedByRemaining(remaining);
            if (donationRequestedList == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            DataResponse<List<DonationRequested>> dataResponse = new DataResponse<>(donationRequestedList, "Operation Completed");
            return new ResponseEntity<>(dataResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
