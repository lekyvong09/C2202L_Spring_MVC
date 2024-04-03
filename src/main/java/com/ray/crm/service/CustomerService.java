package com.ray.crm.service;

import java.util.List;

import com.ray.crm.entity.Customer;

public interface CustomerService {
	List<Customer> getCustomers();
	
	void saveCustomer(Customer theCustomer);
	
	Customer getCustomer(int theId);
	
	List<Customer> searchCustomers(String theSearchName);
}
