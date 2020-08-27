package com.lti.service;

import com.lti.dto.AdminLoginDetails;
import com.lti.dto.AdminLoginResult;

public interface AdminLoginService {

	public AdminLoginResult adminLoginService(AdminLoginDetails ald);
}
