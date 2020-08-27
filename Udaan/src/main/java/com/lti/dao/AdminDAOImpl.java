package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.AdminLoginDetails;
import com.lti.entity.AdminRegistration;

@Component("admin")
public class AdminDAOImpl implements AdminDAO{

	@PersistenceContext	//@Autowired does not work for injecting EntityManager
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void addAdmin(AdminRegistration ar) {
		entityManager.persist(ar);
		
	}

	@Override
	public String login(AdminLoginDetails ald) {
		String query="select a.adminName from AdminRegistration a where a.username= :u1 and a.password= :p1";
		List<String> li=entityManager.createQuery(query).setParameter("u1", ald.getUserName()).setParameter("p1", ald.getPassword()).getResultList();
		if(li.size()>0)
		{
			System.out.println(li.get(0));
			return li.get(0);
		}
		else
		{
			System.out.println("No data found");
		}
		return null;
	}

}
