package com.app.homeservices.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.homeservices.pojos.*;
import com.app.homeservices.services.ServiceProviderService;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/KnocKnock")
public class ServiceProviderController {
	@Autowired 
	ServiceProviderService spService;
	
	
	@GetMapping("/hello")
	public String hello()
	{
		return "Rest is working";
	}
	
	@PostMapping("/registerserviceprovider")
	public Integer registerServiceProvider(@RequestBody DummyJson json)
	{
		System.out.println(json);
		Integer id=spService.registerServiceProvider(new ServiceProvider(json.getName(),json.getUsername(),json.getPassword(),json.getPhoneno()),json.getAddress());
		spService.registerService(id,json.servicename,json.locations,json.ratePerHour,json.visitCharge);
		return id;
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam String username,@RequestParam String password)
	{
		
		ServiceProvider sp=spService.loginServiceProvider(username,password);
		
		System.out.println(sp);
		if(sp!=null)
		{
			return  ResponseEntity.ok().body(sp.getServiceProviderId());
		}
		
		return  ResponseEntity.ok().body(-1);
	}
	
	/*@PostMapping("/registerservice/{id}")
	public ResponseEntity<?> registerService(@RequestBody DummyAddService das,@PathVariable int id)
	{
		
		System.out.println("Id="+id);
		System.out.println(das.servicename);
		System.out.println(das.ratePerHour);
		System.out.println(das.visitCharge);
		System.out.println(das.locations);
		
		 spService.registerService(1,das.servicename,das.locations,das.ratePerHour,das.visitCharge);
		 return new ResponseEntity<>("Service is registered",HttpStatus.OK);
	}*/
	
	@GetMapping("/addlocation/{id}/{location}")
	public ResponseEntity<?> addLocation(@PathVariable String location,@PathVariable int id)
	{
		
		return  ResponseEntity.ok().body(spService.addLocation(id, location));
	}
	
	@GetMapping("/removelocation/{id}/{location}")
	public ResponseEntity<?> removeLocation(@PathVariable String location,@PathVariable int id)
	{
		
		
		return ResponseEntity.ok().body(spService.removeLocation(id, location));
	}
	
	@GetMapping("/visitcharge/{id}/{newVisitCharge}")
	public ResponseEntity<?> updateVisitCharge(@PathVariable float newVisitCharge,@PathVariable int id)
	{
		
		
		return ResponseEntity.ok().body(spService.updateVisitCharge(id, newVisitCharge));
	}
	@GetMapping("/rateperhour/{id}/{newRatePrHour}")
	public ResponseEntity<?> updateRatePrHour(@PathVariable float newRatePrHour,@PathVariable int id)
	{
	
		return ResponseEntity.ok().body(spService.updateRatePrHour(id, newRatePrHour));
	}
	@GetMapping("/phoneno/{id}/{phoneno}")
	public ResponseEntity<?> updatePhoneno(@PathVariable String phoneno,@PathVariable int id)
	{
		
		
		return ResponseEntity.ok().body(spService.updatePhoneNo(id, phoneno));
	}
	@GetMapping("/deleteservice/{id}")
	public ResponseEntity<?> deleteService(@PathVariable int id)
	{
		
		
		return ResponseEntity.ok().body(spService.deleteService(id));
	}
	
	@GetMapping("/workcompleted/{bookingId}/{hours}")
	public ResponseEntity<?> workCompleted(@PathVariable int bookingId,@PathVariable int hours)
	{
		
		
		return ResponseEntity.ok().body(spService.workCompleted(bookingId,hours));
	}
	@GetMapping("/currentBooking/{id}")
	public ResponseEntity<?> getBookingDetails(@PathVariable int id)
	{
		
	
		return ResponseEntity.ok().body(spService.getCurrentBooking(id));
	}
	@GetMapping("/history/{id}")
	public ResponseEntity<?> getHistoryOfBooking(@PathVariable int id)
	{

	
		
		return ResponseEntity.ok().body(spService.getHistoryOfBooking(id));
	}
	
	@GetMapping("/notregisteredlocation/{id}")
	public ResponseEntity<?> getNotRegisteredLocations(@PathVariable int id)
	{
		
		return ResponseEntity.ok().body(spService.getNotRegisteredLocations(id));
		
	}
	@GetMapping("/getserviceproviderbyid/{id}")
	public ResponseEntity<?>  getServiceProviderById(@PathVariable int id)
	{
		ServiceProvider sp=spService.getServiceProviderById(id);
		return ResponseEntity.ok().body(sp);
		
	}
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@GetMapping("serviceproviderid/{ser_pro_id}")
	public ResponseEntity<?> getBookingById(@PathVariable int ser_pro_id)
	{
		try{
			System.out.println("in service provider");
			ServiceProvider details=service.getServiceProviderById(ser_pro_id);
			System.out.println("deatils "+details);
			return new ResponseEntity<>(details,HttpStatus.OK);
		}catch(Exception e)
		{
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		}
	}*/
	
	/*@PutMapping("serviceprovider/{ser_pro_id}/{working_status}")
	public ResponseEntity<?> updateWorkingStatus(@PathVariable int ser_pro_id,@PathVariable int working_status)
	{
		try{
			System.out.println("in service provider update status");
			System.out.println("working status"+working_status);
			System.out.println("pro_id"+ser_pro_id);
			service.updateServiceProviderWorkingStatus(working_status, ser_pro_id);
			return new ResponseEntity<>("Service provider Status updated",HttpStatus.OK);
		}catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
		}
	}
*/
