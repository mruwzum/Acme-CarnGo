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


    //Average number of comments posted by administrators and customers.
    @Query("select avg(commentsPosted) from Actor ")
    Double averageNumberOfCommentsPostedByActors();

    //The actors who have posted ±10% the average number of comments per actor.




    //Average number of offers and request per customer..........?
    @Query("select avg(offers.size) from Customer ")
    Double averageOfOfferPerConsumer2();
    @Query("select avg(requests.size) from Customer")
    Double averageOfRequestPerConsumer2();

    //Average number of applications per offer or request........?
    @Query("select avg(applications.size) from Offer")
    Double averageNumberOfApplicationsPerOffers();
    @Query("select avg(applications.size) from Request")
    Double averageNumberOfApplicationsPerRequests2();


    //The customer who has more applications accepted.
    @Query("select max(c) from Customer c join c.offers o join o.applications p join c.requests r join r.applications l  where p.requestStatus = 0 and l.requestStatus=0")
    Customer customerWithMoreApplicationsAccepted();
    //The customer who has more applications denied.
    @Query("select max(c) from Customer c join c.offers o join o.applications p join c.requests r join r.applications l  where p.requestStatus = 1 and l.requestStatus=1")
    Customer customerWithMoreApplicationsDenied();

    //Average number of comments per actor, offer, or request.
    @Query("select avg(comment.size) from Actor")
    Double averageNumberOfCommentPerActor();
    @Query("select avg(comment.size) from Offer")
    Double averageNumberOfCommentPerOffer();
    @Query("select avg(comment.size) from Request")
    Double averageNumberOfCommentPerRequest();







    //The minimum, the average, and the maximum number of messages sent per actor.

    @Query("select avg(sendMessages.size) from Actor")
    Double averageNumberOfSMesasgePerActor();
    @Query("select max(sendMessages.size) from Actor")
    Double maxNumberOfSMesasgePerActor();
    @Query("select min(sendMessages.size) from Actor")
    Double minNumberOfSMesasgePerActor();



    //The minimum, the average, and the maximum number of messages received per actor.

    @Query("select avg(recivedMessages.size) from Actor")
    Double averageNumberOfRMesasgePerActor();
    @Query("select max(recivedMessages.size) from Actor")
    Double maxNumberOfRMesasgePerActor();
    @Query("select min(recivedMessages.size) from Actor")
    Double minNumberOfRMesasgePerActor();


    //The actors who have sent more messages.
    @Query("select a from Actor a order by max (a.sendMessages.size) asc")
    Actor actorWhoHaveSentMoreMessage();

    //The actors who have got more messages.
    @Query("select a from Actor a order by max (a.recivedMessages.size) asc")
    Actor actorWhoHaveGetMoreMessage();
}
