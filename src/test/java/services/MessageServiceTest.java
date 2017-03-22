package services;

import domain.Actor;
import domain.Administrator;
import domain.Customer;
import domain.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import utilities.AbstractTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

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

    // Tests ------------------------------------------------------------------

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