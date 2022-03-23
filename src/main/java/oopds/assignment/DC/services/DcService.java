package oopds.assignment.DC.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import oopds.assignment.DC.DAOs.DcDAO;
import oopds.assignment.DC.models.Dc;

@Service
public class DcService {
    @Autowired
    private DcDAO dcDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Dc getById(UUID id) {
        return dcDAO.getById(id);
    }

    public Dc getByEmail(String email) {
        return dcDAO.findByEmail(email);
    }

    public Dc getByName(String name) {
        return dcDAO.findByName(name);
    }

    public Dc addNewNgo(Dc dc) {
        dc.setPassword(bCryptPasswordEncoder.encode(dc.getPassword()));
        return dcDAO.save(dc);
    }

    public Dc findByEmail(String email) {
        return dcDAO.findByEmail(email);
    }
}