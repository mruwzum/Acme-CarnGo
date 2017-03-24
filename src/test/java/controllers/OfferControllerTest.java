package controllers;

import domain.Offer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import services.OfferService;
import utilities.AbstractTest;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by mruwzum on 24/3/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml",})
@WebAppConfiguration
@Transactional
public class OfferControllerTest extends AbstractTest {


    @Mock
    private OfferService offerService;
    @Mock
    private Offer offer;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testMockCreation(){
        assertNotNull(offer);
        assertNotNull(offerService);
    }

}
