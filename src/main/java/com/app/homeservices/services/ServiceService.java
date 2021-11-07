package com.app.homeservices.services;

import java.util.List;

import com.app.homeservices.pojos.Services;

public interface ServiceService {
	Services getServiceById(int id);
	List<Services> getAllServices();
}
