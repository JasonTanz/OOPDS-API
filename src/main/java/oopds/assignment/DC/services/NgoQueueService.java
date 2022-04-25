package oopds.assignment.DC.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oopds.assignment.DC.DAOs.NgoQueueDAO;
import oopds.assignment.DC.models.NgoQueue;


/**
 * A Service Class to be used by the Spring API to do certain operations based
 * on the method called.
 * This service class contains the operations that involves the NgoQueue.
 */
@Service
public class NgoQueueService {
    private final NgoQueueDAO ngoQueueDAO;


    /**
	 * The constructor for NgoQueueService class based on the parameters passed.
	 *
	 * @param ngoQueueDAO The Data Access Object for the NgoQueue model.
	 */
    @Autowired
    public NgoQueueService(NgoQueueDAO ngoQueueDAO) {
        this.ngoQueueDAO = ngoQueueDAO;
    }


    /**
	 * Save and Returns the NgoQueue Entity.
	 *
	 * @param ngoQueue The NgoQueue object to be saved.
	 * @return The saved NgoQueue object.
	 */
    public NgoQueue save(NgoQueue ngoQueue) {
        return ngoQueueDAO.save(ngoQueue);
    }


    /**
	 * Gets and Returns all of the Ngo Queue objects stored in the database.
	 *
	 * @return A list of all Ngo Queue objects in the database.
	 */
    public List<NgoQueue> findAll() {
        return ngoQueueDAO.findAll();
    }


    /**
	 * Gets and Returns the NgoQueue Entity, based on id.
	 *
	 * @param id The id of the NgoQueue to be searched for.
	 * @return An NgoQueue Object that matches the id.
	 */
    public NgoQueue findById(UUID id) {
        return ngoQueueDAO.findById(id).get();
    }
}
