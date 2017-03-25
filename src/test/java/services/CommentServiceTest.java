package services;

import domain.Comment;
import domain.Customer;
import domain.Offer;
import domain.Request;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import utilities.AbstractTest;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private AdministratorService administratorService;
    // System under test ------------------------------------------------------

    @Autowired
    private CommentService commentService;

    // Tests ------------------------------------------------------------------
    @Before
    public void setUp() {
        authenticate("customer1");
        Request request = requestService.create();
        request.setOwnerR(customerService.findByPrincipal());
        request.setTitle("Viaje a granada");
        request.setDescription("nos vamos para Graná");
        request.setOriginAddress("Sevilla");
        request.setDestinationAddress("Granada");
        request.setTripDate(new Date(System.currentTimeMillis() - 1000));
        request.setCoordXValue(200.0);
        request.setCoordYValue(-213.0);
        request.setCoordXL("s".charAt(0));
        request.setCoordYL("n".charAt(0));
        customerService.findByPrincipal().getRequests().add(request);
        request.setOwnerR(customerService.findByPrincipal());



        Offer offer = offerService.create();
        offer.setOwnerO(customerService.findByPrincipal());
        offer.setTitle("Viaje a granada");
        offer.setDescription("nos vamos para Graná");
        offer.setOriginAddress("Sevilla");
        offer.setDestinationAddress("Granada");
        offer.setTripDate(new Date(System.currentTimeMillis() - 1000));
        offer.setCoordXValue(200.0);
        offer.setCoordYValue(-213.0);
        offer.setCoordXL("s".charAt(0));
        offer.setCoordYL("n".charAt(0));
        customerService.findByPrincipal().getOffers().add(offer);

        Comment comment = commentService.create();
        comment.setNumberOfStars(2);
        comment.setOwner(customerService.findByPrincipal());
        List<Customer> requestList = new ArrayList<>(customerService.findAll());
        Customer obje = requestList.get(0);
        comment.setObjectiveId(obje.getId());
        comment.setText("sgfdfg");
        comment.setTitle("342343");
        commentService.post(comment);
        unauthenticate();


        authenticate("customer4");

        Comment comment1 = commentService.create();
        comment1.setNumberOfStars(2);
        comment1.setOwner(customerService.findByPrincipal());
        List<Customer> requestList1 = new ArrayList<>(customerService.findAll());
        Customer obje1 = requestList1.get(0);
        comment1.setObjectiveId(obje1.getId());
        comment1.setText("sgfdfg");
        comment1.setTitle("342343");
        commentService.post(comment1);
        unauthenticate();

        authenticate("administrator1");


        Comment comment12 = commentService.create();
        comment12.setNumberOfStars(2);
        comment12.setOwner(administratorService.findByPrincipal());
        List<Customer> requestList12 = new ArrayList<>(customerService.findAll());
        Customer obje12 = requestList12.get(0);
        comment12.setObjectiveId(obje12.getId());
        comment12.setText("sgfdfg");
        comment12.setTitle("342343");
        commentService.post(comment12);
        unauthenticate();
    }




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
        commentService.flush();
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
        commentService.flush();
    }
    @Test(expected = NullPointerException.class)
    public void postNegativeTest() throws Exception{
        authenticate("customer1");
        List<Customer> requestList = new ArrayList<>(customerService.findAll());
        Customer request = requestList.get(0);
        Comment comment = commentService.create();
        commentService.post(comment);
        authenticate(null);
        commentService.flush();
    }

    @Test(expected = NullPointerException.class)
    public void postNegativeTestAdmin() throws Exception{
        authenticate("administrator1");
        List<Customer> requestList = new ArrayList<>(customerService.findAll());
        Customer request = requestList.get(0);
        Comment comment = commentService.create();
        commentService.post(comment);
        authenticate(null);
        commentService.flush();
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
        commentService.flush();
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
        commentService.flush();
    }
    @Test(expected = IllegalArgumentException.class)
    public void postToOfferNegativeTest() throws Exception {
        authenticate("customer1");
        List<Offer> requestList = new ArrayList<>(offerService.findAll());
        Offer request = requestList.get(0);
        Comment comment = commentService.create();
        commentService.postToOffer(comment);
        authenticate(null);
        commentService.flush();
    }
    @Test(expected = IllegalArgumentException.class)
    public void postToOfferNegativeTestAdministrator() throws Exception {
        authenticate("administrator1");
        List<Offer> requestList = new ArrayList<>(offerService.findAll());
        Offer request = requestList.get(0);
        Comment comment = commentService.create();
        commentService.postToOffer(comment);
        authenticate(null);
        commentService.flush();
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
        commentService.flush();
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
        commentService.flush();
    }

    @Test(expected = IllegalArgumentException.class)
    public void postToRequestNegativeTest() throws Exception{
        authenticate("customer3");
        List<Request> requestList = new ArrayList<>(requestService.findAll());
        Request request = requestList.get(1);
        Comment comment = commentService.create();
        commentService.postToRequest(comment);
        authenticate(null);
        commentService.flush();
    }
    @Test(expected = IllegalArgumentException.class)
    public void postToRequestNegativeTestAdministrator() throws Exception{
        authenticate("administrator1");
        List<Request> requestList = new ArrayList<>(requestService.findAll());
        Comment comment = commentService.create();
        commentService.postToRequest(comment);
        authenticate(null);
        commentService.flush();
    }
    @Test
    public void banComment() throws Exception {
        authenticate("administrator1");
        List<Comment> requestList = new ArrayList<>(commentService.findAll());
        Comment comment = requestList.get(0);
        administratorService.banComment(comment);
        Assert.isTrue(comment.isBanned());
        authenticate(null);
        commentService.flush();
    }
    @Test(expected = IllegalArgumentException.class)
    public void banBannedComment() throws Exception {
        authenticate("administrator1");
        List<Comment> requestList = new ArrayList<>(commentService.findAll());
        Comment comment = requestList.get(0);
        administratorService.banComment(comment);
        Assert.isTrue(administratorService.banComment(comment));
        authenticate(null);
        commentService.flush();
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