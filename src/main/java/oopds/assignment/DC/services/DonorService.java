package oopds.assignment.DC.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import oopds.assignment.DC.DAOs.DonorDAO;
import oopds.assignment.DC.models.Donor;

@Service
public class DonorService implements UserDetailsService {
    private final DonorDAO donorDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("email");
        System.out.println(email);
        UserDetails donor = donorDAO.findByEmail(email);
        System.out.println(donor);
        if (donor == null) {
            throw new UsernameNotFoundException("Not found!");
        }

        // return (UserDetails) donor;
        return donor;
    }

    @Autowired
    public DonorService(DonorDAO donorDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.donorDAO = donorDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Donor findByEmail(String email) {
        return donorDAO.findByEmail(email);
    }

    public Donor addNewDonor(Donor donor) {

        donor.setPassword(bCryptPasswordEncoder.encode(donor.getPassword()));
        return donorDAO.save(donor);
    }

}
