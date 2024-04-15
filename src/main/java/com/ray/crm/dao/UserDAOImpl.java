package com.ray.crm.dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ray.crm.entity.User;


@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
	private final SessionFactory sessionFactory;
	
	@Autowired
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public User findByUserName(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> theQuery = currentSession.createQuery("from User where username=:uName", User.class);
		theQuery.setParameter("uName", username);
		
		User result = null;
		try {
			result = theQuery.getSingleResult();
		} catch (Exception e) {
			/// log
			result = null;
		}

		return result;
	}

}
