package repositories;

import domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by daviddelatorre on 10/3/17.
 */
@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {


    @Query("select c from Administrator c where c.userAccount.id = ?1")
    Administrator findByUserAccountId(int userAccountId);

    //Ratio of offers versus requests
    @Query("select c from Offer c")
    Collection<Offer> allOffersInTheSystem2();
    @Query("select c from Request c")
    Collection<Request> allRequestInTheSystem2();

    //Average number of offers and request per customer..........?
    @Query("select avg(c.offers.size) from Customer c group by c")
    Collection<Double> averageOfOfferPerConsumer2();
    @Query("select avg(c.requests.size) from Customer c group by c")
    Collection<Double> averageOfRequestPerConsumer2();

    //Average number of applications per offer or request........?
    @Query("select avg(c.applications.size) from Offer c group by c")
    Collection<Double> averageNumberOfApplicationsPerOffers();
    @Query("select avg(c.applications.size) from Request c group by c")
    Collection<Double> averageNumberOfApplicationsPerRequests2();

    //Esto no va a funcionar ni de coña

    //The customer who has more applications accepted.
    @Query("select max(c) from Customer c join c.offers o join o.applications p join c.requests r join r.applications l  where p.requestStatus = 0 and l.requestStatus=0")
    Customer customerWithMoreApplicationsAccepted();
    //The customer who has more applications denied.
    @Query("select max(c) from Customer c join c.offers o join o.applications p join c.requests r join r.applications l  where p.requestStatus = 1 and l.requestStatus=1")
    Customer customerWithMoreApplicationsDenied();

    //Average number of comments per actor, offer, or request.
    @Query("select avg(c.comment.size) from Actor c group by c")
    Collection<Double> averageNumberOfCommentPerActor();
    @Query("select avg(c.comment.size) from Offer c group by c")
    Collection<Double> averageNumberOfCommentPerOffer();
    @Query("select avg(c.comment.size) from Request c group by c")
    Collection<Double> averageNumberOfCommentPerRequest();


    //Average number of comments posted by administrators and customers.


    //The actors who have posted ±10% the average number of comments per actor.

    //The minimum, the average, and the maximum number of messages sent per actor.

    //The minimum, the average, and the maximum number of messages received per actor.

    //The actors who have sent more messages.

    //The actors who have got more messages.

}
