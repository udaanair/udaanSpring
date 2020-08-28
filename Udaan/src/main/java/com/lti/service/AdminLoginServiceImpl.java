package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.AdminDAO;
import com.lti.dto.AdminLoginDetails;
import com.lti.dto.AdminLoginResult;
import com.lti.dto.FlightIdForCancellation;
import com.lti.dto.Status;

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

	@Override
	public Status cancelFlightAndUpdateInCustomerBooking(FlightIdForCancellation fifc) {
		ad.cancelFlight(fifc.getFlightId());
		Status st=new Status();
		st.setStatus("Flight deleted successfully and refund initiated");
		return st;
	}

	

	

}
