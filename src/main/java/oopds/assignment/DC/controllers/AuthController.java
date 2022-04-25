package oopds.assignment.DC.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import oopds.assignment.DC.models.DataResponse;
import oopds.assignment.DC.models.Dc;
import oopds.assignment.DC.models.Donor;
import oopds.assignment.DC.models.Ngo;
import oopds.assignment.DC.services.DcService;
import oopds.assignment.DC.services.DonorService;
import oopds.assignment.DC.services.NgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A Controller is a Class that controls the operations of the web service by
 * creating a REST API.
 * This Controller is responsible for Controlling operations for Authentication
 * Operations such as signup and new account.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final DonorService donorService;
	private final NgoService ngoService;
	private final DcService dcService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

	/**
	 * This is a constructor for the AuthController controller with the specified
	 * values
	 *
	 * @param donorService          The service class for Donors.
	 * @param ngoService            The service class for Ngos.
	 * @param dcService             The service class for DCs.
	 * @param bCryptPasswordEncoder The password encoder to encrypt passwords.
	 */
	@Autowired
	public AuthController(
			DonorService donorService,
			NgoService ngoService,
			DcService dcService,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.donorService = donorService;
		this.ngoService = ngoService;
		this.dcService = dcService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	/**
	 * This is a HTTP Post method to add new donors into the database.
	 *
	 * @param donor The new donor to be added.
	 * @return A ResponseEntity Object that signals the HTTP to do certain
	 *         operations (HTTP Status Codes).
	 */
	@PostMapping("/donor/signup")
	public ResponseEntity<DataResponse<?>> addNewDonor(@RequestBody Donor donor) {
		Donor donorExists = donorService.findByEmail(donor.getEmail());
		Donor donorNameExists = donorService.findByName(donor.getName());
		if (donorExists == null) {
			if (donorNameExists == null) {
				donorService.addNewDonor(donor);

				String accessToken = JWT
						.create()
						.withClaim("role", "Donor")
						.withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
						.sign(algorithm);

				String refreshToken = JWT
						.create()
						.withClaim("role", "Donor")
						.withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 60 * 1000))
						.sign(algorithm);
				Map<String, Object> data = new HashMap<>();
				data.put("accessToken", accessToken);
				data.put("refreshToken", refreshToken);
				donor.setPassword("");
				data.put("user", donor);
				return new ResponseEntity<>(new DataResponse<>(data), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(
						new DataResponse<>("Donor name already exist"),
						HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(
					new DataResponse<>("Donor email already exists"),
					HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * This is a HTTP Post method to add new ngos into the database.
	 *
	 * @param ngo The new ngo to be added.
	 * @return a ResponseEntity Object that signals the HTTP to do certain
	 *         operations (HTTP Status Codes).
	 */
	@PostMapping("/ngo/signup")
	public ResponseEntity<DataResponse<?>> addNewNGO(@RequestBody Ngo ngo) {
		Ngo ngoExists = ngoService.findByEmail(ngo.getEmail());
		Ngo ngoNameExists = ngoService.findByName(ngo.getName());

		if (ngoExists == null) {
			if (ngoNameExists == null) {
				ngoService.addNewNgo(ngo);
				String accessToken = JWT
						.create()
						.withClaim("role", "Ngo")
						.withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
						.sign(algorithm);

				String refreshToken = JWT
						.create()
						.withClaim("role", "Ngo")
						.withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 60 * 1000))
						.sign(algorithm);
				Map<String, Object> data = new HashMap<>();
				data.put("accessToken", accessToken);
				data.put("refreshToken", refreshToken);
				ngo.setPassword("");
				data.put("user", ngo);
				return new ResponseEntity<>(new DataResponse<>(data), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(
						new DataResponse<>("Ngo name already exist"),
						HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(
					new DataResponse<>("Ngo email already exists"),
					HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * This is a HTTP Post method to check and authenticate donor login.
	 *
	 * @param donor The donor passed to check for credentials.
	 * @return a ResponseEntity Object that signals the HTTP to do certain
	 *         operations (HTTP Status Codes).
	 */
	@PostMapping("/donor/login")
	public ResponseEntity<DataResponse<?>> donorLogin(@RequestBody Donor donor) {
		try {
			Donor donorExist = donorService.findByEmail(donor.getEmail());

			if (donorExist != null) {
				Boolean passwordMatch = bCryptPasswordEncoder.matches(
						donor.getPassword(),
						donorExist.getPassword());
				if (passwordMatch) {
					String accessToken = JWT
							.create()
							.withClaim("role", "Donor")
							.withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
							.sign(algorithm);

					String refreshToken = JWT
							.create()
							.withClaim("role", "Donor")
							.withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 60 * 1000))
							.sign(algorithm);
					Map<String, Object> data = new HashMap<>();
					data.put("accessToken", accessToken);
					data.put("refreshToken", refreshToken);
					donorExist.setPassword("");
					data.put("user", donorExist);
					return new ResponseEntity<>(new DataResponse<>(data), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(
							new DataResponse<>("Password not match"),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new ResponseEntity<>(
						new DataResponse<>("Donor not found"),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(
					new DataResponse<>("Some errors occured"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This is a HTTP Post method to check and authenticate ngo login.
	 *
	 * @param ngo The ngo passed to check for credentials.
	 * @return a ResponseEntity Object that signals the HTTP to do certain
	 *         operations (HTTP Status Codes).
	 */
	@PostMapping("/ngo/login")
	public ResponseEntity<?> ngoLogin(@RequestBody Ngo ngo) {
		try {
			Ngo ngoExist = ngoService.findByEmail(ngo.getEmail());

			if (ngoExist != null) {
				Boolean passwordMatch = bCryptPasswordEncoder.matches(
						ngo.getPassword(),
						ngoExist.getPassword());
				if (passwordMatch) {
					String accessToken = JWT
							.create()
							.withClaim("role", "Ngo")
							.withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
							.sign(algorithm);

					String refreshToken = JWT
							.create()
							.withClaim("role", "Ngo")
							.withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 60 * 1000))
							.sign(algorithm);
					Map<String, Object> data = new HashMap<>();
					data.put("accessToken", accessToken);
					data.put("refreshToken", refreshToken);
					ngoExist.setPassword("");
					data.put("user", ngoExist);
					return new ResponseEntity<>(new DataResponse<>(data), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(
							new DataResponse<>("Password not match"),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new ResponseEntity<>(
						new DataResponse<>("Ngo not found"),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(
					new DataResponse<>("Some errors occured"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This is a HTTP Post method to check and authenticate dc login.
	 *
	 * @param dc The dc passed to check for credentials.
	 * @return a ResponseEntity Object that signals the HTTP to do certain
	 *         operations (HTTP Status Codes).
	 */
	@CrossOrigin
	@PostMapping("/dc/login")
	public ResponseEntity<?> dcLogin(@RequestBody Dc dc) {
		try {
			Dc dcExists = dcService.findByEmail(dc.getEmail());

			if (dc.getEmail() == "dc@gmail.com" & dc.getPassword() == "dc@gmail.com") {
				String accessToken = JWT
						.create()
						.withClaim("role", "Dc")
						.withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
						.sign(algorithm);

				String refreshToken = JWT
						.create()
						.withClaim("role", "Dc")
						.withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 60 * 1000))
						.sign(algorithm);
				Map<String, Object> data = new HashMap<>();
				data.put("accessToken", accessToken);
				data.put("refreshToken", refreshToken);
				dcExists.setPassword("");
				data.put("user", dcExists);
				return new ResponseEntity<>(new DataResponse<>(data), HttpStatus.OK);
			}

			if (dcExists != null) {
				Boolean passwordMatch = bCryptPasswordEncoder.matches(
						dc.getPassword(),
						dcExists.getPassword());
				if (passwordMatch) {
					String accessToken = JWT
							.create()
							.withClaim("role", "Dc")
							.withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
							.sign(algorithm);

					String refreshToken = JWT
							.create()
							.withClaim("role", "Dc")
							.withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 60 * 1000))
							.sign(algorithm);
					Map<String, Object> data = new HashMap<>();
					data.put("accessToken", accessToken);
					data.put("refreshToken", refreshToken);
					dcExists.setPassword("");
					data.put("user", dcExists);
					return new ResponseEntity<>(new DataResponse<>(data), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(
							new DataResponse<>("Password not match."),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new ResponseEntity<>(
						new DataResponse<>("Dc not found"),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(
					new DataResponse<>("Some errors occured"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
