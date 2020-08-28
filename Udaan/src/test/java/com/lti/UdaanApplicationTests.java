package com.lti;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.lti.dao.AdminDAO;
import com.lti.dao.BookingAndSeatsDAO;
import com.lti.dao.FlightsDAO;
import com.lti.dao.UserLoginDAO;
import com.lti.dto.AdminLoginDetails;
import com.lti.dto.SearchParameters;
import com.lti.dto.SeatParameters;
import com.lti.dto.UserLoginDetails;
import com.lti.entity.AdminRegistration;
import com.lti.entity.BookingRecord;
import com.lti.entity.CustomerBookingRecord;
import com.lti.entity.Flights;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class UdaanApplicationTests {

	@Autowired
	private FlightsDAO fdi;
	
	@Autowired
	private BookingAndSeatsDAO basd;
	
	@Autowired
	private AdminDAO ad;
	
	@Autowired
	private UserLoginDAO uld;
	
	@Test
	void contextLoads() throws ParseException {
		
		SearchParameters sp=new SearchParameters();
		sp.setCabinSelected("economy");
		sp.setSource("Dehradun");
		sp.setDestination("Bangalore");
		sp.setNumberOfPassengers(3);
		sp.setDateOfFlight((new SimpleDateFormat("yy-MM-dd")).parse("20-10-20"));
		List<Flights> li=fdi.getEconomySearchResult(sp);
		for(Flights fl:li)
		{
			System.out.println(fl);
		}
	}
	
	@Test
	void testForBookingImpl()
	{
		Date dt=new Date();
		System.out.println(dt);
		BookingRecord bd=new BookingRecord(dt,22,9648557255L,"economy",10000,0);
		List<CustomerBookingRecord> li=new ArrayList<CustomerBookingRecord>();
		li.add(new CustomerBookingRecord("Kunwar Singh",23,"adult",6,4000,"Booked",bd));
		li.add(new CustomerBookingRecord("Yash K",22,"adult",7,4000,"Booked",bd));
		li.add(new CustomerBookingRecord("Rajat S",24,"adult",10,4000,"Booked",bd));
		
		bd.setCustomers(li);
		
		basd.booking(bd);
		
		int pnr=basd.getPNR(bd.getUserId(), dt);
		System.out.println(pnr);
	}
	
	@Test
	void testForSeatsBooked()
	{
		SeatParameters sp=new SeatParameters();
		sp.setFlightNumber(22);
		List<Integer> li=basd.bookedSeatsList(sp);
		for(int x: li)
		{
			System.out.println(x);
		}
			
	}

	@Test
	void cabinTypeByFlightID()
	{
		//ArrayList<String> li=(ArrayList<String>) fdi.getCabinType(22);
		//List<String> li=fdi.getCabinType(22);
		String type=fdi.getCabinType(22).get(0);
		System.out.println(type);
			
	}
	
	@Test
	void testForAdminEntry()
	{
		AdminRegistration admin=new AdminRegistration("kunwar0902", "Kunwar Uday Singh", "123456"); 
		ad.addAdmin(admin);
			
	}
	
	@Test
	void testForAdminLogin()
	{
		AdminLoginDetails ald=new AdminLoginDetails();
		ald.setUserName("kunwar0902");
		ald.setPassword("12345");
		String s=ad.login(ald);
		if(s==null)
		{
			System.out.println("null returned");
		}
			
	}
	
	@Test
	void testForUserLogin()
	{
		UserLoginDetails ald=new UserLoginDetails();
		ald.setUserName(9648557255L);
		ald.setPassword("0608196");
		String s=uld.login(ald);
		if(s==null)
		{
			System.out.println("null returned");
		}
			
	}
	
	@Test
	void testForCancelFlight()
	{
		
		ad.cancelFlight(21);
			
	}
}


