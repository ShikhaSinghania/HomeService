package com.app.homeservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.homeservices.dao.LocationDao;
import com.app.homeservices.pojos.Location;

@Service
public class LocationServiceImp implements LocationService {

	@Autowired
	private LocationDao locationDao;
	
	public LocationServiceImp() {
	}
	
	@Override
	public List<Location> getAllLocations() {
		return locationDao.findAll();
	}

	@Override
	public Location getLocationById(int id) {
		return locationDao.findById(id).get();
	}

}
