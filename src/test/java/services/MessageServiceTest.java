package services;

import domain.Actor;
import domain.Message;
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
public class MessageServiceTest extends AbstractTest {

    @Autowired
    private MessageService messageService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private CustomerService customerService;


    @Test
    public void send() throws Exception {
        authenticate("customer1");
        Message message = messageService.create();
        message.setSubject("sgdgasdg");
        message.setBody("fasfsd");
        message.setReceiverEmail("Customer2Email@gmail.com");
        System.out.println(message.getReceiverEmail());
        System.out.println(customerService.findByPrincipal().getEmail());
        message.setSenderEmail(customerService.findByPrincipal().getEmail());
        messageService.save(message);
        Actor c = actorService.findActorByEmail(message.getReceiverEmail());

        messageService.send(message);
        System.out.println(message);
        System.out.println(c.getRecivedMessages());
        authenticate(null);
    }

}