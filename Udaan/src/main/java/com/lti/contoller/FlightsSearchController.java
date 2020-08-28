package com.lti.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AdminLoginDetails;
import com.lti.dto.AdminLoginResult;
import com.lti.dto.AvailableSeatsListClass;
import com.lti.dto.FlightIdForCancellation;
import com.lti.dto.RegistrationStatus;
import com.lti.dto.ResultOfBooking;
import com.lti.dto.SearchParameters;
import com.lti.dto.SeatParameters;
import com.lti.dto.Status;
import com.lti.dto.UserLoginDetails;
import com.lti.dto.UserLoginResult;
import com.lti.entity.BookingRecord;
import com.lti.entity.Flights;
import com.lti.entity.UserRegistration;
import com.lti.service.AdminLoginService;
import com.lti.service.BookingAndSeatSelectionService;
import com.lti.service.FlightsSearchService;
import com.lti.service.UserLoginService;

@RestController
@CrossOrigin
public class FlightsSearchController {
	
	@Autowired
	private BookingAndSeatSelectionService basss;
	
	@Autowired
	private FlightsSearchService fss;
	
	@Autowired
	private AdminLoginService als;
	
	//private AdminLoginServiceImpl try1=new AdminLoginServiceImpl();//using this instead of interface auto-wiring above will give null pointer exception
	
	@Autowired
	private UserLoginService uls;
	
	@RequestMapping(path = "/fetchFlights.api", method = RequestMethod.GET , produces = "application/json")
	public List<Flights> getAllFLights()
	{
		List<Flights> itemsList=fss.allFlights();
		return itemsList;
	}
	
	@RequestMapping(path = "/getFlights.api", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public List<Flights> getSearchFLights(@RequestBody SearchParameters sp)
	{
		List<Flights> itemsList=fss.getSearchResult(sp);
		return itemsList;
	}

	@RequestMapping(path = "/addflight.api", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Status Flights(@RequestBody Flights fl) {
		Status status = new Status();
		fss.addNewFlight(fl);
		status.setStatus("Flight added successfully");
		return status;
	}
	
	@RequestMapping(path = "/availableseats.api", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public AvailableSeatsListClass availableSeats(@RequestBody SeatParameters sp)
	{
		return basss.getAvailableSeatsList(sp);
	}
	
	@RequestMapping(path = "/addBooking.api", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResultOfBooking bookTicket(@RequestBody BookingRecord br)
	{
		ResultOfBooking rob=basss.addBookingDetails(br);
		return rob;
	}
	
	@RequestMapping(path = "/adminLogin.api", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public AdminLoginResult adminLogin(@RequestBody AdminLoginDetails ald)
	{
		return als.adminLoginService(ald);
	}
	
	@RequestMapping(path = "/addUser.api", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public RegistrationStatus userRegistration(@RequestBody UserRegistration ul) {
		
		return fss.addUser(ul);
	}
	
	@RequestMapping(path = "/cancelFlight.api", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Status cancelFlight(@RequestBody FlightIdForCancellation fifc) {
		
		return als.cancelFlightAndUpdateInCustomerBooking(fifc);
	}
	
	@RequestMapping(path = "/userLogin.api", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public UserLoginResult userLogin(@RequestBody UserLoginDetails uld)
	{
		return uls.userLoginService(uld);
	}
}

