package oopds.assignment.DC.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import oopds.assignment.DC.DAOs.NgoDAO;
import oopds.assignment.DC.models.Ngo;

@Service
public class NgoService {
    private NgoDAO ngoDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public NgoService(NgoDAO ngoDAO) {
        this.ngoDAO = ngoDAO;
    }

    public Ngo findByEmail(String email) {
        return ngoDAO.findByEmail(email);
    }

    public Ngo addNewNgo(Ngo ngo) {
        ngo.setPassword(bCryptPasswordEncoder.encode(ngo.getPassword()));
        return ngoDAO.save(ngo);
    }
}
