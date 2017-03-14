package controllers;


import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.CustomerService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	//Services ----------------------------------------------------------------

	@Autowired
	private CustomerService customerService;

    @Autowired
    private ActorService actorService;


	//Constructors----------------------------------------------

	public CustomerController(){
		super();
	}

    protected static ModelAndView createEditModelAndView(Customer customer) {
        ModelAndView result;

        result= createEditModelAndView(customer, null);

        return result;
    }


	//Create Method -----------------------------------------------------------

    protected static ModelAndView createEditModelAndView(Customer customer, String message) {
        ModelAndView result;

        result = new ModelAndView("customer/edit");
        result.addObject("customer", customer);
        result.addObject("message", message);

        return result;

    }

    //Create Method -----------------------------------------------------------

    protected static ModelAndView createEditModelAndView2(Customer customer) {
        ModelAndView result;

        result= createEditModelAndView2(customer, null);

        return result;
    }


    //Create Method -----------------------------------------------------------

    protected static ModelAndView createEditModelAndView2(Customer customer, String message) {
        ModelAndView result;

        result = new ModelAndView("customer/register");
        result.addObject("customer", customer);
        result.addObject("message", message);

        return result;

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

		Customer customer = customerService.create();
        result = createEditModelAndView(customer);

		return result;

		}

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView result;
        Customer customer = customerService.create();
        result = createEditModelAndView2(customer);
        return result;
    }

    @RequestMapping(value = "/finishRegistration", method = RequestMethod.POST, params = "save")
    public ModelAndView saveRegistration(@Valid Customer customer) {
        ModelAndView result;
        actorService.registerAsCustomer(customer);
        result = new ModelAndView("redirect:list.do");
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

}
