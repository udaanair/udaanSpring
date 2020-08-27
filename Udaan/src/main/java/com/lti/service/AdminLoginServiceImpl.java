package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.AdminDAO;
import com.lti.dto.AdminLoginDetails;
import com.lti.dto.AdminLoginResult;

@Service
@Transactional
public class AdminLoginServiceImpl implements AdminLoginService{

	@Autowired
	private AdminDAO ad;
	
	@Override
	public AdminLoginResult adminLoginService(AdminLoginDetails ald) {
		AdminLoginResult result =new AdminLoginResult();
		String uname=ad.login(ald);
		if(uname==null)
		{
			result.setName("Not Found");
			result.setStatus("Wrong credentials");
			result.setUserName(ald.getUserName());
		}
		else
		{
			result.setName(uname);
			result.setStatus("Welcome Admin "+uname);
			result.setUserName(ald.getUserName());
		}
		return result;
	}

	

	

}
