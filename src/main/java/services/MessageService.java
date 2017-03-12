package services;

import domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.MessageRepository;

import java.util.Collection;

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

}
