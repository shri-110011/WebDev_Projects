package com.shri.ecommercebackend.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* Note spring-mvc has built in support for creating Rest services and Spring-Rest is integrated 
 * with Jackson project. So when we add @RestController and we have the Jackson dependency there in pom.xml
 * then when we get json data from the client it gets automatically converted into java POJO and vice versa.
 * 
 */
@RestController
@RequestMapping("/test")
public class DemoRestController {

	// add code for the "/hello" endpoint
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World!";
	}
	
	@GetMapping("/get-fruits")
	public String[] getFruits() {
		 String fruits[] = {"Apple", "Banana", "Pear"};
		 return fruits;
	}
	
	
}
