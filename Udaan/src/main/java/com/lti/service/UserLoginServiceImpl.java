package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.UserLoginDAO;
import com.lti.dto.UserLoginDetails;
import com.lti.dto.UserLoginResult;

@Service
@Transactional
public class UserLoginServiceImpl implements UserLoginService{

	@Autowired
	private UserLoginDAO uldao;
	
	@Override
	public UserLoginResult userLoginService(UserLoginDetails uld) {
		UserLoginResult result=new UserLoginResult();
		String uname=uldao.login(uld);
		if(uname==null)
		{
			result.setName("Not Found");
			result.setStatus("Wrong credentials");
			result.setUserName(uld.getUserName());
		}
		else
		{
			result.setName(uname);
			result.setStatus("Welcome User "+uname);
			result.setUserName(uld.getUserName());
		}
		return result;
	}

	
}
