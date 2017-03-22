package services;

import domain.Actor;
import domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.MessageRepository;

import java.util.Collection;
import java.util.Date;

/**
 * Created by daviddelatorre on 12/3/17.
 */
@Service
@Transactional
public class MessageService {


    // Constructors--------------------------------------------------------------------------------------

    public MessageService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private MessageRepository messageRepository;

@Autowired
private  ActorService actorService;
    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Message create() {
        Message res;
        res = new Message();
        return res;
    }

    public Collection<Message> findAll() {
        Collection<Message> res;
        res = messageRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Message findOne(int Message) {
        Message res;
        res = messageRepository.findOne(Message);
        Assert.notNull(res);
        return res;
    }

    public Message save(Message a) {
        Assert.notNull(a);
        Message res;
        res = messageRepository.save(a);
        return res;
    }

    public void delete(Message a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        messageRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------
    public void send(Message message){


        //Set the rest of values
        message.setSentDate(new Date(System.currentTimeMillis()-1000));


        //Associate message
        Actor sender = actorService.findByPrincipal();
        Assert.notNull(sender);
        sender.getSendMessages().add(message);
        Actor recipient = message.getReceiver();
        Assert.notNull(recipient);
        recipient.getRecivedMessages().add(message);

    }
public Message reply(Message message){
    Actor receiver = message.getSender();
    Actor sender = message.getReceiver();

    Message message1 = create();
    message1.setReceiver(receiver);
    message1.setSender(sender);
    return message1;
}

public Message forward(Message message){
    Actor receiver = message.getSender();
    Actor sender = message.getReceiver();

    Message message1 = create();
    message1.setReceiver(receiver);
    message1.setSender(sender);
    message1.setBody(message.getBody());

    return  message1;
}
    public void flush(){
        messageRepository.flush();
    }
}
