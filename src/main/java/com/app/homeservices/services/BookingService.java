package com.app.homeservices.services;

import java.util.Date;
import java.util.List;

import com.app.homeservices.pojos.Booking;

public interface BookingService {
	
	public int saveBooking(Booking book);	
	public Booking getBookingById(int booking_id);
	public void UpdateBookingHours(int hours,int booking_id);
	public void UpdateBookingStatus(int status,int booking_id);
	public Booking getBookingByCustomerId(int customer_id);
	public Booking getBookingByServiceProId(int serviceprovider_id);
	public int getServiceProviderIdByBookingId(int bookingId);
	public Integer getAllServiceProvidersIdNotBookedAtDate(int serviceProviderId,Date date);
	public List<Booking> getCurrentBookingDetailsByCustomerId(int id);
	public List<Booking> getHistoryOfBookingDetailsByCustomerId(int id);

}
