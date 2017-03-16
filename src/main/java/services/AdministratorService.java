package services;

import com.sun.org.apache.xpath.internal.operations.Bool;
import domain.Administrator;
import domain.Comment;
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

}
