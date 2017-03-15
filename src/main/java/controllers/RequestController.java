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
import services.CustomerService;
import services.RequestService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Controller
@RequestMapping("/request")
public class RequestController extends AbstractController {

	//Services ----------------------------------------------------------------

	@Autowired
	private RequestService requestService;

@Autowired
private CustomerService customerService;


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

    @RequestMapping( value="/listAll", method = RequestMethod.GET)
    public ModelAndView requestListAll() {

        ModelAndView result;
        Collection<Request> requests;
        requests = requestService.findAll();
        result = new ModelAndView("request/list");
        result.addObject("requests", requests);
        result.addObject("requestURI","request/list.do");

        return result;
    }


	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView requestList() {

		ModelAndView result;
        ArrayList<Request> requests = new ArrayList<>(requestService.findAll());
        Collection<Request> requestRes = new HashSet<>();
        for(Request r : requests){
            if (!r.isBanned()){
                requestRes.add(r);
            }
        }

		result = new ModelAndView("request/list");
		result.addObject("requests", requestRes);
		result.addObject("requestURI","request/list.do");

		return result;
	}


    //List the request of actor who is authenticated

    @RequestMapping( value="/listMy", method = RequestMethod.GET)
    public ModelAndView RequestsMyList() {

        ModelAndView result;

        Collection<Request> offers = customerService.findByPrincipal().getRequests();

        result = new ModelAndView("request/list");
        result.addObject("requests", offers);
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
        if (binding.hasErrors()) {
            result= createEditModelAndView(request);
        }else{
            try{
                request.setOwnerR(customerService.findByPrincipal());
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


    @RequestMapping(value = "/view" , method = RequestMethod.GET)
    public ModelAndView view(@RequestParam int requestId){

        ModelAndView res;
        Request o = requestService.findOne(requestId);

        res = new ModelAndView("offer/view");
        res.addObject("title",o.getTitle());
        res.addObject("description",o.getDescription());
        res.addObject("originAddress", o.getOriginAddress());
        res.addObject("destinationAddress", o.getDestinationAddress());
        res.addObject("tripDate",o.getTripDate());
        res.addObject("comments",o.getComment());
        res.addObject("applications",o.getApplications());
        res.addObject("banned",o.isBanned());
        return res;

    }

    @RequestMapping(value="ban", method=RequestMethod.GET)
    public ModelAndView ban(@RequestParam int requestId){
        ModelAndView result;
        Boolean op;
        Request request = requestService.findOne(requestId);
        op = customerService.banRequest(request);

        if(op.equals(false)){
            result =  new ModelAndView("request/error");
        }else{
            result =  new ModelAndView("redirect:listAll.do");
        }


        return result;
    }
}
