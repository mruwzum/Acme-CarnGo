package services;

import domain.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.RequestRepository;

import java.util.Collection;

/**
 * Created by daviddelatorre on 12/3/17.
 */
@Service
@Transactional
public class RequestService {

    // Constructors--------------------------------------------------------------------------------------

    public RequestService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private RequestRepository requestRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Request create() {
        Request res;
        res = new Request();
        return res;
    }

    public Collection<Request> findAll() {
        Collection<Request> res;
        res = requestRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Request findOne(int Request) {
        Request res;
        res = requestRepository.findOne(Request);
        Assert.notNull(res);
        return res;
    }

    public Request save(Request a) {
        Assert.notNull(a);
        Request res;
        res = requestRepository.save(a);
        return res;
    }

    public void delete(Request a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        requestRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}
