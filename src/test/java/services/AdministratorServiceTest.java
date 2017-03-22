package services;

import domain.Banner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import utilities.AbstractTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mruwzum on 17/3/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional

public class AdministratorServiceTest extends AbstractTest {
    // System under test ------------------------------------------------------

    @Autowired
    AdministratorService administratorService;
    @Autowired
    private BannerService bannerService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private RequestService requestService;
    // Tests ------------------------------------------------------------------

    @Test
    public void changeBanner() throws Exception {
        authenticate("administrator1");
        List<Banner> bannerList = new ArrayList<>(bannerService.findAll());
        Banner banner = bannerList.get(0);
        banner.setUrl("www.http://caracolazo.com/wp-content/uploads/2014/12/asdasdasd.jpg");
        Banner banner1 = bannerService.save(banner);
        Assert.isTrue(banner.getUrl().equals(banner1.getUrl()));
        authenticate(null);
        bannerService.flush();
    }
    @Test
    public void createBanner() throws Exception {
        authenticate("administrator1");
        Banner banner = bannerService.create();
        banner.setUrl("www.http://caracolazo.com/wp-content/uploads/2014/12/asdasdasd.jpg");
        Assert.notNull(bannerService.save(banner));
        authenticate(null);
        bannerService.flush();
    }
    @Test(expected = IllegalArgumentException.class)
    public void createBannerNonReg() throws Exception {
        authenticate("dfg");
        Banner banner = bannerService.create();
        banner.setUrl("www.http://caracolazo.com/wp-content/uploads/2014/12/asdasdasd.jpg");
       bannerService.save(banner);
        authenticate(null);
        bannerService.flush();
    }
    @Test
    public void changeBannerNot() throws Exception{
        authenticate("customer2");
        List<Banner> bannerList = new ArrayList<>(bannerService.findAll());
        Banner banner = bannerList.get(0);
        banner.setUrl(null);
        bannerService.save(banner);
        authenticate(null);
        bannerService.flush();
    }


    // The following are fictitious test cases that are intended to check that
    // JUnit works well in this project.  Just righ-click this class and run
    // it using JUnit.

    @Test
    public void samplePositiveTest() {
        Assert.isTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sampleNegativeTest() {
        Assert.isTrue(false);
    }

    // Ancillary methods ------------------------------------------------------
}