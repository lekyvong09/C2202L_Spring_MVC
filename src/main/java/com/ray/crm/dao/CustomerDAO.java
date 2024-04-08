package com.ray.crm.dao;

import java.util.List;

import com.ray.crm.entity.Customer;

public interface CustomerDAO {
	List<Customer> getCustomers(int theSortField);
	
	void saveCustomer(Customer theCustomer);
	
	Customer getCustomer(int theId);
	
	List<Customer> searchCustomers(String theSearchName);
	
	void deleteCustomer(int theId);
	
}
