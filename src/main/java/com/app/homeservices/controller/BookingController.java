 package com.app.homeservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.homeservices.pojos.Address;
import com.app.homeservices.pojos.Booking;
import com.app.homeservices.services.AddressService;
import com.app.homeservices.services.BookingService;
import com.app.homeservices.services.ServiceProviderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/KnocKnock")
public class BookingController {

	@Autowired
	private BookingService service;
	
	@Autowired
	private ServiceProviderService ser_pro_service;
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/booking/{addressId}")
	public  ResponseEntity<?> saveBooking(@RequestBody Booking booking,@PathVariable int addressId)
	{
		
			System.out.println("json data is="+ booking);
			int workingStatus=1;
			Address address=addressService.getAddressByAddressId(addressId);
			int bookingId=service.saveBooking(booking);
			Booking book=service.getBookingById(bookingId);
			book.setRelatedAddresse(address);
			int ser_pro_id=service.getServiceProviderIdByBookingId(bookingId);
			service.UpdateBookingStatus(workingStatus, bookingId);
			System.out.println("working status"+ workingStatus);
			System.out.println("pro_id"+ser_pro_id);
			ser_pro_service.updateServiceProviderWorkingStatus( workingStatus, ser_pro_id);
			return ResponseEntity.ok().body(bookingId);
			//return "redirect:/KnocKnock/serviceprovider/" + ser_pro_id+"/"+1;
			//return new ResponseEntity<>(booking_id,HttpStatus.OK);
		
	}
	
	@GetMapping("/bookingid/{booking_id}")
	public ResponseEntity<?> getBookingById(@PathVariable int booking_id)
	{
		try{
			
			Booking details=service.getBookingById(booking_id);
			System.out.println("deatils "+details);
			//return new ResponseEntity<>(details,HttpStatus.OK);
			return ResponseEntity.ok().body(details);
		}catch(Exception e)
		{
			return ResponseEntity.ok().body(null);
		}
	}
	
/*	@PutMapping("bookinghours/{booking_id}/{hours}")
	public void UpdateBookingHours(@PathVariable int booking_id,@PathVariable int hours)
	{
	try{
			service.UpdateBookingHours(hours, booking_id);
			System.out.println("Booking hours updated");
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@PutMapping("bookingstatus/{booking_id}/{status}")
	public void UpdateBookingStatus(@PathVariable int booking_id,@PathVariable int status)
	{
	try{
			service.UpdateBookingStatus(status, booking_id);
			System.out.println("Booking status updated");
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}*/
	
	@GetMapping("/customerbook/{customer_id}")
	public ResponseEntity<?> getBookingByCustomerId(@PathVariable int customer_id)
	{
		try{
			
			Booking details=service.getBookingByCustomerId(customer_id);
			System.out.println("deatils "+details);
			return ResponseEntity.ok().body(details);
		}catch(Exception e)
		{
			return ResponseEntity.ok().body(null);
		}
	}
	
	@GetMapping("/currentcustomerbook/{customer_id}")
	public ResponseEntity<?> getCurrentBookingDetailsByCustomerId(@PathVariable int customer_id)
	{
		try{
			System.out.println("in booking current customer");
			List<Booking> details=service.getCurrentBookingDetailsByCustomerId(customer_id);
			System.out.println("deatils "+details);
			return ResponseEntity.ok().body(details);
		}catch(Exception e)
		{
			return ResponseEntity.ok().body(null);
		}
	}
	
	@GetMapping("/historycustomerbook/{customer_id}")
	public ResponseEntity<?> getHistoryBookingDetailsByCustomerId(@PathVariable int customer_id)
	{
		try{
			
			List<Booking> details=service.getHistoryOfBookingDetailsByCustomerId(customer_id);
			System.out.println("deatils "+details);
			return ResponseEntity.ok().body(details);
		}catch(Exception e)
		{
			return ResponseEntity.ok().body(null);
		}
	}
	
	
	/*@GetMapping("serviceproviderbook/{serviceprovider_id}")
	public ResponseEntity<?> getBookingByServiceProviderId(@PathVariable int serviceprovider_id)
	{
		try{
			
			Booking details=service.getBookingByServiceProId(serviceprovider_id);
			System.out.println("deatils "+details);
			return new ResponseEntity<>(details,HttpStatus.OK);
		}catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
		}
	}*/
	

}