package controllers;


import domain.Customer;
import domain.Offer;
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
import services.OfferService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

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

    @RequestMapping( value="/listAll", method = RequestMethod.GET)
    public ModelAndView offerListAll() {

        ModelAndView result;
        Collection<Offer> offers;
        offers = offerService.findAll();
        result = new ModelAndView("offer/list");
        result.addObject("offers", offers);
        result.addObject("requestURI","offer/list.do");


        return result;
    }

    //List all offers without banneds
	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView offerList() {

		ModelAndView result;
		ArrayList<Offer> offers = new ArrayList<>(offerService.findAll());
        Collection<Offer> offersRes = new HashSet<>();
        for(Offer o : offers){
            if (!o.isBanned()){
               offersRes.add(o);
            }
        }

        Assert.notEmpty(offers);
		result = new ModelAndView("offer/list");
		result.addObject("offers", offersRes);
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
        res.addObject("banned",o.isBanned());
        return res;

    }

    //Manage applications



    @RequestMapping(value="ban", method=RequestMethod.GET)
    public ModelAndView ban(@RequestParam int offerId){
        ModelAndView result;
        Boolean op;
        Offer offer = offerService.findOne(offerId);
        op = customerService.banOffer(offer);

        if(op.equals(false)){
            result =  new ModelAndView("offer/error");
        }else{
            result =  new ModelAndView("redirect:listAll.do");
        }


        return result;
    }


}
