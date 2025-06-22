package com.limits.service.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.limits.service.bean.Limits;
import com.limits.service.coniguration.Configuration;

@RestController
public class LimitsController {

	@Autowired
	private Configuration config;
	
	@GetMapping("/limits")
	public Limits retriveLimits() {
		
		return new Limits(config.getMinimum(),config.getMaximum());
		//return new Limits(1, 1000);
	}
}
