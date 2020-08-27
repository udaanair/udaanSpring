package com.lti.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.SeatParameters;
import com.lti.entity.BookingRecord;
import com.lti.entity.Flights;

@Component("bookingRecord")
public class BookingAndSeatsDAOImpl implements BookingAndSeatsDAO{

	@PersistenceContext	//@Autowired does not work for injecting EntityManager
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void booking(BookingRecord records) {
		entityManager.persist(records);
		if(records.getCabinClass().equalsIgnoreCase("economy"))
		{
			String query="update Flights f set f.economySeatsLeft=f.economySeatsLeft- :n1 where f.flightId= :n2";
			int x=entityManager.createQuery(query).setParameter("n1", records.getCustomers().size()).setParameter("n2", records.getFlightId()).executeUpdate();
			System.out.println(x);
		}
		else
		{
			String query="update Flights f set f.businessSeatsLeft=f.businessSeatsLeft- :n1 where f.flightId= :n2";
			int x=entityManager.createQuery(query).setParameter("n1", records.getCustomers().size()).setParameter("n2", records.getFlightId()).executeUpdate();
			System.out.println(x);
		}
		
		
		
	}

	@Override
	public List<Integer> bookedSeatsList(SeatParameters sp) {
		String query="select c.seatNumber from CustomerBookingRecord c "
				+ "where c.bookingPnr.pnr IN (select b.pnr from BookingRecord b where "
				+ "b.flightId= :f1) and c.status='Booked'";
		//select seatnumber from customerbookingrecord
		//where pnr IN (select pnr from booking_record where flightid=1) and status='booked';

		List<Integer> li=entityManager.createQuery(query).setParameter("f1", sp.getFlightNumber()).getResultList();
		
		return li;
	}

	@Override
	public int getPNR(long userID, Date dateTimeOfBooking) {
		System.out.println(dateTimeOfBooking);
		String query="select b.pnr from BookingRecord b where b.userId= :u1 and b.dateOfBooking= :d1";
		
		List<Integer> li=entityManager.createQuery(query).setParameter("u1", userID).setParameter("d1", dateTimeOfBooking).getResultList();
		if(li.size()>0)
			return li.get(0);
		
		return 0;
	}

}
