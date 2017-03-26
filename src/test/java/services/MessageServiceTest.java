package services;

import domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import utilities.AbstractTest;

import java.util.*;

/**
 * Created by mruwzum on 17/3/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class MessageServiceTest extends AbstractTest {

    // System under test ------------------------------------------------------

    @Autowired
    private MessageService messageService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private RequestService requestService;
    @Autowired
    private OfferService offerService;

    // Tests ------------------------------------------------------------------


    @Before
    public void setUp(){
        authenticate("administrator1");
        List<Customer> customers = new ArrayList<>(customerService.findAll());
        Actor receiver = customers.get(3);
        Actor sender = actorService.findByPrincipal();
        Message m = messageService.create();
        m.setSender(sender);
        m.setReceiver(receiver);
        m.setBody("BODYZ");
        m.setSubject("SUBJECT");
        m.setSentDate(new Date(System.currentTimeMillis() - 1000));
        Collection<String> att = new HashSet<>();
        att.add("dsf");
        m.setAttachments(att);
        messageService.save(m);
        messageService.send(m);
        unauthenticate();
        authenticate("administrator2");
        List<Customer> customers1 = new ArrayList<>(customerService.findAll());
        Actor receiver1 = customers1.get(3);
        Actor sender1 = actorService.findByPrincipal();
        Message m1 = messageService.create();
        m1.setSender(sender1);
        m1.setReceiver(receiver1);
        m1.setBody("BODYZ");
        m1.setSubject("SUBJECT");
        m1.setSentDate(new Date(System.currentTimeMillis() - 1000));
        Collection<String> att1 = new HashSet<>();
        att1.add("dsf");
        m.setAttachments(att1);
        messageService.save(m1);
        messageService.send(m);
        unauthenticate();
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
        unauthenticate();

        authenticate("customer2");
        Request request2 = requestService.create();
        request2.setOwnerR(customerService.findByPrincipal());
        request2.setTitle("Viaje a granada");
        request2.setDescription("nos vamos para Graná");
        request2.setOriginAddress("Sevilla");
        request2.setDestinationAddress("Granada");
        request2.setTripDate(new Date(System.currentTimeMillis() - 1000));
        request2.setCoordXValue(200.0);
        request2.setCoordYValue(-213.0);
        request2.setCoordXL("s".charAt(0));
        request2.setCoordYL("n".charAt(0));
        customerService.findByPrincipal().getRequests().add(request2);
        request2.setOwnerR(customerService.findByPrincipal());



        Offer offer2 = offerService.create();
        offer2.setOwnerO(customerService.findByPrincipal());
        offer2.setTitle("Viaje a granada");
        offer2.setDescription("nos vamos para Graná");
        offer2.setOriginAddress("Sevilla");
        offer2.setDestinationAddress("Granada");
        offer2.setTripDate(new Date(System.currentTimeMillis() - 1000));
        offer2.setCoordXValue(200.0);
        offer2.setCoordYValue(-213.0);
        offer2.setCoordXL("s".charAt(0));
        offer2.setCoordYL("n".charAt(0));
        customerService.findByPrincipal().getOffers().add(offer2);
        unauthenticate();

        authenticate("customer5");

        Collection<Request> requests = customerService.findByPrincipal().getRequests();
        customerService.findByPrincipal().getRequests().removeAll(requests);
        Collection<Offer> offers = customerService.findByPrincipal().getOffers();
        customerService.findByPrincipal().getOffers().removeAll(offers);

        Request request3 = requestService.create();
        request3.setOwnerR(customerService.findByPrincipal());
        request3.setTitle("Viaje a granada");
        request3.setDescription("nos vamos para Graná");
        request3.setOriginAddress("Sevilla");
        request3.setDestinationAddress("Granada");
        request3.setTripDate(new Date(System.currentTimeMillis() - 1000));
        request3.setCoordXValue(200.0);
        request3.setCoordYValue(-213.0);
        request3.setCoordXL("s".charAt(0));
        request3.setCoordYL("n".charAt(0));
        customerService.findByPrincipal().getRequests().add(request3);
        request3.setOwnerR(customerService.findByPrincipal());



        Offer offer3 = offerService.create();
        offer3.setOwnerO(customerService.findByPrincipal());
        offer3.setTitle("Viaje a granada");
        offer3.setDescription("nos vamos para Graná");
        offer3.setOriginAddress("Sevilla");
        offer3.setDestinationAddress("Granada");
        offer3.setTripDate(new Date(System.currentTimeMillis() - 1000));
        offer3.setCoordXValue(200.0);
        offer3.setCoordYValue(-213.0);
        offer3.setCoordXL("s".charAt(0));
        offer3.setCoordYL("n".charAt(0));
        customerService.findByPrincipal().getOffers().add(offer3);
        unauthenticate();
    }



    @Test
    public void sendFromCustomerToCustomerOk(){
        authenticate("customer1");
        List<Customer> customers = new ArrayList<>(customerService.findAll());
        Actor receiver = customers.get(3);
        Actor sender = customerService.findByPrincipal();
        Message m = messageService.create();
        m.setSender(sender);
        m.setReceiver(receiver);
        m.setBody("BODYZ");
        m.setSubject("SUBJECT");
        m.setSentDate(new Date(System.currentTimeMillis() - 1000));
        Collection<String> att = new HashSet<>();
        att.add("dsf");
        m.setAttachments(att);
        messageService.save(m);
        messageService.send(m);
        authenticate(null);
        messageService.flush();
    }
    @Test
    public void sendFromCustomerToAdminOk(){
        authenticate("customer1");
        List<Administrator> admins = new ArrayList<>(administratorService.findAll());
        Actor receiver = admins.get(3);
        Actor sender = customerService.findByPrincipal();
        Message m = messageService.create();
        m.setSender(sender);
        m.setReceiver(receiver);
        m.setBody("BODY");
        m.setSubject("SUBJECT");
        m.setSentDate(new Date(System.currentTimeMillis() - 1000));
        Collection<String> att = new HashSet<>();
        att.add("dsf");
        m.setAttachments(att);
        messageService.send(m);
        authenticate(null);
        messageService.flush();
    }
    @Test
    public void sendFromAdminToCustomerOk(){
        authenticate("administrator1");
        List<Customer> customers = new ArrayList<>(customerService.findAll());
        Actor receiver = customers.get(3);
        Actor sender = administratorService.findByPrincipal();
        Message m = messageService.create();
        m.setSender(sender);
        m.setReceiver(receiver);
        m.setBody("BODIES");
        m.setSubject("SUBJECTS");
        m.setSentDate(new Date(System.currentTimeMillis() - 1000));
        Collection<String> att = new HashSet<>();
        att.add("dsfs");
        m.setAttachments(att);
        messageService.send(m);
        authenticate(null);
        messageService.flush();
    }
    @Test
    public void sendFromAdminToAdminOk(){
        authenticate("administrator1");
        List<Administrator> admins = new ArrayList<>(administratorService.findAll());
        Actor receiver = admins.get(3);
        Actor sender = administratorService.findByPrincipal();
        Message m = messageService.create();
        m.setSender(sender);
        m.setReceiver(receiver);
        m.setBody("BODYY");
        m.setSubject("SUBJECTT");
        m.setSentDate(new Date(System.currentTimeMillis() - 1000));
        Collection<String> att = new HashSet<>();
        att.add("dsfff");
        m.setAttachments(att);
        messageService.send(m);
        authenticate(null);
        messageService.flush();
    }
    @Test(expected = IllegalArgumentException.class)
    public void sendFromCustomerToCustomerNoOk(){
        authenticate("customer1");
        List<Customer> customers = new ArrayList<>(customerService.findAll());
        Actor receiver = customers.get(3);
        Actor sender = customerService.findByPrincipal();
        Message m = messageService.create();
        messageService.send(m);
        authenticate(null);
        messageService.flush();
    }
    @Test(expected = IllegalArgumentException.class)
    public void sendFromCustomerToAdminNoOk(){
        authenticate("customer1");
        List<Administrator> admins = new ArrayList<>(administratorService.findAll());
        Actor receiver = admins.get(3);
        Actor sender = customerService.findByPrincipal();
        Message m = messageService.create();
        Collection<String> att = new HashSet<>();
        att.add("dsf");
        m.setAttachments(att);
        messageService.send(m);
        authenticate(null);
        messageService.flush();
    }
    @Test(expected = IllegalArgumentException.class)
    public void sendFromAdminToCustomerNoOk(){
        authenticate("administrator1");
        List<Customer> customers = new ArrayList<>(customerService.findAll());
        Actor receiver = customers.get(3);
        Actor sender = administratorService.findByPrincipal();
        Message m = messageService.create();
        messageService.send(m);
        authenticate(null);
        messageService.flush();
    }
    @Test(expected = IllegalArgumentException.class)
    public void sendFromAdminToAdminNoOk(){
        authenticate("administrator1");
        List<Administrator> admins = new ArrayList<>(administratorService.findAll());
        Actor receiver = admins.get(3);
        Actor sender = administratorService.findByPrincipal();
        Message m = messageService.create();
        messageService.send(m);
        authenticate(null);
        messageService.flush();
    }
    @Test
    public void replyAMessage() {
        authenticate("customer1");
        Actor a = actorService.findByPrincipal();
        List<Message> messages0 = new ArrayList<>(a.getRecivedMessages());
        Message m = messages0.get(0);
        Assert.notNull(m);
        messageService.reply(m);
        authenticate(null);
        messageService.flush();
    }
    @Test
    public void forwardAMessage(){
        authenticate("customer1");
        Actor a = actorService.findByPrincipal();
        List<Message> messages = new ArrayList<>(a.getRecivedMessages());
        Message m = messages.get(0);
        messageService.forward(m);
        authenticate(null);
        messageService.flush();
    }
    @Test
    public void replyAMessageAdmin(){
        authenticate("administrator2");
        Actor a = actorService.findByPrincipal();
        List<Message> messages0 = new ArrayList<>(a.getRecivedMessages());
        Message m = messages0.get(0);
        messageService.reply(m);
        authenticate(null);
        messageService.flush();
    }
    @Test
    public void forwardAMessageAdmin(){
        authenticate("administrator2");
        Actor a = actorService.findByPrincipal();
        List<Message> messages = new ArrayList<>(a.getRecivedMessages());
        Message m = messages.get(0);
        messageService.forward(m);
        authenticate(null);
        messageService.flush();
    }

    @Test
    public void deleteMessageCustomer(){
        authenticate("customer1");
        Actor a = actorService.findByPrincipal();
        List<Message> messages0 = new ArrayList<>(a.getRecivedMessages());
        Message m = messages0.get(0);
        Assert.notNull(m);
        messageService.delete(m);
        authenticate(null);
        messageService.flush();
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteMessageAdminWithoutIt(){
        authenticate("administrator1");
        Actor a = actorService.findByPrincipal();
        List<Message> messages = new ArrayList<>(a.getRecivedMessages());
        Message m = messages.get(0);
        Assert.notNull(m);
        messageService.delete(m);
        authenticate(null);
        messageService.flush();
    }
    @Test
    public void deleteMessageCustomerOk(){
        authenticate("customer2");
        Actor a = actorService.findByPrincipal();
        List<Message> messages0 = new ArrayList<>(a.getRecivedMessages());
        Message m = messages0.get(0);
        Assert.notNull(m);
        messageService.delete(m);
        authenticate(null);
        messageService.flush();
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