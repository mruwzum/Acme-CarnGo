package services;

import domain.Customer;
import domain.Offer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import utilities.AbstractTest;

import javax.validation.ConstraintViolationException;
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

public class CustomerServiceTest extends AbstractTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OfferService offerService;


    // System under test ------------------------------------------------------

    // Tests ------------------------------------------------------------------

    // USE CASE THAT INVOLVES A LISTING & EDITION REQ.


    //01 - EMPTY LIST
    /**
     * Description: An actor who is authenticated as a customer must be able to: Post an offer in which he or she advertises that he?s going to move from
     a place to another place and would like to share his or
     her car with someone else.
     * Precondition: The user is a customer with an empty offer list.
     * Return: TRUE
     * Postcondition: The offer list is shown empty.
     */
    @Test
    public void OfferListEmpty(){
        authenticate("customer5");
        List<Offer> offerList = new ArrayList<>();
        Customer customer = customerService.findByPrincipal();
        offerList.addAll(customer.getOffers());
        Assert.isTrue(offerList.isEmpty());
      //  System.out.println(offerList);
        authenticate(null);
        customerService.flush();

    }


    //02 - REGULAR CUSTOMER WITH A OFFER
    /**
     * Description: An actor who is authenticated as a customer must be able to: Post an offer in which he or she advertises that he?s going to move from
     a place to another place and would like to share his or
     her car with someone else.
     * Precondition: The user is a customer with an offer list with one item.
     * Return: TRUE
     * Postcondition: The offer list is shown with one single item.
     */
    @Test
    public void OfferListOne(){
        authenticate("customer1");
        List<Offer> offerList = new ArrayList<>();
        Customer customer = customerService.findByPrincipal();
        offerList.addAll(customer.getOffers());
        Assert.isTrue(offerList.size()==1);
      //  System.out.println(offerList);
        authenticate(null);
        customerService.flush();
    }

    //03 - ALL OFFERS
    /**
     * Description: An actor who is authenticated as a customer must be able to: Post an offer in which he or she advertises that he?s going to move from
     a place to another place and would like to share his or
     her car with someone else.
     * Precondition: The user is a customer with an offer list with more than one item.
     * Return: TRUE
     * Postcondition: The offer list is shown with multiple items.
     */
    @Test
    public void OfferListFull(){
        authenticate("customer2");
        List<Offer> offerList = new ArrayList<>();
        Customer customer = customerService.findByPrincipal();
        offerList.addAll(customer.getOffers());
        //System.out.println(offerList);
        Assert.isTrue(offerList.size()>=2);
        authenticate(null);
        customerService.flush();
    }

//04 - CREATE OK
    /**
     * Description: An actor who is authenticated as a customer must be able to: Post an offer in which he or she advertises that he?s going to move from
     a place to another place and would like to share his or
     her car with someone else.
     * Precondition: The user is a customer and the offer data is correct.
     * Return: TRUE
     * Postcondition: A new offer is created.
     */
    @Test
    public void OfferCreateOk(){
        authenticate("customer1");
        Offer offer = offerService.create();
        offer.setOwnerO(customerService.findByPrincipal());
        offer.setTitle("Viaje a granada");
        offer.setDescription("nos vamos para Gran�");
        offer.setOriginAddress("Sevilla");
        offer.setDestinationAddress("Granada");
        offer.setTripDate(new Date(System.currentTimeMillis() - 1000));
        offer.setCoordXValue(200.0);
        offer.setCoordYValue(-213.0);
        offer.setCoordXL("s".charAt(0));
        offer.setCoordYL("n".charAt(0));
        customerService.findByPrincipal().getOffers().add(offer);
        Assert.isTrue(customerService.findByPrincipal().getOffers().size() ==2);
        authenticate(null);
        customerService.flush();
    }
