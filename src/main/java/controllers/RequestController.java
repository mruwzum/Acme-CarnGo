package controllers;


import domain.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.RequestService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/request")
public class RequestController extends AbstractController {

	//Services ----------------------------------------------------------------

	@Autowired
	private RequestService requestService;




	//Constructors----------------------------------------------

	public RequestController(){
		super();
	}
	
    protected static ModelAndView createEditModelAndView(Request request) {
        ModelAndView result;

        result= createEditModelAndView(request, null);

        return result;
    }
	
	
	//Create Method -----------------------------------------------------------
	
    protected static ModelAndView createEditModelAndView(Request request, String message) {
        ModelAndView result;

        result= new ModelAndView("request/edit");
        result.addObject("request", request);
        result.addObject("message", message);

        return result;

    }



	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView requestList() {

		ModelAndView result;
		Collection<Request> requests;

        requests = requestService.findAll();
		result = new ModelAndView("request/list");
		result.addObject("requests", requests);
		result.addObject("requestURI","request/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;

		Request request = requestService.create();
        result = createEditModelAndView(request);

		return result;

		}

	// Ancillary methods ------------------------------------------------


    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int requestId){
        ModelAndView result;
        Request request;

        request= requestService.findOne(requestId);
        Assert.notNull(request);
        result= createEditModelAndView(request);

        return result;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Request request, BindingResult binding){
        ModelAndView result;
        if (!binding.hasErrors()) {
            result= createEditModelAndView(request);
        }else{
            try{
                requestService.save(request);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(request, "request.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Request request){
        ModelAndView result;
        try{
            requestService.delete(request);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(request, "request.commit.error");
        }

        return result;
    }

}
