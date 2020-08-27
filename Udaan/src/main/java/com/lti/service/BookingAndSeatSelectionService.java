package com.lti.service;

import java.util.List;

import com.lti.dto.AvailableSeatsListClass;
import com.lti.dto.ResultOfBooking;
import com.lti.dto.SeatParameters;
import com.lti.entity.BookingRecord;

public interface BookingAndSeatSelectionService {

	public AvailableSeatsListClass getAvailableSeatsList(SeatParameters sp);
	
	public ResultOfBooking addBookingDetails(BookingRecord br);
}
