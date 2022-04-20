package oopds.assignment.DC.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oopds.assignment.DC.DAOs.NgoQueueDAO;
import oopds.assignment.DC.models.NgoQueue;

@Service
public class NgoQueueService {
    private final NgoQueueDAO ngoQueueDAO;

    @Autowired
    public NgoQueueService(NgoQueueDAO ngoQueueDAO) {
        this.ngoQueueDAO = ngoQueueDAO;
    }

    public NgoQueue addNewQueue(NgoQueue ngoQueue) {
        return ngoQueueDAO.save(ngoQueue);
    }
}
