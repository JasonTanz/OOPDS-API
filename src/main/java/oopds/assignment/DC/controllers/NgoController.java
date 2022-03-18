package oopds.assignment.DC.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dc")
public class NgoController {
	NgoService ngoService;
	NgoDAO ngoDAO;

	@GetMapping("/ngo")
	public ResponseEntity< DataResponse<List<Ngo>> > getAllNgos() {
		try {
			DataResponse<List<Ngo>> dataResponse = new DataResponse<>(ngoService.getNgos(), "Operation Completed");
			return new ResponseEntity<>(dataResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

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
