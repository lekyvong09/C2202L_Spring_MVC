package com.ray.crm.dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ray.crm.entity.Role;

@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO {

	private SessionFactory sessionFactory;
	
	
	@Autowired
	public RoleDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}



	@Override
	public Role findRoleByName(String roleName) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Role> theQuery = currentSession.createQuery("from Role where name=:roleName", Role.class);
		theQuery.setParameter("roleName", roleName);
		
		Role result = null;
		try {
			result = theQuery.getSingleResult();
		} catch (Exception e) {
			/// log
			result = null;
		}

		return result;
	}

	
}
