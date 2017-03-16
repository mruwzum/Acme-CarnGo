package controllers;


import domain.Administrator;
import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.AdministratorService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {
	
	//Services ----------------------------------------------------------------


    @Autowired
    private AdministratorService administratorService;

	
	//Constructors----------------------------------------------
	
	public AdministratorController(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView administratorList() {
		
		ModelAndView result;
		Collection<Administrator> administrators;
		
		administrators = administratorService.findAll();
		result = new ModelAndView("administrator/list");
		result.addObject("administrators", administrators);
		result.addObject("requestURI","administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		Administrator administrator = administratorService.create();
		result = createEditModelAndView(administrator);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int administratorId){
        ModelAndView result;
        Administrator administrator;
         
        administrator= administratorService.findOne(administratorId);
        Assert.notNull(administrator);
        result= createEditModelAndView(administrator);
         
        return result;
    }

    @RequestMapping(value = "/view" , method = RequestMethod.GET)
    public ModelAndView view(@RequestParam int administratorId){

        ModelAndView res;
        Administrator o = administratorService.findOne(administratorId);
        Collection<Comment> comm = new HashSet<>(o.comments);
        res = new ModelAndView("actor/view");
        res.addObject("name",o.getName());
        res.addObject("surname",o.getSurname());
        res.addObject("comments", comm);
        res.addObject("id",o.getId());
        return res;

    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Administrator administrator, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(administrator);
        }else{
            try{
                administratorService.save(administrator);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(administrator, "administrator.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Administrator administrator){
        ModelAndView result;
        try{
            administratorService.delete(administrator);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(administrator, "administrator.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(Administrator administrator){
        ModelAndView result;
         
        result= createEditModelAndView(administrator, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(Administrator administrator, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("administrator/edit");
        result.addObject("administrator", administrator);
        result.addObject("message", message);
         
        return result;
 
    }


}
