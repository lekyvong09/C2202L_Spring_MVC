package com.ray.crm.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ray.crm.constant.SortCustomerColumn;
import com.ray.crm.dao.CustomerDAO;
import com.ray.crm.entity.Customer;
import com.ray.crm.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	private final CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/list")
	public String listCustomers(Model theModel, @RequestParam(required = false) String sort) {
		List<Customer> theCustomers = null;
		
		if (sort != null) {
			int theSortField = Integer.parseInt(sort);
			theCustomers = customerService.getCustomers(theSortField);
		} else {
			theCustomers = customerService.getCustomers(SortCustomerColumn.LAST_NAME);
		}
		
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}


	@GetMapping("/new")
	public String showFormForAdd(Model theModel) {
		Customer theCustomer = new Customer();
		theModel.addAttribute("customer", theCustomer);
		return "customer-form";
	}
	
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@RequestParam CommonsMultipartFile file, @ModelAttribute("customer") Customer theCustomer) {
		String path = "/Users/ray/Dropbox/Aptech/C2202L/JavaEE/crm/src/main/resources/images";
		
		int customerId = customerService.saveCustomer(theCustomer);
		
		try {
			byte[] bytes = file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File(path + File.separator + "customer_" + customerId + ".jpg")));
			stream.write(bytes);
			stream.flush();
			stream.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/customer/list";
	}
	
	
	@GetMapping("/load")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		Customer theCustomer = customerService.getCustomer(theId);
		theModel.addAttribute("customer", theCustomer);
		return "customer-form";
	}
	
	
	@GetMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String theSearchName, Model theModel) {
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
		theModel.addAttribute("customers", theCustomers);
		return "list-customers";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		customerService.deleteCustomer(theId);
		return "redirect:/customer/list";
	}
}
