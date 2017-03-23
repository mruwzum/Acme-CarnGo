package services;

import com.sun.org.apache.xpath.internal.operations.Bool;
import domain.Actor;
import domain.Administrator;
import domain.Comment;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;

import java.util.Collection;

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



    public Double averageOfOfferPerConsumer2(){
        return administratorRepository.averageOfOfferPerConsumer2();
    }

    public Double averageOfRequestPerConsumer2(){
        return administratorRepository.averageOfRequestPerConsumer2();
    }

    public Double averageNumberOfApplicationsPerOffers(){
        return administratorRepository.averageNumberOfApplicationsPerOffers();
    }

    public Double averageNumberOfApplicationsPerRequests2(){
        return administratorRepository.averageNumberOfApplicationsPerRequests2();
    }

    public Customer customerWithMoreApplicationsAccepted(){
        return administratorRepository.customerWithMoreApplicationsAccepted();
    }

    public Customer customerWithMoreApplicationsDenied(){
        return administratorRepository.customerWithMoreApplicationsDenied();
    }

    public Double averageNumberOfCommentPerActor(){
        return administratorRepository.averageNumberOfCommentPerActor();
    }

    public Double averageNumberOfCommentPerOffer(){
        return administratorRepository.averageNumberOfCommentPerOffer();
    }

    public Double averageNumberOfCommentPerRequest(){
        return administratorRepository.averageNumberOfCommentPerRequest();
    }

    public Double averageNumberOfSMesasgePerActor(){
        return administratorRepository.averageNumberOfSMesasgePerActor();
    }

    public Double maxNumberOfSMesasgePerActor(){
        return administratorRepository.maxNumberOfSMesasgePerActor();
    }

    public Double minNumberOfSMesasgePerActor(){
        return administratorRepository.minNumberOfSMesasgePerActor();
    }

    public Double averageNumberOfRMesasgePerActor(){
        return administratorRepository.averageNumberOfRMesasgePerActor();
    }

    public Double maxNumberOfRMesasgePerActor(){
        return administratorRepository.maxNumberOfRMesasgePerActor();
    }

    public Double minNumberOfRMesasgePerActor(){
        return administratorRepository.minNumberOfRMesasgePerActor();
    }

    public Actor actorWhoHaveSentMoreMessage(){
        return administratorRepository.actorWhoHaveSentMoreMessage();
    }

    public Actor actorWhoHaveGetMoreMessage(){
        return administratorRepository.actorWhoHaveGetMoreMessage();
    }

}
