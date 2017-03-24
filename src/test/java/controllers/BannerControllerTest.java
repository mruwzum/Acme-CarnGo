package controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import services.BannerService;
import utilities.AbstractTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by mruwzum on 24/3/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml",})
@WebAppConfiguration
@Transactional
public class BannerControllerTest extends AbstractTest {

    @Mock
    private BannerService bannerService;
    @Mock
    private BannerController controller;

    private MockMvc mvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();

    }
    @Test
    public void create() throws Exception {
        //when(bannerService.save(isA(Banner.class))).thenReturn(new Banner());
        mvc.perform(get("/banner/create"));
    }
    @Test
    public void list() throws Exception {
        mvc.perform(get("/banner/list"));
    }
    @Test
    public void edit() throws Exception {
        mvc.perform(get("/banner/edit").param("int","bannerId"));
    }
    @Test
    public void editBinding() throws Exception {
        mvc.perform(post("/banner/edit").param("Banner","banner").param("BindingResult" ,"binding"));
    }
    @Test
    public void delete() throws Exception {
        mvc.perform(get("/banner/delete").param("int","bannerId"));
    }

}
