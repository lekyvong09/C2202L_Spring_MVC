package com.ray.crm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ray.crm.constant.SortCustomerColumn;
import com.ray.crm.entity.Customer;


@Repository
public class CustomerDAOImpl implements CustomerDAO{
	
	private final SessionFactory sessionFactory;
	
	@Autowired
	public CustomerDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public List<Customer> getCustomers(int theSortField) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		String sortField = null;
		
		switch (theSortField) {
			case SortCustomerColumn.FIRST_NAME:
				sortField = "firstName";
				break;
			case SortCustomerColumn.EMAIL:
				sortField = "email";
				break;
			default:
				sortField = "lastName";
		}
		
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by " + sortField, Customer.class);
		
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}


	@Override
	public int saveCustomer(Customer theCustomer) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Object customerId;
		
		if (theCustomer.getId() == 0) {
			/// case insert
			customerId = currentSession.save(theCustomer);
		} else {
			/// case update
			currentSession.update(theCustomer);
			customerId = theCustomer.getId();
		}
		
		return (int) customerId;
	}


	@Override
	public Customer getCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Customer theCustomer = currentSession.get(Customer.class, theId);
		return theCustomer;
	}


	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer> theQuery = null;
		
		if (theSearchName != null && theSearchName.trim().length() > 0) {
			theQuery = currentSession.createQuery("from Customer where lower(firstName) like :theName "
					+ "or lower(lastName) like :theName", Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		} else {
			theQuery = currentSession.createQuery("from Customer", Customer.class);
		}
		
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}


	@Override
	public void deleteCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Customer deletedCustomer = currentSession.get(Customer.class, theId);
		currentSession.delete(deletedCustomer);
	}

	
}
