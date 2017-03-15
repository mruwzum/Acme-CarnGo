package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CustomerRepository;
import security.LoginService;
import security.UserAccount;

import java.util.Collection;

/**
 * Created by daviddelatorre on 12/3/17.
 */
@Service
@Transactional
public class CustomerService {
    // Constructors--------------------------------------------------------------------------------------

    public CustomerService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private RequestService requestService;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Customer create() {
        Customer res;
        res = new Customer();
        return res;
    }

    public Collection<Customer> findAll() {
        Collection<Customer> res;
        res = customerRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Customer findOne(int Customer) {
        Customer res;
        res = customerRepository.findOne(Customer);
        Assert.notNull(res);
        return res;
    }

    public Customer save(Customer a) {
        Assert.notNull(a);
        Customer res;
        res = customerRepository.save(a);
        return res;
    }

    public void delete(Customer a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        customerRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------
    public Customer findByPrincipal() {
        Customer result;
        UserAccount userAccount;

        userAccount = LoginService.getPrincipal();
        Assert.notNull(userAccount);
        result = findByUserAccount(userAccount);
        Assert.notNull(result);

        return result;
    }

    private Customer findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);

        Customer result;

        result = customerRepository.findByUserAccountId(userAccount.getId());

        return result;
    }


    public Boolean acceptApplication(Application application){

        Boolean res = false;
        if(!application.getRequestStatus().equals(RequestStatus.ACCEPTED)){
            application.setRequestStatus(RequestStatus.ACCEPTED);
            applicationService.save(application);
                    res= true;
        }

        return res;
    }


    public Boolean deniedApplication(Application application){

        Boolean res = false;
        if(!application.getRequestStatus().equals(RequestStatus.DENIED)){
            application.setRequestStatus(RequestStatus.DENIED);
            applicationService.save(application);
            res= true;
        }

        return res;
    }
    public Boolean banOffer(Offer offer){
        Boolean res = false;
        if(offer.isBanned()){
            res = false;
        }else if(!offer.isBanned()){
            offer.setBanned(true);
            offerService.save(offer);
            res = true;
        }
        return res;
    }
    public Boolean banRequest(Request request){
        Boolean res = false;
        if(request.isBanned()){
            res = false;
        }else if(!request.isBanned()){
            request.setBanned(true);
            requestService.save(request);
            res = true;
        }
        return res;
    }
}
