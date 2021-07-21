package com.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
@SpringBootApplication
public class LogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogApiApplication.class, args);
	}
	
	@GetMapping
	public ModelAndView swaggerui() {
		return new ModelAndView("redirect:/swagger-ui/");
	}

}
