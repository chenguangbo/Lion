package com.example.demo.test;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SpringBootController {

	@GetMapping("/hello")
	public String index(){
		
		return "index";
	}
	
	
	
}
