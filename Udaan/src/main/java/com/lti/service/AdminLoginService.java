package com.lti.service;

import com.lti.dto.AdminLoginDetails;
import com.lti.dto.AdminLoginResult;
import com.lti.dto.FlightIdForCancellation;
import com.lti.dto.Status;

public interface AdminLoginService {

	public AdminLoginResult adminLoginService(AdminLoginDetails ald);
	
	public Status cancelFlightAndUpdateInCustomerBooking(FlightIdForCancellation fifc); 
}
