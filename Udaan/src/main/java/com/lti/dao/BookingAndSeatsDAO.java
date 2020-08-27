package com.lti.dao;

import java.util.Date;
import java.util.List;

import com.lti.dto.SeatParameters;
import com.lti.entity.BookingRecord;

public interface BookingAndSeatsDAO {

	public void booking(BookingRecord records);
	
	public List<Integer> bookedSeatsList(SeatParameters sp);
	
	public int getPNR(long userID, Date dateTimeOfBooking);
}
