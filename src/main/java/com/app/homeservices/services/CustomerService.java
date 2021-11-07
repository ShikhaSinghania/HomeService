package com.app.homeservices.services;

import java.util.List;

import com.app.homeservices.pojos.Address;
import com.app.homeservices.pojos.Booking;
import com.app.homeservices.pojos.Customer;

public interface CustomerService {
	long registerUser(Customer user,Address address);
	Customer loginCustomer(String email,String password); 
	Customer getCustomerById(int customerId);
	List<Booking> getCurrentBooking(int customerId);
	List<Booking> getHistoryOfBooking(int customerId);
	Customer getAddressListByCustomerId(int customerId);
	Integer addAddress(Address address,int customerId);
	Integer removeAddress(int addressId,int customerId);
}
