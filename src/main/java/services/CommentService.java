package services;

import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CommentRepository;

import java.util.Collection;

/**
 * Created by daviddelatorre on 12/3/17.
 */
@Service
@Transactional
public class CommentService {

    // Constructors--------------------------------------------------------------------------------------

    public CommentService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private CommentRepository commentRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Comment create() {
        Comment res;
        res = new Comment();
        return res;
    }

    public Collection<Comment> findAll() {
        Collection<Comment> res;
        res = commentRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Comment findOne(int Comment) {
        Comment res;
        res = commentRepository.findOne(Comment);
        Assert.notNull(res);
        return res;
    }

    public Comment save(Comment a) {
        Assert.notNull(a);
        Comment res;
        res = commentRepository.save(a);
        return res;
    }

    public void delete(Comment a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        commentRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}