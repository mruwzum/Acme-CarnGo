package controllers;


import domain.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.TripService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/trip")
public class TripController extends AbstractController {

	//Services ----------------------------------------------------------------

	@Autowired
	private TripService tripService;

	//Constructors----------------------------------------------

	public TripController(){
		super();
	}


	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView actorList() {
		
		ModelAndView result;
		Collection<Trip> actors;
		
		actors = tripService.findAll();
		result = new ModelAndView("trip/list");
		result.addObject("trips", actors);
		result.addObject("requestURI","trip/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	

	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int tripId){
        ModelAndView result;
        Trip actor;
         
        actor= tripService.findOne(tripId);
        Assert.notNull(actor);
        result= createEditModelAndView(actor);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Trip trip, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(trip);
        }else{
            try{
                tripService.save(trip);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(trip, "actor.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Trip trip){
        ModelAndView result;
        try{
            tripService.delete(trip);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(trip, "actor.commit.error");
        }
         
        return result;   
    }



	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(Trip trip){
        ModelAndView result;
         
        result= createEditModelAndView(trip, null);
         
        return result;
    }

    protected ModelAndView createEditModelAndView(Trip trip, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("trip/edit");
        result.addObject("trip", trip);
        result.addObject("message", message);
         
        return result;
 
    }


}
