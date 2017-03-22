package controllers;


import domain.Finder;
import domain.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.FinderService;
import services.TripService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/finder")
public class FinderController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private FinderService finderService;
	@Autowired
    private TripService tripService;
	//Constructors----------------------------------------------


	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView finderList() {
		
		ModelAndView result;
		Collection<Finder> finders;
		
		finders = finderService.findAll();
		result = new ModelAndView("finder/list");
		result.addObject("finders", finders);
		result.addObject("requestURI","finder/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		Finder finder = finderService.create();
		result = createEditModelAndView(finder);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int finderId){
        ModelAndView result;
        Finder finder;
         
        finder= finderService.findOne(finderId);
        Assert.notNull(finder);
        result= createEditModelAndView(finder);
         
        return result;
    }
     
    @RequestMapping(value="/find", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Finder finder, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(finder);
        }else{
            try{

                List<Trip> properties = finderService.finder(finder.getTitle(),finder.getDescription(),finder.getOriginAddress(),finder.getDestinationAddress(),finder.getKeyword());
                finder.setNumberOfFinderResults(properties.size());
                 finderService.save(finder);
                result= new ModelAndView("trip/list");
                result.addObject("trips",properties);
            }catch(Throwable oops){
                result= createEditModelAndView(finder, "finder.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Finder finder){
        ModelAndView result;
        try{
            finderService.delete(finder);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(finder, "finder.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(Finder finder){
        ModelAndView result;
         
        result= createEditModelAndView(finder, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(Finder finder, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("finder/edit");
        result.addObject("finder", finder);
        result.addObject("message", message);

         
        return result;
 
    }


}
