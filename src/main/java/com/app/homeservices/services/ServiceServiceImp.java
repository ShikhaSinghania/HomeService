package com.app.homeservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.homeservices.dao.ServiceDao;
import com.app.homeservices.pojos.Services;

@Service
public class ServiceServiceImp implements ServiceService {

	@Autowired
	private ServiceDao serviceDao;
	
	public ServiceServiceImp() {
		System.out.println("ServiceServiceImp:In Constructor");
	}

	@Override
	public Services getServiceById(int id) {
		return serviceDao.findById(id).get();
	}
	
	@Override
	public List<Services> getAllServices(){
		return serviceDao.findAll();
	}

}
