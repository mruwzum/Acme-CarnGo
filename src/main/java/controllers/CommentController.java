package controllers;


import domain.Comment;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CommentService;
import services.CustomerService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private CommentService commentService;
@Autowired
private CustomerService customerService;


	
	//Constructors----------------------------------------------
	
	public CommentController(){
		super();
	}
	
    protected static ModelAndView createEditModelAndView(Comment comment) {
        ModelAndView result;

        result= createEditModelAndView(comment, null);

        return result;
    }
	
	
	//Create Method -----------------------------------------------------------
	
    protected static ModelAndView createEditModelAndView(Comment comment, String message) {
        ModelAndView result;

        result= new ModelAndView("comment/edit");
        result.addObject("comment", comment);
        result.addObject("message", message);

        return result;

    }


     
	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView commentList() {

		ModelAndView result;
		Collection<Comment> comments;

		comments = commentService.findAll();
		result = new ModelAndView("comment/list");
		result.addObject("comments", comments);
		result.addObject("requestURI","comment/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@RequestParam int id) {

        ModelAndView result;

		Comment comment = commentService.create();
		Customer customer = customerService.findOne(id);
        comment.setOwner(customer);

        commentService.save(comment);
        result = createEditModelAndView(comment);

		return result;

		}



    @RequestMapping(value = "/createCustomer", method = RequestMethod.GET)
    public ModelAndView createFromCustomer(@RequestParam int id) {

        ModelAndView result;

        Comment comment = commentService.create();
        comment.setObjectiveId(id);
        Customer customer = customerService.findOne(id);
        List<Comment> commentsAn = new ArrayList<>(customer.getComment());
        commentsAn.add(comment);
        customerService.save(customer);
        result = createEditModelAndView(comment);

        return result;

    }
	// Ancillary methods ------------------------------------------------


    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int commentId){
        ModelAndView result;
        Comment comment;

        comment= commentService.findOne(commentId);
        Assert.notNull(comment);
        result= createEditModelAndView(comment);

        return result;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Comment comment, BindingResult binding){
        ModelAndView result;
        if (!binding.hasErrors()) {
            result= createEditModelAndView(comment);
        }else{
            try{
                comment.setPostedMoment(new Date(System.currentTimeMillis() - 1000));
                comment.setOwner(customerService.findByPrincipal());
                //comment.setObjectiveId(comment.getObjectiveId());
                commentService.save(comment);

                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(comment, "comment.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Comment comment){
        ModelAndView result;
        try{
            commentService.delete(comment);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(comment, "comment.commit.error");
        }

        return result;
    }

}
