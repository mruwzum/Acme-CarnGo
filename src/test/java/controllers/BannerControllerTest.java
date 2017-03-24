package controllers;

import domain.Banner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import services.BannerService;
import utilities.AbstractTest;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by mruwzum on 24/3/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml",})
@WebAppConfiguration
@Transactional
public class BannerControllerTest extends AbstractTest {

    //http://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/spring-mvc-unit-testing/



    @Mock
    private BannerService bannerService;
    @Mock
    private BannerController controller;

    private MockMvc mvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
     //   DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.context);
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void testMyMvcController() throws Exception {
        ResultMatcher ok = status().isOk();
        ResultMatcher msg = model()
                .attribute("msg", "Spring quick start!!");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/banner/list");
        this.mvc.perform(builder)
                .andExpect(ok)
                .andExpect(msg);
    }

    //http://stackoverflow.com/questions/16170572/unable-to-mock-service-class-in-spring-mvc-controller-tests
    @Test(expected = NullPointerException.class)
    public void createOrUpdateFailsWhenInvalidDataPostedAndSendsUserBackToForm() throws Exception {
        // POST no data to the form (i.e. an invalid POST)
        mvc.perform(post("/edit")).andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("banner"))
                .andExpect(view().name("banner"));
    }

    @Test
    public void createOrUpdateSuccessful() throws Exception {

        when(bannerService.save(isA(Banner.class))).thenReturn(new Banner());

        mvc.perform(
                post("/banner/edit").param("companyName", "Company Name")
                        .param("name", "Name").param("effectiveDate", "2001-01-01"))
                .andExpect(status().isMovedTemporarily()).andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("list"));
    }


    @Test
    public void create() throws Exception {

        //when(bannerService.save(isA(Banner.class))).thenReturn(new Banner());

        mvc.perform(get("/create"));
    }

}
