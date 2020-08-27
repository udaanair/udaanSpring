package com.lti.service;

import java.util.List;

import com.lti.dto.RegistrationStatus;
import com.lti.dto.SearchParameters;
import com.lti.entity.Flights;
import com.lti.entity.UserRegistration;

public interface FlightsSearchService {

	public List<Flights> allFlights();
	
	public List<Flights> getSearchResult(SearchParameters sp);
	
	public void addNewFlight(Flights fl);
	
	public RegistrationStatus addUser(UserRegistration ul);
}
