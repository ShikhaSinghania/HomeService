package com.app.homeservices.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.homeservices.pojos.Admin;
import com.app.homeservices.pojos.DummyAddLocation;
import com.app.homeservices.services.AdminService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admincontroller")
public class AdminController {
	@Autowired
	AdminService adminservice;
	
	@PostMapping("/adminLogin")
	public ResponseEntity<?>  adminLogin(@PathVariable String username,@PathVariable String password)
	{
		
		Admin admin=adminservice.loginAdmin(username, password);
		if(admin!=null)
			return new ResponseEntity<>(admin, HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		
	}
	
	@PostMapping("/addlocation")
	public ResponseEntity<?> addLocation(@RequestBody DummyAddLocation json)
	{
		System.out.println(json);
		Integer id=adminservice.addLocation(json.locationname,json.services);
		return new ResponseEntity<>("Location added with Id"+id,HttpStatus.OK);
	}
	@DeleteMapping("/deletelocation/{locationname}")
	public ResponseEntity<?> deleteLocation(@PathVariable String locationname)
	{
		System.out.println(locationname);
		if(adminservice.deleteLocation(locationname))
		return new ResponseEntity<>("Location is deleted successfully",HttpStatus.OK);
		else return new ResponseEntity<>("Location is not deleted",HttpStatus.EXPECTATION_FAILED);
	}
	@PostMapping("/addservice")
	public ResponseEntity<?> addService(@RequestBody DummyAddLocation json)
	{
		System.out.println(json);
		Integer id=adminservice.addService(json.servicename,json.locations);
		return new ResponseEntity<>("Service added with Id"+id,HttpStatus.OK);
	}
	
/*	@PostMapping("/deleteservice")
	public ResponseEntity<?> deleteService(@RequestBody DummyAddLocation json)
	{
		System.out.println(json);
		String msg=adminservice.deleteService(json.servicename);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
*/
	
	
	
}
