package controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import services.BannerService;
import utilities.AbstractTest;


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



    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BannerService bannerService;


    private MockMvc mvc;

    @Before
    public void setup() {

        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.context);
        this.mvc = builder.build();
      //  mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testMyMvcController() throws Exception {
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        ResultMatcher msg = MockMvcResultMatchers.model()
                .attribute("msg", "Spring quick start!!");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/banner/list");
        this.mvc.perform(builder)
                .andExpect(ok)
                .andExpect(msg);
    }
}
