package com.app.homeservices.services;

import java.util.List;

import com.app.homeservices.pojos.Location;

public interface LocationService {
	List<Location> getAllLocations();
	Location getLocationById(int id);
}
