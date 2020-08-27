package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.FlightsDAO;
import com.lti.dto.RegistrationStatus;
import com.lti.dto.SearchParameters;
import com.lti.entity.Flights;
import com.lti.entity.UserRegistration;

@Service
@Transactional
public class FlightsSearchServiceImpl implements FlightsSearchService{

	@Autowired
	private FlightsDAO fdi;
	
	@Override
	public List<Flights> allFlights()
	{
		////1600584600000
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		List<Flights> li=fdi.getAllFlights();
		for(Flights fl:li) {
			/*try {
				fl.setDepartureSchedule(formatter.parse(fl.getDepartureSchedule().toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			System.out.println(fl.getDepartureSchedule());
		}
		return li;
	}

	@Override
	public List<Flights> getSearchResult(SearchParameters sp) {
		if(sp.getCabinSelected().equalsIgnoreCase("economy"))
		{
			return fdi.getEconomySearchResult(sp);
		}
		else
		{
			return fdi.getBusinessSearchResult(sp);
		}
	}

	@Override
	public void addNewFlight(Flights fl) {
		fl.setStatus("running"); 		
		if(fl.getCabin().equalsIgnoreCase("economy")) 		
		{ 			
			fl.setEconomySeatsLeft(60); 			
			fl.setBusinessSeatsLeft(0); 		
		} 		
		else 		
		{ 			
			fl.setEconomySeatsLeft(40); 			
			fl.setBusinessSeatsLeft(20); 		
		} 	 		
		fdi.addNewFlight(fl);
		
	}

	@Override
	public RegistrationStatus addUser(UserRegistration ul) {
		RegistrationStatus s=new RegistrationStatus();
		if(fdi.isValid(ul.getPhoneNumber())== 1)
		{
			s.setStatus("This phone number is already registered");
		}
		else
		{
			fdi.addUser(ul);
			s.setStatus("Registered Successfully");
		}
		return s;
	}
}
