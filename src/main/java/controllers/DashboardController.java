package controllers;

import domain.Actor;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.AdministratorService;

import java.util.Collection;


@Controller
@RequestMapping("/admin")
public class DashboardController extends AbstractController {

    @Autowired
    private AdministratorService administratorService;


    public DashboardController() {
        super();
    }

    @RequestMapping(value = "/dashboard")
    public ModelAndView dashboard() {
        ModelAndView res;


        //DASHBOARD C

       Double q1 = administratorService.averageOfOfferPerConsumer2();
       Double q2 = administratorService.averageOfRequestPerConsumer2();

       Double q3 = administratorService.averageNumberOfApplicationsPerOffers();
       Double q4 = administratorService.averageNumberOfApplicationsPerRequests2();

       Customer q5 = administratorService.customerWithMoreApplicationsAccepted();
       Customer q6 = administratorService.customerWithMoreApplicationsDenied();

       Double q18 = administratorService.ratioOfferVsRequest();


       //DASHBOARD B

       Double q7 = administratorService.averageNumberOfCommentPerActor();
       Double q8 = administratorService.averageNumberOfCommentPerOffer();
       Double q9 = administratorService.averageNumberOfCommentPerRequest();
       Double q19 = administratorService.averageNumberOfCommentsPostedByActors();
       Collection<Actor> q20 = administratorService.actorWhoHavePosted10TheAverageNumberOfCommentsPerActor();



       //DASHBOARD A


        Double q10 = administratorService.averageNumberOfSMesasgePerActor();
       Double q11 = administratorService.maxNumberOfSMesasgePerActor();
       Double q12 = administratorService.minNumberOfSMesasgePerActor();


       Double q13 = administratorService.averageNumberOfRMesasgePerActor();
       Double q14 = administratorService.maxNumberOfRMesasgePerActor();
       Double q15 = administratorService.minNumberOfRMesasgePerActor();

       Actor q16 = administratorService.actorWhoHaveSentMoreMessage();
       Actor q17 = administratorService.actorWhoHaveGetMoreMessage();










   res = new ModelAndView("administrator/dashboard");


        res.addObject("q1",q1);
        res.addObject("q2",q2);
        res.addObject("q3",q3);
        res.addObject("q4",q4);
        res.addObject("q5",q5);
        res.addObject("q6",q6);
        res.addObject("q7",q7);
        res.addObject("q8",q8);
        res.addObject("q9",q9);
        res.addObject("q10",q10);
        res.addObject("q11",q11);
        res.addObject("q12",q12);
        res.addObject("q13",q13);
        res.addObject("q14",q14);
        res.addObject("q15",q15);
        res.addObject("q16",q16);
        res.addObject("q17",q17);
        res.addObject("q18",q18);
        res.addObject("q19",q19);
        res.addObject("q20",q20);





        return res;
    }
}
