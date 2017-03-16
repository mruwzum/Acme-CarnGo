package services;

import domain.Application;
import domain.Offer;
import domain.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.OfferRepository;

import java.util.Collection;

/**
 * Created by daviddelatorre on 12/3/17.
 */
@Service
@Transactional
public class OfferService {

    // Constructors--------------------------------------------------------------------------------------

    public OfferService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private CustomerService customerService;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Offer create() {
        Offer res;
        res = new Offer();
        return res;
    }

    public Collection<Offer> findAll() {
        Collection<Offer> res;
        res = offerRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Offer findOne(int Offer) {
        Offer res;
        res = offerRepository.findOne(Offer);
        Assert.notNull(res);
        return res;
    }

    public Offer save(Offer a) {
        Assert.notNull(a);
        Offer res;
        res = offerRepository.save(a);
        return res;
    }

    public void delete(Offer a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        offerRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

    public Boolean applyOffer(Offer offer, Application application){

        Boolean res =  false;
        Assert.notNull(offer);

        try {
            application.setRequestStatus(RequestStatus.PENDING);
            offer.getApplications().add(application);
            application.setOwner(customerService.findByPrincipal());
            applicationService.save(application);
            res=true;
        }catch (Exception e){

        }

        return res;
    }


}
