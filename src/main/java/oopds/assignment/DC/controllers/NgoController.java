package oopds.assignment.DC.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import oopds.assignment.DC.DAOs.NgoDAO;
import oopds.assignment.DC.models.DataResponse;
import oopds.assignment.DC.models.Ngo;
import oopds.assignment.DC.services.NgoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A Controller is a Class that controls the operations of the web service by creating a REST API.
 * This Controller is responsible for Controlling operations for Ngo Entities.
 */
@RestController
@RequestMapping("/dc")
public class NgoController {

	NgoService ngoService;
	NgoDAO ngoDAO;

	/**
	 * Gets and Sends all Ngos available in the database as a resource to the web.
	 * If there's no Ngos in the database (null), then it will return a HTTP error code.
	 * 
	 * @return a ResponseEntity Object, which contains a List of Ngos and the appropriate HTTP Response Code or only a HTTP Response Code to the web.
	 * @throws Exception Any exceptions in operation will return a HTTP error code.
	 */
	@GetMapping("/ngo")
	public ResponseEntity< DataResponse<List<Ngo>> > getAllNgos() {
		try {
			List<Ngo> ngos = ngoService.getNgos();
			if (ngos == null)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				
			DataResponse<List<Ngo>> dataResponse = new DataResponse<>(ngoService.getNgos(), "Operation Completed");
			return new ResponseEntity<>(dataResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets and Sends the Ngos in the database based on ID as a resource to the web.
	 * If there's no Ngos with the specified ID, then it will return a HTTP error code.
	 * 
	 * @return a ResponseEntity Object, which contains the Ngo and the appropriate HTTP Response Code or only a HTTP Response Code to the web.
	 * @throws Exception Any exceptions in operation will return a HTTP error code.
	 */
	@GetMapping("/ngo/{id}")
	public ResponseEntity< DataResponse<Ngo> > getNgoById(@PathVariable("id") UUID id) {
		try {
			Optional<Ngo> ngo = ngoService.getNgosById(id);
			if (ngo.isPresent()){
				DataResponse<Ngo> dataResponse = new DataResponse<>(ngo.get(), "Operation Completed");
				return new ResponseEntity<>(dataResponse, HttpStatus.OK);
			}
			else{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets and Sends all Ngos based on their Email in the database as a resource to the web.
	 * If there's no Ngos matching the email specified in the database (null), then it will return a HTTP error code.
	 * 
	 * @return a ResponseEntity Object, which contains a List of Ngos and the appropriate HTTP Response Code or only a HTTP Response Code to the web.
	 * @throws Exception Any exceptions in operation will return a HTTP error code.
	 */
	@GetMapping("/ngo/{email}")
	public ResponseEntity< DataResponse<List<Ngo>> > getNgoByEmail(@PathVariable("email") String email){
		try {
			List<Ngo> ngos = ngoService.getNgosByEmail(email);
			if (ngos == null)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
			DataResponse<List<Ngo>> dataResponse = new DataResponse<>(ngos, "Operation Completed");
			return new ResponseEntity<>(dataResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets and Sends all Ngos based on their Name in the database as a resource to the web.
	 * If there's no Ngos matching the name specified in the database (null), then it will return a HTTP error code.
	 * 
	 * @return a ResponseEntity Object, which contains a List of Ngos and the appropriate HTTP Response Code or only a HTTP Response Code to the web.
	 * @throws Exception Any exceptions in operation will return a HTTP error code.
	 */
	@GetMapping("/ngo/{name}")
	public ResponseEntity< DataResponse<List<Ngo>> > getNgoByName(@PathVariable("name") String name){
		try {
			List<Ngo> ngos = ngoService.getNgosByName(name);
			if (ngos == null)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
			DataResponse<List<Ngo>> dataResponse = new DataResponse<>(ngos, "Operation Completed");
			return new ResponseEntity<>(dataResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
