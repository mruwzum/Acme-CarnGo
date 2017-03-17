package services;

import domain.Comment;
import domain.Request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import utilities.AbstractTest;

/**
 * Created by mruwzum on 17/3/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class CommentServiceTest extends AbstractTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private RequestService requestService;

    @Test
    public void post() throws Exception {

    }

    @Test
    public void postToOffer() throws Exception {

    }

    @Test
    public void postToRequest() throws Exception {
        authenticate("customer1");
            Request request = requestService.findOne(492);

            Comment comment = commentService.create();

            comment.setObjectiveId(request.getId());
            comment.setText("sgfdfg");
            comment.setNumberOfStars(0);
            comment.setTitle("342343");
            Comment comment1 = commentService.save(comment);
        System.out.println(comment1);

        commentService.postToRequest(comment);
            System.out.println(comment.getOwner());
        System.out.println(request.getComment());

        System.out.println(comment.getObjectiveId());
            authenticate(null);
    }



}