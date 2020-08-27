package com.lti.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.BookingAndSeatsDAO;
import com.lti.dao.FlightsDAO;
import com.lti.dto.AvailableSeatsListClass;
import com.lti.dto.ResultOfBooking;
import com.lti.dto.SeatParameters;
import com.lti.entity.BookingRecord;
import com.lti.entity.CustomerBookingRecord;

@Service
@Transactional
public class BookingAndSeatSelectionServiceImpl implements BookingAndSeatSelectionService{

	@Autowired
	private BookingAndSeatsDAO basd;
	
	@Autowired
	private FlightsDAO fd;
	
	@Override
	public AvailableSeatsListClass getAvailableSeatsList(SeatParameters sp) {
		List<Integer> availableSeats= new ArrayList<Integer>();
		
		String cabinTypeOfFlight=fd.getCabinType(sp.getFlightNumber()).get(0);
		System.out.println(cabinTypeOfFlight);
		System.out.println(sp.getFlightNumber());
		List<Integer> bookedSeats=basd.bookedSeatsList(sp);
		for(int x:bookedSeats)
		{
			System.out.println(x);
		}
		if(sp.getCabinClass().equalsIgnoreCase("economy"))
		{
			if(cabinTypeOfFlight.equalsIgnoreCase("economy"))//user asked for economy and flight type also economy
			{
				for(int i=1;i<=60;i++)
				{
					if(!(bookedSeats.contains(i)))
					{
						availableSeats.add(i);
					}
				}
			}
			else////user asked for economy and flight type is business
			{
				for(int i=1;i<=40;i++)
				{
					if(!(bookedSeats.contains(i)))
					{
						availableSeats.add(i);
					}
				}
			}
		}
		else//business demand by user and business type flights also
		{
			for(int i=41;i<=60;i++)
			{
				if(!(bookedSeats.contains(i)))
				{
					availableSeats.add(i);
				}
			}
		}
		for(int x:availableSeats)
		{
			System.out.print(x);
		}
		System.out.println();
		AvailableSeatsListClass aslc=new AvailableSeatsListClass();
		aslc.setAvailableSeats(availableSeats);
		return aslc;
	}

	@Override
	public ResultOfBooking addBookingDetails(BookingRecord br) {
		ResultOfBooking rob=new ResultOfBooking();
		
		Date dt=new Date();
		br.setDateOfBooking(dt);
		br.setRefundAmount(0);
		List<CustomerBookingRecord> li=br.getCustomers();
		for(CustomerBookingRecord csr: li)
		{
			csr.setBookingPnr(br);
			if(csr.getAge()>17)
			{
				csr.setType("Adult");
			}
			else
			{
				csr.setType("Children");
			}
			csr.setStatus("Booked");
		}
		
		basd.booking(br);
		
		rob.setPnrGenerated(basd.getPNR(br.getUserId(), dt));
		rob.setStatus("Ticket Done Successfully. Your PNR is "+rob.getPnrGenerated());
		
		return rob;
		
	}

}
