package com.app.homeservices.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.homeservices.dao.BookingDao;
import com.app.homeservices.dao.LocationDao;
import com.app.homeservices.dao.ServiceDao;
import com.app.homeservices.dao.ServiceProviderDao;
import com.app.homeservices.pojos.Address;
import com.app.homeservices.pojos.Booking;
import com.app.homeservices.pojos.Location;
import com.app.homeservices.pojos.ServiceProvider;
import com.app.homeservices.pojos.Services;

@Service
public class ServiceProviderServiceImpl implements ServiceProviderService {


	
	@Autowired
	ServiceProviderDao daosp;
		
	@Autowired
	ServiceDao daoserv;

	@Autowired
	LocationDao daoloc;

	@Autowired 
	BookingDao daobook;
	
	
	public ServiceProvider getServiceProviderById(int serviceproviderid)
	{
		return daosp.findById(serviceproviderid).get();
	}


	@Override
	public void updateServiceProviderWorkingStatus(int working_status, int service_provider_id) {
		 daosp.updateServiceProviderWoringStatus(working_status, service_provider_id);
	}
	


		@Override
		public Integer registerServiceProvider(ServiceProvider sp,Address adr) {
				sp.addAddress(adr);
				ServiceProvider s=daosp.save(sp);
			return s.getServiceProviderId();
		}

		@Override
		public ServiceProvider loginServiceProvider(String username,String password) {
			return daosp.findByUsernameAndPassword(username, password);
			
		}

		@Override
		public ServiceProvider registerService(int id, String servicename, Set<String> locations, float ratePerHr, float visitCharge) {
			Optional<ServiceProvider> opt=daosp.findById(id);
			Services service=daoserv.findByServiceName(servicename);
			Set<Location> locs=new HashSet<>();
			for(String l:locations)
			{
				Location location=daoloc.findByLocationName(l);
				locs.add(location);
			}
			if(opt!=null)
			{
				ServiceProvider sp=opt.get();
				sp.addService(service);
				sp.addLocations(locs);
				sp.setRatePerHour(ratePerHr);
				sp.setVisitCharge(visitCharge);
				return daosp.save(sp);
			}
			return null;
		}

		public int addLocation(int id,String location)
		 {
			Optional<ServiceProvider> opt= daosp.findById(id);
			if(opt!=null)
			{
			 ServiceProvider sp=opt.get();
			 Location loc=daoloc.findByLocationName(location);
			 sp.addOneLocation(loc);
			 daosp.save(sp);
			 return 1;
			}
			 return 0;
		 }
		public List<Location> getNotRegisteredLocations(int id)
		{
			ServiceProvider sp=daosp.getServiceProviderDetails(id);
			List<Location> loclist=daoloc.findAll();
			if(sp==null){
				return loclist;
			}
			Set<Location> locset=sp.getLocations();
			List<Location> locations=new ArrayList<>();
			for(Location ll:loclist)
			{int count=0;
				for(Location ls:locset)
				{
					if(ll.getLocation().equals(ls.getLocation()))
					{
						count=1;
						break;
					}
						
				}
				if(count==0)
				locations.add(ll);
				
			}
			return locations;
		}
		
		
		@Override
		public int removeLocation(int id, String location)
		{
			Optional<ServiceProvider> opt= daosp.findById(id);
			if(opt!=null)
			{
			 ServiceProvider sp=opt.get();
			 Location loc=daoloc.findByLocationName(location);
			 sp.removeOneLocation(loc);
			 daosp.save(sp);
			 return 1;
			}
			 return 0;
		}
		
		@Override
		public int updatePhoneNo(int id,String phoneno)
		{
			Optional<ServiceProvider> opt=daosp.findById(id);
			if(opt!=null)
			{
			ServiceProvider sp=opt.get();
			sp.setPhoneno(phoneno);
			daosp.save(sp);
			return 1;
			}
			return 0;
			
		}
		
		
		
		
		@Override
		public int updateVisitCharge(int id, float newVisitCharge) {
			Optional<ServiceProvider> opt=daosp.findById(id);
			if(opt!=null)
			{
			ServiceProvider sp=opt.get();
			sp.setVisitCharge(newVisitCharge);
			 daosp.save(sp);
			 return 1;
			}
			return 0;
		}
		 
		@Override
		public int updateRatePrHour(int id, float newRatePrHour) {
			Optional<ServiceProvider> opt=daosp.findById(id);
			if(opt!=null)
			{
			ServiceProvider sp=opt.get();
			sp.setRatePerHour(newRatePrHour);
			 daosp.save(sp);
			 return 1;
			}
			return 0;
		}
		
		
		
		public ServiceProvider getDetailsOfService(int id)
		{
			ServiceProvider sp=daosp.getServiceProviderDetails(id);
			if(sp.getServiceProviderId()!=0)
			return new ServiceProvider(sp.getService().getServiceId(),sp.getService().getServiceName(),sp.getLocations());
			return null;
					
		
		}
		
		
		public int deleteService(int id)
		{
				Booking b=daobook.findBookingDetailsByServiceProviderId(id);
				if(b==null)
				{
					ServiceProvider sp=daosp.getServiceProviderDetails(id);
						Services service=daoserv.findByServiceName(sp.getService().getServiceName());
						sp.removeService(service);
						sp.removeLocations(sp.getLocations());
						sp.setRatePerHour(0);
						sp.setVisitCharge(0);
						daosp.save(sp);
						return 1;
				}
			return 0;
		}
		
		
		@Override
		public int workCompleted(int bookingId,int hours) {
		
			Booking booking=daobook.findById(bookingId).get();
			booking.setWorkingHours(hours);
			booking.setStatus(0);
			int serviceProviderId=booking.getServiceProviderId();
			ServiceProvider serviceProvider= daosp.findById(serviceProviderId).get();
			serviceProvider.setWorkingStatus(0);
			booking.setFinalPrice(hours*serviceProvider.getRatePerHour()+serviceProvider.getVisitCharge());
			daobook.save(booking);
			daosp.save(serviceProvider);
			return bookingId;
		}
		
		
		public List<Booking> getCurrentBooking(int id)
		{
			return daobook.getCurrentBookingDetails(id);
			
		}
		
		
		 public List<Booking> getHistoryOfBooking(int id)
		 {
			 return daobook.getHistoryOfBooking(id);
		 }

		 @Override
			public List<ServiceProvider> getAuthenticatedServiceProvidersByServiceId(int serviceId) {
				return daosp.getAuthenticatedServiceProvidersByServiceId(serviceId);
			}


		@Override
		public List<ServiceProvider> getAllAuthenticatedServiceProvider() {
			return daosp.getAllAuthenticatedServiceProvider();
			 
		}
		 
	
}
