package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reading")
public class ReadingController {

	@RequestMapping("/narrative")
	public String narrative() {
		
		// controller from path /reading/narrative to narrative.html
		return "narrative";
		
	}
	
	
	@RequestMapping("/assay")
	public String assay() {
		
		// controller from path /reading/assay to assay.html
		return "assay";
		
	}
}
