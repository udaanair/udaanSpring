package com.lti.dao;

import com.lti.dto.AdminLoginDetails;
import com.lti.entity.AdminRegistration;

public interface AdminDAO {

	public void addAdmin(AdminRegistration ar);
	
	public String login(AdminLoginDetails ald);
	
	public void cancelFlight(int flight_id);
}