//05 - CREATE  NO OK
    /**
     * Description: An actor who is authenticated as a customer must be able to: Post an offer in which he or she advertises that he?s going to move from
     a place to another place and would like to share his or
     her car with someone else.
     * Precondition: The user is a customer and the offer data that is not correct.
     * Return: FALSE
     * Postcondition: A new offer is not created.
     */

    @Test(expected = ConstraintViolationException.class)
    public void OfferCreateNotOk(){
        authenticate("customer1");
        Offer offer = offerService.create();
        offer.setOwnerO(customerService.findByPrincipal());
        offer.setTitle("");
        offer.setDescription("nos vamos para Gran�");
        offer.setOriginAddress("Sevilla");
        offer.setDestinationAddress("Granada");
        offer.setTripDate(new Date(System.currentTimeMillis() - 1000));
        offer.setCoordXValue(200.0);
        offer.setCoordYValue(-213.0);
        offer.setCoordXL("s".charAt(0));
        offer.setCoordYL("n".charAt(0));
        customerService.findByPrincipal().getOffers().add(offer);
        authenticate(null);
        customerService.flush();
    }

    //06 - CREATE  NO OK
    /**
     * Description: An actor who is authenticated as a customer must be able to: Post an offer in which he or she advertises that he?s going to move from
     a place to another place and would like to share his or
     her car with someone else.
     * Precondition: The user is an administrator and the offer data is correct.
     * Return: FALSE
     * Postcondition: A new offer is not created.
     */

    @Test(expected = IllegalArgumentException.class)
    public void OfferCreateNotAdministratorOk(){
        authenticate("administrator1");
        Offer offer = offerService.create();
        offer.setOwnerO(customerService.findByPrincipal());
        offer.setTitle("adsf");
        offer.setDescription("nos vamos para Gran�");
        offer.setOriginAddress("Sevilla");
        offer.setDestinationAddress("Granada");
        offer.setTripDate(new Date(System.currentTimeMillis() - 1000));
        offer.setCoordXValue(200.0);
        offer.setCoordYValue(-213.0);
        offer.setCoordXL("s".charAt(0));
        offer.setCoordYL("n".charAt(0));
        customerService.findByPrincipal().getOffers().add(offer);
        authenticate(null);
        customerService.flush();
    }

    //07 - EDIT OK
    /**
     * Description: An actor who is authenticated as a customer must be able to: Post an offer in which he or she advertises that he?s going to move from
     a place to another place and would like to share his or
     her car with someone else.
     * Precondition: The user is a customer and the offer that is trying to edit belongs to his offers. All the new offer data is correct.
     * Return: TRUE
     * Postcondition: A old offer is modified.
     */

    @Test
    public void OfferEditOk(){
        authenticate("customer1");
        List<Offer> offers = new ArrayList<>(customerService.findByPrincipal().getOffers());
        Offer offer = offers.get(0);
        Offer offer1 = offer;
//        System.out.println("ANTIGUOS VALORES --------");
//        System.out.println(offer.getTitle());
//        System.out.println(offer.getDescription());
        offer.setTitle("adsf");
        offer.setDescription("nos vamos para Gran�");
        offer.setOriginAddress("Sevilla");
        offer.setDestinationAddress("Granada");
        offer.setTripDate(new Date(System.currentTimeMillis() - 1000));
        offer.setCoordXValue(200.0);
        offer.setCoordYValue(-213.0);
        offer.setCoordXL("s".charAt(0));
        offer.setCoordYL("n".charAt(0));
//        System.out.println("NUEVOS VALORES --------");
//        System.out.println(offer.getTitle());
//        System.out.println(offer.getDescription());
        Assert.isTrue(offer.getTitle().equals(offer1.getTitle())||offer.getDescription().equals(offer1.getDescription())||offer.getDestinationAddress().equals(offer1.getDestinationAddress())||offer.getOriginAddress().equals(offer1.getOriginAddress()));
        authenticate(null);
        customerService.flush();
    }


//08 - EDIT NOT OK
    /**
     * Description: An actor who is authenticated as a customer must be able to: Post an offer in which he or she advertises that he?s going to move from
     a place to another place and would like to share his or
     her car with someone else.
     * Precondition: The user is a customer and the offer that is trying to edit belongs to his offers. All the new offer data is not correct.
     * Return: FALSE
     * Postcondition: A old offer is not modified.
     */

    @Test(expected = ConstraintViolationException.class)
    public void OfferEditNotOk(){
        authenticate("customer1");
        List<Offer> offers = new ArrayList<>(customerService.findByPrincipal().getOffers());
        Offer offer = offers.get(0);
        Offer offer1 = offer;
        offer.setTitle("");
        offer.setDescription("nos vamos para Gran�");
        offer.setOriginAddress("Sevilla");
        offer.setDestinationAddress("Granada");
        offer.setTripDate(new Date(System.currentTimeMillis() - 1000));
        offer.setCoordXValue(200.0);
        offer.setCoordYValue(-213.0);
        offer.setCoordXL("s".charAt(0));
        offer.setCoordYL("n".charAt(0));
        Assert.isTrue(offer.getTitle().equals(offer1.getTitle())||offer.getDescription().equals(offer1.getDescription())||offer.getDestinationAddress().equals(offer1.getDestinationAddress())||offer.getOriginAddress().equals(offer1.getOriginAddress()));
        authenticate(null);
        customerService.flush();
    }


//09 -  ADMIN EDIT NOT OK
    /**
     * Description: An actor who is authenticated as a customer must be able to: Post an offer in which he or she advertises that he?s going to move from
     a place to another place and would like to share his or
     her car with someone else.
     * Precondition: The user is an administrator and the offer that is trying to edit belongs to his offers. All the new offer data is correct.
     * Return: FALSE
     * Postcondition: A old offer is not modified.
     */

    @Test(expected = IllegalArgumentException.class)
    public void OfferAdminEditNotOk(){
        authenticate("administrator1");
        List<Offer> offers = new ArrayList<>(customerService.findByPrincipal().getOffers());
        Offer offer = offers.get(0);
        Offer offer1 = offer;
        offer.setTitle("dsfasdf");
        offer.setDescription("nos vamos para Gran�");
        offer.setOriginAddress("Sevilla");
        offer.setDestinationAddress("Granada");
        offer.setTripDate(new Date(System.currentTimeMillis() - 1000));
        offer.setCoordXValue(200.0);
        offer.setCoordYValue(-213.0);
        offer.setCoordXL("s".charAt(0));
        offer.setCoordYL("n".charAt(0));
        Assert.isTrue(offer.getTitle().equals(offer1.getTitle())||offer.getDescription().equals(offer1.getDescription())||offer.getDestinationAddress().equals(offer1.getDestinationAddress())||offer.getOriginAddress().equals(offer1.getOriginAddress()));
        authenticate(null);
        customerService.flush();
    }



//10 -  NON REGISTERED USER
    /**
     * Description: An actor who is authenticated as a customer must be able to: Post an offer in which he or she advertises that he?s going to move from
     a place to another place and would like to share his or
     her car with someone else.
     * Precondition: The user not registered.
     * Return: FALSE
     * Postcondition: The offer list is not shown.
     */

    @Test(expected = IllegalArgumentException.class)
    public void OfferAnonymous(){
        authenticate(null);
        List<Offer> offers = new ArrayList<>(offerService.findAll());
        authenticate(null);
        customerService.flush();
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