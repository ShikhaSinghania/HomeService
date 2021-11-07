package com.app.homeservices.services;

import java.util.List;
import java.util.Set;

import com.app.homeservices.pojos.Address;
import com.app.homeservices.pojos.Booking;
import com.app.homeservices.pojos.Location;
import com.app.homeservices.pojos.ServiceProvider;

public interface ServiceProviderService {

	ServiceProvider getServiceProviderById(int serviceproviderid);
	void updateServiceProviderWorkingStatus(int working_status,int service_provider_id);
	
	Integer registerServiceProvider(ServiceProvider sp,Address adr);
	ServiceProvider loginServiceProvider(String username,String Password);
	ServiceProvider registerService(int id,String servicename,Set<String> locations,float ratePerHr,float VisitCharge);
	int addLocation(int id,String location);
	int removeLocation(int id, String location);
	int updateVisitCharge(int id,float newVisitCharge);
	int updateRatePrHour(int id, float newRatePrHour);
	ServiceProvider getDetailsOfService(int id);
	int deleteService(int id);
	List<Booking> getCurrentBooking(int id);
	List<Booking> getHistoryOfBooking(int id);
	int workCompleted(int bookingId,int hours);
	List<Location> getNotRegisteredLocations(int id);
	int updatePhoneNo(int id,String num);
	//String unsubscribeMe(int id);
	List<ServiceProvider> getAuthenticatedServiceProvidersByServiceId(int serviceId);
	List<ServiceProvider> getAllAuthenticatedServiceProvider();
}
