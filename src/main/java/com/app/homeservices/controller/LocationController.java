package com.app.homeservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.homeservices.services.LocationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/KnocKnock")
public class LocationController {

	@Autowired
	private LocationService locationService;

	public LocationController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/locations")
	public ResponseEntity<?> getAllLocations() {
		System.out.println("--------------------------here in location get---------------------------");
		try {
			return ResponseEntity.ok().body(locationService.getAllLocations());
		} catch (Exception e) {
			return ResponseEntity.ok().body(null);
		}
	}

	@GetMapping("/locations/{id}")
	public ResponseEntity<?> getServicesByLocationId(@PathVariable("id") int id) {
		System.out.println("--------------------------here in location get---------------------------");
		try {
			return ResponseEntity.ok().body(locationService.getLocationById(id).getServices());
		} catch (Exception e) {
			return ResponseEntity.ok().body(null);
		}
	}

}