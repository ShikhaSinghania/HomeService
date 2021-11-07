package com.app.homeservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.homeservices.dao.AddressDao;
import com.app.homeservices.dao.BookingDao;
import com.app.homeservices.dao.CustomerDao;
import com.app.homeservices.pojos.Address;
import com.app.homeservices.pojos.Booking;
import com.app.homeservices.pojos.Customer;

@Service
public class CustomerServiceImp implements CustomerService {
	
	@Autowired
	private CustomerDao dao;
	
	@Autowired
	private BookingDao daoBook;
	
	@Autowired
	private AddressDao addDao;
	
	
	public CustomerServiceImp() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public long registerUser(Customer user, Address address) {
		System.out.println("KnockKnockServicesImp:in registerUser()");
		user.addAddress(address);
		return dao.save(user).getUserId();
	}

	@Override
	public Customer loginCustomer(String email, String password) {
		System.out.println("KnockKnockServicesImp:in loginCustomer()");
		return dao.login(email, password);
	}

	@Override
	public Customer getCustomerById(int customerId) {
		System.out.println("KnockKnockServicesImp:in getCustomerById()");
		return dao.getCustomerDetailsById(customerId);
	}

	@Override
	public List<Booking> getCurrentBooking(int customerId) {
		System.out.println("KnockKnockServicesImp:in getCurrentBooking()");
		List<Booking> b=daoBook.getCurrentBookingDetailsByCustomerId(customerId);
		//System.out.println("getCurrentBooking  "+b);
		return b;
	}

	@Override
	public List<Booking> getHistoryOfBooking(int customerId) {
		System.out.println("KnockKnockServicesImp:in getHistoryOfBooking()");
		return daoBook.getHistoryOfBookingDetailsByCustomerId(customerId);
	}


	@Override
	public Customer getAddressListByCustomerId(int customerId) {
		return dao.getAddressListByCustomerId(customerId);
	}

	@Override
	public Integer addAddress(Address address, int customerId) {
		Customer customer=dao.getCustomerDetailsById(customerId);
		customer.addAddress(address);
		dao.save(customer);
		return customerId;
	}

	@Override
	public Integer removeAddress(int addressId, int customerId) {
		Customer customer=dao.getCustomerDetailsById(customerId);
		 Address address=addDao.findById(addressId).get();
		customer.removeAddress(address);
		dao.save(customer);
		return customerId;
	}

}
