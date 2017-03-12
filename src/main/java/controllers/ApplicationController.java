package controllers;


import domain.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ApplicationService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/application")
public class ApplicationController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private ApplicationService applicationService;



	
	//Constructors----------------------------------------------
	
	public ApplicationController(){
		super();
	}
	
    protected static ModelAndView createEditModelAndView(Application application) {
        ModelAndView result;

        result= createEditModelAndView(application, null);

        return result;
    }
	
	
	//Create Method -----------------------------------------------------------
	
    protected static ModelAndView createEditModelAndView(Application application, String message) {
        ModelAndView result;

        result= new ModelAndView("application/edit");
        result.addObject("application", application);
        result.addObject("message", message);

        return result;

    }



	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView commentList() {

		ModelAndView result;
		Collection<Application> applications;

        applications = applicationService.findAll();
		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("requestURI","application/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;

		Application application = applicationService.create();
        result = createEditModelAndView(application);

		return result;

		}

	// Ancillary methods ------------------------------------------------


    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int applicationId){
        ModelAndView result;
        Application application;

        application= applicationService.findOne(applicationId);
        Assert.notNull(application);
        result= createEditModelAndView(application);

        return result;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Application application, BindingResult binding){
        ModelAndView result;
        if (!binding.hasErrors()) {
            result= createEditModelAndView(application);
        }else{
            try{
                applicationService.save(application);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(application, "application.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Application application){
        ModelAndView result;
        try{
            applicationService.delete(application);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(application, "comment.commit.error");
        }

        return result;
    }

}
