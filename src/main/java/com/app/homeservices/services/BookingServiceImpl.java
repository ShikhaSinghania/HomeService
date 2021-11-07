package com.app.homeservices.services;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.homeservices.dao.BookingDao;
import com.app.homeservices.pojos.Booking;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao dao;

	@Override
	public int saveBooking(Booking book) {
		return dao.save(book).getBookingId();
		
	}

	@Override
	public Booking getBookingById(int booking_id) {
		return dao.findById(booking_id).get();
	
	}

	@Override
	public void UpdateBookingHours(int hours, int booking_id) {
	dao.setBookingWorkingHours(hours, booking_id);
		
	}

	@Override
	public void UpdateBookingStatus(int status, int booking_id) {
		dao.setBookingWorkingStatus(status, booking_id);
		
	}

	@Override
	public Booking getBookingByCustomerId(int customer_id) {
		return dao.findById(customer_id).get();
	}

	@Override
	public Booking getBookingByServiceProId(int serviceprovider_id) {
		return dao.findById(serviceprovider_id).get();
	}

	@Override
	public int getServiceProviderIdByBookingId(int bookingId) {
		return dao.findById(bookingId).get().getServiceProviderId();
	}
	
	@Override
	public Integer getAllServiceProvidersIdNotBookedAtDate(int serviceProviderId,Date date) {
		return dao.getAllServiceProvidersIdNotBookedAtDate( serviceProviderId,date);
		
	}

	@Override
	public List<Booking> getCurrentBookingDetailsByCustomerId(int id) {
		return dao.getCurrentBookingDetailsByCustomerId(id);
	}

	@Override
	public List<Booking> getHistoryOfBookingDetailsByCustomerId(int id) {
		return dao.getHistoryOfBooking(id);
	}
	
	
}
