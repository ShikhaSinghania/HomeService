package com.app.homeservices.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.app.homeservices.pojos.Booking;

@Repository

public interface BookingDao extends JpaRepository<Booking, Integer>{

	@Transactional
	@Modifying
	@Query(value="update booking  set working_hours=?1 where booking_id=?2",nativeQuery=true)
	public void setBookingWorkingHours(int hours,int booking_id);
	

	@Transactional
	@Modifying
	@Query(value="update booking  set status=?1 where booking_id=?2",nativeQuery=true)
	public void setBookingWorkingStatus(int status,int booking_id);
	
	//consider later
	@Query("select b from Booking b where b.serviceProviderId=?1 and b.status=1")
	Booking findBookingDetailsByServiceProviderId(int id);

	@Query("select b from Booking b where b.serviceProviderId=?1 and b.status=1")
	List<Booking> getCurrentBookingDetails(int id);
	
	@Query("select b from Booking b where b.serviceProviderId=?1 and b.status=0")
	List<Booking> getHistoryOfBooking(int id);
	
	@Query("select b from Booking b where b.customerId=?1 and b.status=1")
	List<Booking> getCurrentBookingDetailsByCustomerId(int id);
	
	@Query("select b from Booking b where b.customerId=?1 and b.status=0" )
	List<Booking> getHistoryOfBookingDetailsByCustomerId(int id);
	
	@Query(value="select distinct service_provider_id from booking where service_provider_id=?1 and booking_date=?2",nativeQuery=true)
	public Integer getAllServiceProvidersIdNotBookedAtDate(int serviceprovider,Date date);
	
	
}
