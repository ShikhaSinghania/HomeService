package com.app.homeservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.homeservices.dao.AddressDao;
import com.app.homeservices.pojos.Address;


@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao dao;
	
	@Override
	public Address getAddressByAddressId(int addressId) {
		return dao.findById(addressId).get();
	}
}
