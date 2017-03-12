package services;

import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CustomerRepository;

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



}
