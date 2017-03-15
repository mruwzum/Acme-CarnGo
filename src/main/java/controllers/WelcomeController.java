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

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(required = false, defaultValue = "John Doe") final String name) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		Random randomGenerator = new Random();


		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());


		List<String> banner = new ArrayList<>();
		banner.add(" <img src=\"http://www.ambientum.com/img_boletin/home/7-puntos-amovens.jpg\" alt=\"Smiley face\" height=\"400\" width=\"400\"> ");
		banner.add(" <img src=\"http://blog.nasafcu.com/wp-content/uploads/2016/02/car_sharing_1.jpg\" alt=\"Smiley face\" height=\"242\" width=\"242\"> ");
		banner.add(" <img src=\"http://www.altmedia.net.au/wp-content/uploads/2014/11/Silver_yaris_in_pod_car_share_bay_lower_res.jpeg\" alt=\"Smiley face\" height=\"242\" width=\"242\"> ");
		banner.add(" <img src=\"http://www.businesstravellogue.com/files/2007/07/car-share.jpg\" alt=\"Smiley face\" height=\"242\" width=\"242\"> ");

		int index = randomGenerator.nextInt(banner.size());
		String bannerOut = banner.get(index);


		result = new ModelAndView("welcome/index");
		result.addObject("name", name);
		result.addObject("moment", moment);
		result.addObject("banner",bannerOut);
		return result;
	}
}
