package com.app.homeservices.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.homeservices.dao.AdminDao;
import com.app.homeservices.dao.BookingDao;
import com.app.homeservices.dao.LocationDao;
import com.app.homeservices.dao.ServiceDao;
import com.app.homeservices.dao.ServiceProviderDao;
import com.app.homeservices.pojos.Admin;
import com.app.homeservices.pojos.Location;
import com.app.homeservices.pojos.ServiceProvider;
import com.app.homeservices.pojos.Services;

@Service
public class AdminServiceImpl implements AdminService {
@Autowired
AdminDao daoadmin;
@Autowired
BookingDao daobook;
@Autowired
LocationDao daoloc;
@Autowired 
ServiceDao daoservice;

@Autowired
ServiceProviderDao daosp;
/*@Override
public Integer registerAdmin(String name, String username, String password) {
	Admin admin=new Admin(name, username, password);
	return daoadmin.save(admin).getId();
	 
}*/
@Override
public Admin loginAdmin(String username,String password) {
	return daoadmin.findByUsernameAndPassword(username, password);
}
@Override
public Integer addLocation(String locationname,List<String> services) {
		Location loc=new Location(locationname);
		Set<Services> setservices=new HashSet<>();
		for(String ser:services)
		{
			setservices.add(daoservice.findByServiceName(ser));
		}
		loc.addLocationWithServices(setservices);
		return daoloc.save(loc).getId();
		
		
}
@Override
public boolean deleteLocation(String locationname)
{
	
	Location loc=daoloc.findByLocationName(locationname);
	if(loc!=null)
	{
	loc.deleteLocationWithServices();
	daoloc.delete(loc);
		return true;
	}
	else return false;
}

@Override
public Integer addService(String servicename,List<String> locations)
{

	Services service=new Services(servicename);
	Set<Location> setlocations=new HashSet<>();
	for(String locs:locations)
	{
		
		setlocations.add(daoloc.findByLocationName(locs));
	}
	service.addServiceWithLocations(setlocations);
	return daoservice.save(service).getServiceId();
	 
}
/*public String deleteService(String servicename)
{
	Services service=daoservice.findByServiceName(servicename);
	if(service!=null)
	{
		service.deleteService();
		daoservice.delete(service);
		return servicename +"is deleted";
	}
	
	return servicename +"is not deleted";
}
*/
public List<Services> getAllServices()
{
	return daoservice.findAll();
}	

public List<ServiceProvider> getAllServiceProvider()
{
	return daosp.findAll();
}
public List<Location> getAllLocations()
{
	return daoloc.findAll();
}

public List<ServiceProvider> getAuthenticatedServiceProvider()
{
	return daosp.getAllAuthenticatedServiceProvider();
}
public List<ServiceProvider> getNotAuthenticatedServiceProvider()
{
	return daosp.getNotAuthenticatedServiceProvider();
}
	
public boolean authenticateServiceProvider(int id)
{
	Optional<ServiceProvider> opt=daosp.findById(id);
	if(opt!=null)
	{
		ServiceProvider sp=opt.get();
		sp.setAuthenticStatus(1);
		daosp.save(sp);
		return true;
	}
	 return false;
}

public boolean deleteServiceProvider(int id)
{
	Optional<ServiceProvider> opt=daosp.findById(id);
	if(opt!=null)
	{
		ServiceProvider sp=opt.get();
		
		sp.deleteServiceprovider();
		daosp.delete(sp);
		return true;
	}
	return false;
}

}
