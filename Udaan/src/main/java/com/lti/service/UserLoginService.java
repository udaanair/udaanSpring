package com.lti.service;

import com.lti.dto.UserLoginDetails;
import com.lti.dto.UserLoginResult;

public interface UserLoginService {

	public UserLoginResult userLoginService(UserLoginDetails uld);
	
}
