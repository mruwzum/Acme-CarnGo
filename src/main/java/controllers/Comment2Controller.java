package controllers;


import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.AdministratorService;
import services.CommentService;
import services.CustomerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/comment2")
public class Comment2Controller extends AbstractController {

	//Services ----------------------------------------------------------------

	@Autowired
	private CommentService commentService;
@Autowired
private CustomerService customerService;

    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private ActorService actorService;

	//Constructors----------------------------------------------

	public Comment2Controller(){
		super();
	}
	
    protected static ModelAndView createEditModelAndViewR(Comment comment) {
        ModelAndView result;

        result= createEditModelAndViewR(comment, null);

        return result;
    }
	
	
	//Create Method -----------------------------------------------------------
	
    protected static ModelAndView createEditModelAndViewR(Comment comment, String message) {
        ModelAndView result;

        result= new ModelAndView("comment/edit2");
        result.addObject("comment", comment);
        result.addObject("message", message);

        return result;

    }




	@RequestMapping(value = "/createOfferCom", method = RequestMethod.GET)
    public ModelAndView create(@RequestParam int id) {

        ModelAndView result;

		Comment comment = commentService.create();
        comment.setObjectiveId(id);
        result = createEditModelAndViewR(comment);

		return result;

		}




	// Ancillary methods ------------------------------------------------


    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int commentId){
        ModelAndView result;
        Comment comment;
        comment= commentService.findOne(commentId);
        Assert.notNull(comment);
        result= createEditModelAndViewR(comment);

        return result;
    }




    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Comment comment, BindingResult binding){
        ModelAndView result;

        if (!binding.hasErrors()) {
            result= createEditModelAndViewR(comment);
        }else{
            try{

                commentService.postToOffer(comment);
                //commentService.save(comment);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndViewR(comment, "comment.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Comment comment){
        ModelAndView result;
        try{
            commentService.delete(comment);
            result=new ModelAndView("comment/list.do");
        }catch(Throwable oops){
            result= createEditModelAndViewR(comment, "comment.commit.error");
        }

        return result;
    }

}
