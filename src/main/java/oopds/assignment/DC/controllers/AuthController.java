
package oopds.assignment.DC.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oopds.assignment.DC.models.Dc;
import oopds.assignment.DC.models.Donor;
import oopds.assignment.DC.models.Ngo;
import oopds.assignment.DC.services.DcService;
import oopds.assignment.DC.services.DonorService;
import oopds.assignment.DC.services.NgoService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final DonorService donorService;
    private final NgoService ngoService;
    private final DcService dcService;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

    @Autowired
    public AuthController(DonorService donorService, NgoService ngoService, DcService dcService,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.donorService = donorService;
        this.ngoService = ngoService;
        this.dcService = dcService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/donor/signup")
    public ResponseEntity<?> addNewDonor(@RequestBody Donor donor) {
        Donor donorExists = donorService.findByEmail(donor.getEmail());

        if (donorExists == null) {
            donorService.addNewDonor(donor);
            String accessToken = JWT.create()
                    .withSubject(donor.getName())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                    // .withClaim("permission_level", user.getAuthorities())
                    .sign(algorithm);

            String refreshToken = JWT.create()
                    .withSubject(donor.getName())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 60 * 1000))
                    .sign(algorithm);
            Map<String, Object> data = new HashMap<>();
            data.put("accessToken", accessToken);
            data.put("refreshToken", refreshToken);
            donor.setPassword("");
            data.put("user", donor);
            return new ResponseEntity<>(data, HttpStatus.OK);

        } else {
            throw new IllegalStateException("Email already taken");
        }

    }

    @PostMapping("/ngo/signup")
    public void addNewNGO(@RequestBody Ngo ngo) {
        Ngo ngoExists = ngoService.findByEmail(ngo.getEmail());

        if (ngoExists == null) {
            ngoService.addNewNgo(ngo);
        } else {
            throw new IllegalStateException("Email already taken");

        }
    }

    
    @PostMapping("/donor/login")
    public ResponseEntity<?> donorLogin(@RequestBody Donor donor) {
        try {

            Donor donorExist = donorService.findByEmail(donor.getEmail());

            if (donorExist != null) {
                Boolean passwordMatch = bCryptPasswordEncoder.matches(donor.getPassword(), donorExist.getPassword());
                if (passwordMatch) {
                    String accessToken = JWT.create()
                            .withSubject(donorExist.getName())
                            .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                            // .withClaim("permission_level", user.getAuthorities())
                            .sign(algorithm);

                    String refreshToken = JWT.create()
                            .withSubject(donorExist.getName())
                            .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 60 * 1000))
                            .sign(algorithm);
                    Map<String, Object> data = new HashMap<>();
                    data.put("accessToken", accessToken);
                    data.put("refreshToken", refreshToken);
                    donorExist.setPassword("");
                    data.put("user", donorExist);
                    return new ResponseEntity<>(data, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @PostMapping("/ngo/login")
    public ResponseEntity<?> ngoLogin(@RequestBody Ngo ngo) {
        try {

            Ngo ngoExist = ngoService.findByEmail(ngo.getEmail());

            if (ngoExist != null) {
                Boolean passwordMatch = bCryptPasswordEncoder.matches(ngo.getPassword(), ngoExist.getPassword());
                if (passwordMatch) {
                    String accessToken = JWT.create()
                            .withSubject(ngoExist.getName())
                            .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                            // .withClaim("permission_level", user.getAuthorities())
                            .sign(algorithm);

                    String refreshToken = JWT.create()
                            .withSubject(ngoExist.getName())
                            .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 60 * 1000))
                            .sign(algorithm);
                    Map<String, Object> data = new HashMap<>();
                    data.put("accessToken", accessToken);
                    data.put("refreshToken", refreshToken);
                    ngoExist.setPassword("");
                    data.put("user", ngoExist);
                    return new ResponseEntity<>(data, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @CrossOrigin
    @PostMapping("/dc/login")
    public ResponseEntity<?> dcLogin(@RequestBody Dc dc) {
        try {

            Dc dcExists = dcService.findByEmail(dc.getEmail());

            if (dcExists != null) {
                Boolean passwordMatch = bCryptPasswordEncoder.matches(dc.getPassword(), dcExists.getPassword());
                if (passwordMatch) {
                    String accessToken = JWT.create()
                            .withSubject(dcExists.getName())
                            .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                            // .withClaim("permission_level", user.getAuthorities())
                            .sign(algorithm);

                    String refreshToken = JWT.create()
                            .withSubject(dcExists.getName())
                            .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 60 * 1000))
                            .sign(algorithm);
                    Map<String, Object> data = new HashMap<>();
                    data.put("accessToken", accessToken);
                    data.put("refreshToken", refreshToken);
                    dcExists.setPassword("");
                    data.put("user", dcExists);
                    return new ResponseEntity<>(data, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

}
