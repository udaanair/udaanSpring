package com.lti.dao;

import java.util.Date;
import java.util.List;

import com.lti.dto.SearchParameters;
import com.lti.entity.Flights;
import com.lti.entity.UserRegistration;

public interface FlightsDAO {

	//adduser function-->userregistraion entity
	
	public void addNewFlight(Flights fl);

	public List<Flights> getEconomySearchResult(SearchParameters sp);
	
	public List<Flights> getBusinessSearchResult(SearchParameters sp);

	public List<Flights> getAllFlights();
	
	public List<String> getCabinType(int flightId);

	public void addUser(UserRegistration ul);

	int isValid(long phoneNumber);
}