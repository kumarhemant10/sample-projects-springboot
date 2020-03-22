package com.hk.prj.usermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Dashboard {

	@GetMapping({"/","/dashboard"})
	public String getDashboardView() {
		return "dashboard"; 
	}
	
}
