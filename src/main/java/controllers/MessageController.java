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
import java.util.HashSet;

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
		Collection<Message> sendMessages = new HashSet<>();
        Collection<Message> recivedMessages = new HashSet<>();
        Actor a = actorService.findByPrincipal();
        sendMessages.addAll(a.getSendMessages());
        recivedMessages.addAll(a.getRecivedMessages());
        Assert.notNull(a);
		result = new ModelAndView("message/list");
		result.addObject("sendMessages", sendMessages);
        result.addObject("recivedMessages", recivedMessages);

		result.addObject("requestURI","message/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@RequestParam int id) {

        ModelAndView result;
        Actor receiver = actorService.findOne(id);
        Actor sender = actorService.findByPrincipal();

		Message message1 = messageService.create();
        message1.setReceiver(receiver);
        message1.setSender(sender);

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

    @RequestMapping(value = "/reply", method = RequestMethod.GET)
    public ModelAndView reply(@RequestParam int messageId){

        ModelAndView res;

        Message messageSelected = messageService.findOne(messageId);

      Message message1 = messageService.reply(messageSelected);

        res = createEditModelAndView(message1);


        return res;
    }

    @RequestMapping(value = "/forward", method = RequestMethod.GET)
    public ModelAndView forward(@RequestParam int messageId){

        ModelAndView res;

        Message messageSelected = messageService.findOne(messageId);

       Message message1 = messageService.forward(messageSelected);

        res = createEditModelAndView(message1);


        return res;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Message message1, BindingResult binding){
        ModelAndView result;
        if (binding.hasErrors()) {
            result= createEditModelAndView(message1);
        }else{
            try{
                messageService.send(message1);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(message1, "message.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public ModelAndView delete(@RequestParam int messageId){
        ModelAndView result;
        Message message = messageService.findOne(messageId);
        try{
            messageService.delete(message);
            result=new ModelAndView("redirect:list.do");
       }catch(Throwable oops){
            result= createEditModelAndView(message, "message.commit.error");
        }

        return result;
    }

}
