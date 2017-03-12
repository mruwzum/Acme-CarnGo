package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.AdministratorService;


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

//   //DASHBOARD C
//    Double q1 = administratorService.averageAcceptedRequestPerLessor();
//    Double q2 = administratorService.averageDeniedRequestPerLessor();
//    Double q3 = administratorService.averageAcceptedRequestPerTenat();
//    Double q4 = administratorService.averageDeniedRequestPerTenat();
//    Collection<Lessor> q5 = administratorService.lessorsWhoHasAcceptedBookRequests();
//    Collection<Lessor> q6 = administratorService.lessorsWhoHasDeniedBookRequests();
//    Collection<Lessor> q7 = administratorService.lessorsWhoHasPendingBookRequests();
//    Collection<Tenant> q8 = administratorService.getTenantWithMoreApprovedBookRequest();
//    Collection<Tenant> q9 = administratorService.getTenantWithMoreDeniedBookRequest();
//    Collection<Tenant> q10 = administratorService.getTenantWithMorePendingBookRequest();
//    Map<Tenant, Double> q11 = administratorService.ratioOfRequestedVSApprovedRequestedPerTenant();
//    Map<Lessor, Double> q12 = administratorService.ratioOfRequestedVSApprovedRequestedPerLessor();
//    Double q13 = administratorService.averageResultPerFinder();
//    Integer q14 = administratorService.MaximumResultPerFinder();
//    Integer q15 = administratorService.MinimumResultPerFinder();
//
//    //DASHBOARD B
//
//    Double  q16 = administratorService.averageNumberOfAuditsPerProperties();
//    String q17 = administratorService.maximumNumberOfAuditsPerProerties();
//    String q18 = administratorService.minimumNumberOfAuditsPerProerties();
//    Collection<Integer> q19 = administratorService.numberOfTimesInWithAnAtributteHasBeenUserToDescribeAProperty();
//    Collection<Lessor> q20 = administratorService.lessorsOrderByNumberOfAudits();
//    Collection<Lessor> q21 = administratorService.lessorsOrderedByNumberOfRequests();
//    Collection<Lessor> q22 = administratorService.lessorsOrderByNumberOfAprovvedRq();
//    Collection<Lessor> q23 = administratorService.lessorOrderByNumberOfDeniedRequestHisPropertiesHaveGot();
//    Collection<Lessor> q24 = administratorService.lessorOrderByNumberOfPendigRequestHisPropertiesHaveGot();
//
//    //DASHBOARD A
//
//
//    Double q25 = administratorService.averageNumberOfSocialIdentitiesPerActor();
//    Integer q26 = administratorService.maximumNumberOfSocialIdentitesPerActor();
//    Integer q27 = administratorService.minimumNumberOfSocialIdentitesPerActor();
//    Double q28 = administratorService.averageNumberOfInvoicePerTenant();
//    Double q29 = administratorService.maximumNumberOfInvoicePerTenant();
//    Double q30 = administratorService.minimumNumberOfInvoicePerTenant();
//    Double q31 = administratorService.totalInvoiceAmmountOnTheSystem();
//    Double q32 = administratorService.averageReqProphaveAtAuditRecordVSavgOfReqForPropWithNoAudits();
//
   res = new ModelAndView("administrator/dashboard");
//        //C
//        res.addObject("q1", q1);
//        res.addObject("q2", q2);
//        res.addObject("q3", q3);
//        res.addObject("q4", q4);
//        res.addObject("q5", q5);
//        res.addObject("q6", q6);
//        res.addObject("q7", q7);
//        res.addObject("q8", q8);
//        res.addObject("q9", q9);
//        res.addObject("q10", q10);
//        res.addObject("q11Pers", q11.keySet());
//        res.addObject("q11Rat", q11.values());
//        res.addObject("q12Pers", q12.keySet());
//        res.addObject("q12Rat", q12.values());
//        res.addObject("q13", q13);
//        res.addObject("q14", q14);
//        res.addObject("q15", q15);
//        //B
//        res.addObject("q16", q16);
//        res.addObject("q17", q17);
//        res.addObject("q18", q18);
//        res.addObject("q19", q19);
//        res.addObject("q20", q20);
//        res.addObject("q21", q21);
//        res.addObject("q22", q22);
//        res.addObject("q23", q23);
//        res.addObject("q24", q24);
//        //A
//        res.addObject("q25", q25);
//        res.addObject("q26", q26);
//
//        res.addObject("q27", q27);
//        res.addObject("q28", q28);
//        res.addObject("q29", q29);
//        res.addObject("q30", q30);
//        res.addObject("q31", q31);
//        res.addObject("q32", q32);
//
//
//
//        return res;
//    }
        return res;
    }
}
