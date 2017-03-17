package services;

import domain.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.TripRepository;

import java.util.Collection;

/**
 * Created by daviddelatorre on 12/3/17.
 */
@Service
@Transactional
public class TripService {

    // Constructors--------------------------------------------------------------------------------------

    public TripService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private TripRepository tripRepository;



    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------


    public Collection<Trip> findAll() {
        Collection<Trip> res;
        res = tripRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Trip findOne(int Request) {
        Trip res;
        res = tripRepository.findOne(Request);
        Assert.notNull(res);
        return res;
    }

    public Trip save(Trip a) {
        Assert.notNull(a);
        Trip res;
        res = tripRepository.save(a);
        return res;
    }

    public void delete(Trip a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        tripRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

//    public Trip getTrip(Offer offer){
//        Trip res;
//        tripRepository.findOne(offer.)
//    }

}
