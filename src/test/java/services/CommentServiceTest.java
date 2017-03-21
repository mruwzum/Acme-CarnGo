package services;

import domain.Comment;
import domain.Request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
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
    private RequestService requestService;


    // System under test ------------------------------------------------------

    @Autowired
    private CommentService commentService;

    // Tests ------------------------------------------------------------------

    @Test
    public void postPositiveTest() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void postNegativeTest() throws Exception {

    }


    @Test
    public void postToOfferPositiveTest() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void postToOfferNegativeTest() throws Exception {

    }



    @Test
    public void postToRequestPositiveTest() throws Exception {
        authenticate("customer1");
        Request request = requestService.findOne(509);

        Comment comment = commentService.create();

        comment.setObjectiveId(request.getId());
        comment.setText("sgfdfg");
        comment.setNumberOfStars(0);
        comment.setTitle("342343");
        // Comment comment1 = commentService.save(comment);
        System.out.println(comment);

        commentService.postToRequest(comment);
        //System.out.println(comment.getOwner());
        System.out.println(request.getComment());

        // System.out.println(comment.getObjectiveId());
        authenticate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void postToRequestNegativeTest() throws Exception {

    }


    // The following are fictitious test cases that are intended to check that
    // JUnit works well in this project.  Just righ-click this class and run
    // it using JUnit.

    @Test
    public void samplePositiveTest() {
        Assert.isTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sampleNegativeTest() {
        Assert.isTrue(false);
    }

    // Ancillary methods ------------------------------------------------------





}