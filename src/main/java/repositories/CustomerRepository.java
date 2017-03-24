package repositories;

import domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by daviddelatorre on 10/3/17.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    @Query("select c from Customer c where c.userAccount.id = ?1")
    Customer findByUserAccountId(int userAccountId);
}
