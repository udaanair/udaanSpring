package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.lti.dto.UserLoginDetails;

@Component("userlogin")
public class UserLoginDAOImpl implements UserLoginDAO{

	@PersistenceContext	//@Autowired does not work for injecting EntityManager
	private EntityManager entityManager;
	
	@Override
	public String login(UserLoginDetails uld) {
		String query="select a.name from UserRegistration a where a.phoneNumber= :u1 and a.password= :p1";
		List<String> li=entityManager.createQuery(query).setParameter("u1",uld.getUserName())
				.setParameter("p1", uld.getPassword()).getResultList();
		
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
