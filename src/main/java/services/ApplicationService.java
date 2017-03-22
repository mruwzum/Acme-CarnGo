package services;

import domain.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.ApplicationRepository;

import java.util.Collection;

/**
 * Created by daviddelatorre on 12/3/17.
 */
@Service
@Transactional
public class ApplicationService {
    // Constructors--------------------------------------------------------------------------------------

    public ApplicationService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private ApplicationRepository applicationRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Application create() {
        Application res;
        res = new Application();
        return res;
    }

    public Collection<Application> findAll() {
        Collection<Application> res;
        res = applicationRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Application findOne(int Application) {
        Application res;
        res = applicationRepository.findOne(Application);
        Assert.notNull(res);
        return res;
    }

    public Application save(Application a) {
        Assert.notNull(a);
        Application res;
        res = applicationRepository.save(a);
        return res;
    }

    public void delete(Application a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        applicationRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------
    public void flush(){
        applicationRepository.flush();
    }

}
