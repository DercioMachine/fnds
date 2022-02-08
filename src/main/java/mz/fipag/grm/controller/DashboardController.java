package mz.fipag.grm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {
	
	public ModelAndView dashboard() {
		
		ModelAndView vm = new ModelAndView("Dashboard");
		
		return vm;
		
	}
	

}
