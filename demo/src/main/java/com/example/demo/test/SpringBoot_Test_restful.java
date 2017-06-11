package com.example.demo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBoot_Test_restful {

	@GetMapping(value="/rest/{id}")
	public Object rest(@PathVariable(value = "id") String id ){
		if(id==null || id == ""){
			return "请输入参数";
		}
		
		return id;
	}
	
	
	
}
