package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/programming")
public class ProgrammingController {

	@RequestMapping("/java")
	public String java() {
		
		// controller from path /programming/java to java.html in templates
		return "java";
		
	}
	
	
	@RequestMapping("/thymeleaf")
	public String thymeleaf() {
		
		// controller from path /programming/thymeleaf to thymeleaf.html
		return "thymeleaf";
		
	}
}
