package controllers;


import domain.Actor;
import domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.MessageService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {

	//Services ----------------------------------------------------------------

	@Autowired
	private MessageService messageService;
	@Autowired
    private ActorService actorService;




	//Constructors----------------------------------------------

	public MessageController(){
		super();
	}
	
    protected static ModelAndView createEditModelAndView(Message message) {
        ModelAndView result;

        result= createEditModelAndView(message, null);

        return result;
    }

	
	//Create Method -----------------------------------------------------------
	
    protected static ModelAndView createEditModelAndView(Message message1, String message) {
        ModelAndView result;

        result= new ModelAndView("message/edit");
        result.addObject("message1", message1);
        result.addObject("message", message);

        return result;

    }


     
	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView messageList() {

		ModelAndView result;
		Collection<Message> messages;

        messages = messageService.findAll();
		result = new ModelAndView("message/list");
		result.addObject("messages", messages);
		result.addObject("requestURI","message/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;

		Message message1 = messageService.create();

        result = createEditModelAndView(message1);

		return result;

		}
	
	// Ancillary methods ------------------------------------------------


    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int messageId){
        ModelAndView result;
        Message message;

        message= messageService.findOne(messageId);
        Assert.notNull(message);
        result= createEditModelAndView(message);

        return result;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Message message1, BindingResult binding){
        ModelAndView result;
        if (binding.hasErrors()) {
            result= createEditModelAndView(message1);
        }else{
            try{

                //Set the rest of values
                message1.setSenderEmail(actorService.findByPrincipal().getEmail());
                message1.setSentDate(new Date(System.currentTimeMillis()-1000));


                //Associate message
                Actor sender = actorService.findByPrincipal();
                sender.getSendMessages().add(message1);
                Assert.notNull(sender);
                Actor recipient = actorService.findActorByEmail(message1.getReceiverEmail());
                recipient.getRecivedMessages().add(message1);
                Assert.notNull(recipient);

                //Save Message
                messageService.save(message1);

                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(message1, "message.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Message message){
        ModelAndView result;
        try{
            messageService.delete(message);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(message, "message.commit.error");
        }

        return result;
    }

}
