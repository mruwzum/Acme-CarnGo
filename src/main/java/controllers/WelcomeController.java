/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import domain.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.BannerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {
	@Autowired
	private BannerService bannerService;

	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(required = false, defaultValue = "John Doe") final String name) {
		ModelAndView result;

		Random randomGenerator = new Random();


		List<Banner> banner = new ArrayList<>();
		banner.addAll(bannerService.findAll());


		int index = randomGenerator.nextInt(banner.size());
		String bannerOut = banner.get(index).getUrl();

		String ppoImg = "<img src=\"";
		String finImg ="\" alt=\"Banner\" height=\"400\" width=\"400\">";

		String bannerFin = ppoImg+bannerOut+finImg;


		result = new ModelAndView("welcome/index");
		result.addObject("banner",bannerFin);
		return result;
	}
}
