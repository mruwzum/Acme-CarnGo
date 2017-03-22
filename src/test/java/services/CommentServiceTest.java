package services;

import domain.Comment;
import domain.Customer;
import domain.Offer;
import domain.Request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import utilities.AbstractTest;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OfferService offerService;

    // System under test ------------------------------------------------------

    @Autowired
    private CommentService commentService;

    // Tests ------------------------------------------------------------------

    @Test
    public void postPositiveTest() throws Exception {
        authenticate("customer1");
        List<Customer> requestList = new ArrayList<>(customerService.findAll());
        Customer request = requestList.get(0);
        Comment comment = commentService.create();
        comment.setObjectiveId(request.getId());
        comment.setText("sgfdfdsfsadg");
        comment.setNumberOfStars(2);
        comment.setTitle("asdfasdf");
        commentService.post(comment);
        Assert.isTrue(!request.getComment().isEmpty());
        authenticate(null);
    }
    @Test
    public void postPositiveTestAdmin() throws Exception {
        authenticate("administrator1");
        List<Customer> requestList = new ArrayList<>(customerService.findAll());
        Customer request = requestList.get(0);
        Comment comment = commentService.create();
        comment.setObjectiveId(request.getId());
        comment.setText("sgfdfg");
        comment.setNumberOfStars(0);
        comment.setTitle("342343");
        commentService.post(comment);
        Assert.isTrue(!request.getComment().isEmpty());
        authenticate(null);
    }
    @Test(expected = NullPointerException.class)
    public void postNegativeTest() throws Exception{
        authenticate("customer1");
        List<Customer> requestList = new ArrayList<>(customerService.findAll());
        Customer request = requestList.get(0);
        Comment comment = commentService.create();
        commentService.post(comment);
        authenticate(null);
    }

    @Test(expected = NullPointerException.class)
    public void postNegativeTestAdmin() throws Exception{
        authenticate("administrator1");
        List<Customer> requestList = new ArrayList<>(customerService.findAll());
        Customer request = requestList.get(0);
        Comment comment = commentService.create();
        commentService.post(comment);
        authenticate(null);
    }
    @Test
    public void postToOfferPositiveTest() throws Exception {
        authenticate("customer1");
        List<Offer> requestList = new ArrayList<>(offerService.findAll());
        Offer request = requestList.get(0);
        Comment comment = commentService.create();
        comment.setObjectiveId(request.getId());
        comment.setText("sgfdfg");
        comment.setNumberOfStars(2);
        comment.setTitle("sdafdfas");
        commentService.postToOffer(comment);
        Assert.isTrue(!request.getComment().isEmpty());
        authenticate(null);
    }
    @Test
    public void postToOfferPositiveTestAdmin() throws Exception {
        authenticate("administrator1");
        List<Offer> requestList = new ArrayList<>(offerService.findAll());
        Offer request = requestList.get(0);
        Comment comment = commentService.create();
        comment.setObjectiveId(request.getId());
        comment.setText("sgfdfg");
        comment.setNumberOfStars(0);
        comment.setTitle("sfadfasdfsd");
        commentService.postToOffer(comment);
        Assert.isTrue(!request.getComment().isEmpty());
        authenticate(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void postToOfferNegativeTest() throws Exception {
        authenticate("customer1");
        List<Offer> requestList = new ArrayList<>(offerService.findAll());
        Offer request = requestList.get(0);
        Comment comment = commentService.create();
        commentService.postToOffer(comment);
        authenticate(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void postToOfferNegativeTestAdministrator() throws Exception {
        authenticate("administrator1");
        List<Offer> requestList = new ArrayList<>(offerService.findAll());
        Offer request = requestList.get(0);
        Comment comment = commentService.create();
        commentService.postToOffer(comment);
        authenticate(null);
    }


    @Test
    public void postToRequestPositiveTest() throws Exception {
        authenticate("customer1");
        List<Request> requestList = new ArrayList<>(requestService.findAll());
        Request request = requestList.get(0);
        Comment comment = commentService.create();
        comment.setObjectiveId(request.getId());
        comment.setText("sgfdfg");
        comment.setNumberOfStars(0);
        comment.setTitle("342343");
        commentService.postToRequest(comment);
        Assert.isTrue(!request.getComment().isEmpty());
        authenticate(null);
    }
    @Test
    public void postToRequestPositiveTestAdministrator() throws Exception {
        authenticate("administrator1");
        List<Request> requestList = new ArrayList<>(requestService.findAll());
        Request request = requestList.get(0);
        Comment comment = commentService.create();
        comment.setObjectiveId(request.getId());
        comment.setText("sgfdfg");
        comment.setNumberOfStars(0);
        comment.setTitle("342343");
        commentService.postToRequest(comment);
        Assert.isTrue(!request.getComment().isEmpty());
        authenticate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void postToRequestNegativeTest() throws Exception{
        authenticate("customer3");
        List<Request> requestList = new ArrayList<>(requestService.findAll());
        Request request = requestList.get(1);
        Comment comment = commentService.create();
        commentService.postToRequest(comment);
        authenticate(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void postToRequestNegativeTestAdministrator() throws Exception{
        authenticate("administrator1");
        List<Request> requestList = new ArrayList<>(requestService.findAll());
        Comment comment = commentService.create();
        commentService.postToRequest(comment);
        authenticate(null);
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