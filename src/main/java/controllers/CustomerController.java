package controllers;


import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.ApplicationService;
import services.CustomerService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	//Services ----------------------------------------------------------------

	@Autowired
	private CustomerService customerService;

    @Autowired
    private ActorService actorService;
    @Autowired
    private ApplicationService applicationService;


	//Constructors----------------------------------------------

	public CustomerController(){
		super();
	}

    protected static ModelAndView createEditModelAndView(Customer customer) {
        ModelAndView result;

        result= createEditModelAndView(customer, null);

        return result;
    }



    protected static ModelAndView createEditModelAndView(Customer customer, String message) {
        ModelAndView result;

        result = new ModelAndView("customer/edit");
        result.addObject("customer", customer);
        result.addObject("message", message);

        return result;

    }

    protected static ModelAndView createEditModelAndView2(Customer customer) {
        ModelAndView result;

        result= createEditModelAndView2(customer, null);

        return result;
    }



    protected static ModelAndView createEditModelAndView2(Customer customer, String message) {
        ModelAndView result;

        result = new ModelAndView("customer/register");
        result.addObject("customer", customer);
        result.addObject("message", message);

        return result;

    }
    //Create Method -----------------------------------------------------------


    @RequestMapping(value = "/applications", method = RequestMethod.GET)
    public ModelAndView myApplications(){
	    ModelAndView res;

	    Collection<Application> applicationsAll  = customerService.getAllMyApplications();
	    Collection<Application> applicationsP = customerService.getMyPendingApplications();

        res = new ModelAndView("application/list");
        res.addObject("applications", applicationsAll);
        res.addObject("applicationsP", applicationsP);

	    return res;


    }




	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView commentList() {

		ModelAndView result;
		Collection<Customer> customers;

        customers = customerService.findAll();
		result = new ModelAndView("customer/list");
		result.addObject("customers", customers);
		result.addObject("requestURI","customer/list.do");

		return result;
	}




	@RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;
//If you're gonna use this shitty controller, you have to create a new one with a createEditModelAndView without the 2
        //We're using this method to register as customer on da system.
		Customer customer = customerService.create();
        result = createEditModelAndView2(customer);

		return result;

		}
    @RequestMapping(value="/register", method=RequestMethod.POST, params="save")
    public ModelAndView register(@Valid Customer customer, BindingResult binding){
        ModelAndView result;
        if (!binding.hasErrors()) {
            result= createEditModelAndView2(customer);
        }else{
            try{
                actorService.registerAsCustomer(customer);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView2(customer, "customer.commit.error");
            }
        }
        return result;
    }

    // Ancillary methods ------------------------------------------------


    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int customerId){
        ModelAndView result;
        Customer customer;

        customer= customerService.findOne(customerId);
        Assert.notNull(customer);
        result= createEditModelAndView(customer);

        return result;
    }


    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Customer customer, BindingResult binding){
        ModelAndView result;
        if (!binding.hasErrors()) {
            result= createEditModelAndView(customer);
        }else{
            try{
                customerService.save(customer);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(customer, "customer.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Customer customer){
        ModelAndView result;
        try{
            customerService.delete(customer);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(customer, "customer.commit.error");
        }

        return result;
    }


    @RequestMapping(value = "/mybannedC", method = RequestMethod.GET)
    public ModelAndView bannedC (){

        ModelAndView res;

        Collection<Comment> comments = customerService.bannedC();
        res = new ModelAndView("comment/list");
        res.addObject("comments", comments);

        return res;



    }


    @RequestMapping(value = "/mybannedR", method = RequestMethod.GET)
    public ModelAndView bannedR(){

        ModelAndView res;

        Collection<Request> comments = customerService.bannedR();
        res = new ModelAndView("request/list");
        res.addObject("requests", comments);

        return res;

    }


    @RequestMapping(value = "/mybannedO", method = RequestMethod.GET)
    public ModelAndView bannedO(){

        ModelAndView res;

        Collection<Offer> comments = customerService.bannedO();
        res = new ModelAndView("offer/list");
        res.addObject("offers", comments);

        return res;

    }


    //Manage applications

    @RequestMapping(value="/app/accept", method=RequestMethod.GET)
    public ModelAndView accept(@RequestParam int applicationId){
        ModelAndView result;
        Boolean op;
        Application application = applicationService.findOne(applicationId);
        op = customerService.acceptApplication(application);

        if(op.equals(false)){
            result =  new ModelAndView("administrator/error");
        }else{
            result =  new ModelAndView("administrator/success");
        }


        return result;
    }

    @RequestMapping(value="/app/deny", method=RequestMethod.GET)
    public ModelAndView deny(@RequestParam int applicationId){
        ModelAndView result;
        Boolean op;
        Application application = applicationService.findOne(applicationId);
        op = customerService.deniedApplication(application);

        if(op.equals(false)){
            result =  new ModelAndView("administrator/error");
        }else{
            result =  new ModelAndView("administrator/success");
        }


        return result;
    }
    @RequestMapping(value = "/view" , method = RequestMethod.GET)
    public ModelAndView view(@RequestParam int customerId){

        ModelAndView res;
        Customer o = customerService.findOne(customerId);
        Collection<Comment> comm = new HashSet<>(o.comments);
        res = new ModelAndView("actor/view");
        res.addObject("name",o.getName());
        res.addObject("surname",o.getSurname());
        res.addObject("comments", comm);
        res.addObject("id",o.getId());
        return res;

    }
}
