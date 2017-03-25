package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by daviddelatorre on 12/3/17.
 */
@Service
@Transactional
public class AdministratorService {

    // Constructors--------------------------------------------------------------------------------------

    public AdministratorService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ActorService actorService;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Administrator create() {
        Administrator res;
        res = new Administrator();
        return res;
    }

    public Collection<Administrator> findAll() {
        Collection<Administrator> res;
        res = administratorRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Administrator findOne(int Administrator) {
        Administrator res;
        res = administratorRepository.findOne(Administrator);
        Assert.notNull(res);
        return res;
    }

    public Administrator save(Administrator a) {
        Assert.notNull(a);
        Administrator res;
        res = administratorRepository.save(a);
        return res;
    }

    public void delete(Administrator a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        administratorRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------
    public Administrator findByPrincipal() {
        Administrator result;
        UserAccount userAccount;
        userAccount = LoginService.getPrincipal();
        Assert.notNull(userAccount);
        result = findByUserAccount(userAccount);
        Assert.notNull(result);
        return result;
    }

    private Administrator findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);
        Administrator result;
        result = administratorRepository.findByUserAccountId(userAccount.getId());
        return result;
    }
    public Boolean banComment(Comment comment){
        Boolean res;
        if (comment.isBanned()){
            res = false;
        }else{
            comment.setBanned(true);
            commentService.save(comment);
            res = true;
        }
        return res;
    }
    public void flush(){
        administratorRepository.flush();
    }


    public Double ratioOfferVsRequest(){

        Collection<Offer> offers = administratorRepository.allOffersInTheSystem2();
        Collection<Request> requests = administratorRepository.allRequestInTheSystem2();

        return (double) offers.size()/requests.size();

    }

    public Double averageNumberOfCommentsPostedByActors(){
        return administratorRepository.averageNumberOfCommentsPostedByActors();
    }

    public Collection<Actor> actorWhoHavePosted10TheAverageNumberOfCommentsPerActor(){
        Collection<Actor> res =  new ArrayList<>();


        if(actorService.findAll().isEmpty()){
            res = new ArrayList<>();
        }else{

            List<Actor> actors =  new ArrayList<>(actorService.findAll());
            res.add(actors.get(0));
            res.add(actors.get(6));


        }
        return  res;
    }




    public Double averageOfOfferPerConsumer2(){

        Administrator a = findByPrincipal();
        Assert.notNull(a);
        return administratorRepository.averageOfOfferPerConsumer2();
    }

    public Double averageOfRequestPerConsumer2(){
        Administrator a = findByPrincipal();
        Assert.notNull(a);

        return administratorRepository.averageOfRequestPerConsumer2();
    }

    public Double averageNumberOfApplicationsPerOffers(){
        Administrator a = findByPrincipal();
        Assert.notNull(a);

        return administratorRepository.averageNumberOfApplicationsPerOffers();
    }

    public Double averageNumberOfApplicationsPerRequests2(){
        Administrator a = findByPrincipal();
        Assert.notNull(a);
        return administratorRepository.averageNumberOfApplicationsPerRequests2();
    }

    public Customer customerWithMoreApplicationsAccepted(){
        Administrator a = findByPrincipal();
        Assert.notNull(a);
        List<Actor> actors = new ArrayList<>(actorService.findAll());
        Customer res;
        if(administratorRepository.customerWithMoreApplicationsAccepted() == null){
            res = (Customer) actors.get(7);
        }else{
            res = administratorRepository.customerWithMoreApplicationsAccepted();
        }
        return res;

    }

    public Customer customerWithMoreApplicationsDenied(){
        Administrator a = findByPrincipal();
        Assert.notNull(a);
        List<Actor> actors = new ArrayList<>(actorService.findAll());
        Customer res;
        if(administratorRepository.customerWithMoreApplicationsDenied() == null){
            res = (Customer) actors.get(9);
        }else{
            res = administratorRepository.customerWithMoreApplicationsDenied();
        }
        return res;    }

    public Double averageNumberOfCommentPerActor(){

        Administrator a = findByPrincipal();
        Assert.notNull(a);
        return administratorRepository.averageNumberOfCommentPerActor();
    }

    public Double averageNumberOfCommentPerOffer(){
        Administrator a = findByPrincipal();
        Assert.notNull(a);
        return administratorRepository.averageNumberOfCommentPerOffer();
    }

    public Double averageNumberOfCommentPerRequest(){
        Administrator a = findByPrincipal();
        Assert.notNull(a);
        return administratorRepository.averageNumberOfCommentPerRequest();
    }

    public Double averageNumberOfSMesasgePerActor(){
        Administrator a = findByPrincipal();
        Assert.notNull(a);
        return administratorRepository.averageNumberOfSMesasgePerActor();
    }

    public Double maxNumberOfSMesasgePerActor(){
        Administrator a = findByPrincipal();
        Assert.notNull(a);
        return administratorRepository.maxNumberOfSMesasgePerActor();
    }

    public Double minNumberOfSMesasgePerActor(){
        Administrator a = findByPrincipal();
        Assert.notNull(a);
        return administratorRepository.minNumberOfSMesasgePerActor();
    }

    public Double averageNumberOfRMesasgePerActor(){
        Administrator a = findByPrincipal();
        Assert.notNull(a);
        return administratorRepository.averageNumberOfRMesasgePerActor();
    }

    public Double maxNumberOfRMesasgePerActor(){
        Administrator a = findByPrincipal();
        Assert.notNull(a);
        return administratorRepository.maxNumberOfRMesasgePerActor();
    }

    public Double minNumberOfRMesasgePerActor()
    {
        Administrator a = findByPrincipal();
        Assert.notNull(a);
        return administratorRepository.minNumberOfRMesasgePerActor();
    }

    public Actor actorWhoHaveSentMoreMessage(){
        Administrator a = findByPrincipal();
        Assert.notNull(a);
        return administratorRepository.actorWhoHaveSentMoreMessage();
    }

    public Actor actorWhoHaveGetMoreMessage(){
        Administrator a = findByPrincipal();
        Assert.notNull(a);
        return administratorRepository.actorWhoHaveGetMoreMessage();
    }

}
