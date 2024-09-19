package com.shri.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shri.springdemo.entity.Customer;
import com.shri.springdemo.service.CustomerService;

@Controller
@RequestMapping(path="/customer")
public class CustomerController {
	
	// need to inject customer service
	@Autowired
	private CustomerService customerservice;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customers from the service
		List<Customer> theCustomers = customerservice.getCustomers();
		
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showAddCustomerForm(Model theModel) {
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer, BindingResult bindingResult) {
		
		System.out.println("Customer to be saved: "+theCustomer);
		
		// Save the customer using our service
		customerservice.saveCustomer(theCustomer);
		
		/* Note we could use either "redirect:list" or 
		 * "redirect:/customer/list" to redirect to the customers list page.
		 * 
		 * Take care of the "/" after the ":". If the "/" is not there after
		 * ":" the resource path would be considered relative to the current 
		 * url mapping for our controller.
		 * 
		 * If the "/" is there after ":" the resource path would be considered 
		 * relative to the base url for our application.
		 */
		return "redirect:list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showUpdateCustomerForm(@RequestParam("customerId") int theId , Model theModel) {
		
		// get the customer form the database
		Customer theCustomer = customerservice.getCustomer(theId);
		
		System.out.println("Customer selected for update: "+theCustomer);
		
		// set customer as model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		// send over to our form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		System.out.println("Id of customer to be deleted: "+theId);
		
		// Delete the customer using our service
		customerservice.deleteCustomer(theId);
		
		return "redirect:list";
	}
	
}
