package controllers;


import domain.Customer;
import domain.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CustomerService;
import services.OfferService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/offer")
public class OfferController extends AbstractController {

	//Services ----------------------------------------------------------------

	@Autowired
	private OfferService offerService;


    @Autowired
    private CustomerService customerService;

	//Constructors----------------------------------------------

	public OfferController(){
		super();
	}
	
    protected static ModelAndView createEditModelAndView(Offer offer) {
        ModelAndView result;

        result= createEditModelAndView(offer, null);

        return result;
    }
	
	
	//Create Method -----------------------------------------------------------
	
    protected static ModelAndView createEditModelAndView(Offer offer, String message) {
        ModelAndView result;

        result= new ModelAndView("offer/edit");
        result.addObject("offer", offer);
        result.addObject("message", message);

        return result;

    }

    //List all offers

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView commentList() {

		ModelAndView result;
		Collection<Offer> offers;

        offers = offerService.findAll();
		result = new ModelAndView("offer/list");
		result.addObject("offers", offers);
		result.addObject("requestURI","offer/list.do");

		return result;
	}

	//List the offers of actor who is authenticated

    @RequestMapping( value="/listMy", method = RequestMethod.GET)
    public ModelAndView OfferMyList() {

        ModelAndView result;

        Collection<Offer> offers = customerService.findByPrincipal().getOffers();

        result = new ModelAndView("offer/list");
        result.addObject("offers", offers);
        result.addObject("requestURI","offer/list.do");

        return result;
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;

		Offer offer = offerService.create();
        result = createEditModelAndView(offer);

		return result;

		}

	// Ancillary methods ------------------------------------------------


    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int offerId){
        ModelAndView result;
        Offer offer;

        offer= offerService.findOne(offerId);
        Assert.notNull(offer);
        result= createEditModelAndView(offer);

        return result;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Offer offer, BindingResult binding){
        ModelAndView result;
        if (!binding.hasErrors()) {
            result= createEditModelAndView(offer);
        }else{
            try{
                offer.setOwnerO(customerService.findByPrincipal());
                offerService.save(offer);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(offer, "offer.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Offer offer){
        ModelAndView result;
        try{
            offerService.delete(offer);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(offer, "offer.commit.error");
        }

        return result;
    }


    @RequestMapping(value = "/view" , method = RequestMethod.GET)
    public ModelAndView view(@RequestParam int offerId){

        ModelAndView res;
        Offer o = offerService.findOne(offerId);

        res = new ModelAndView("offer/view");
        res.addObject("title",o.getTitle());
        res.addObject("description",o.getDescription());
        res.addObject("originAddress", o.getOriginAddress());
        res.addObject("destinationAddress", o.getDestinationAddress());
        res.addObject("tripDate",o.getTripDate());
        res.addObject("comments",o.getComment());
        res.addObject("applications",o.getApplications());

        return res;

    }

}
