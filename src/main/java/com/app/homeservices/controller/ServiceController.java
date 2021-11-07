package com.app.homeservices.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.homeservices.pojos.ServiceProvider;
import com.app.homeservices.services.BookingService;
import com.app.homeservices.services.ServiceProviderService;
import com.app.homeservices.services.ServiceService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/KnocKnock")
public class ServiceController {

	@Autowired
	private ServiceService servicesService;
	
	@Autowired
	private BookingService serviceBook;
	
	@Autowired
	private ServiceProviderService serviceServiceProvider;

	public ServiceController() {
		System.out.println("ServiceController:In Constructor");
	}

	@GetMapping("/services/{id}/{bookDate}")
	public ResponseEntity<?> getServiceproviderByServiceId(@PathVariable("id") int id,@PathVariable("bookDate") String bookDate) {
		System.out.println("--------------------------here in services get---------------------------");
		try {
			java.sql.Date bookingDate=java.sql.Date.valueOf(bookDate);
			List<ServiceProvider> serviceProvider=serviceServiceProvider.getAuthenticatedServiceProvidersByServiceId(id);
			List<Integer> spid=new ArrayList<>();
			System.out.println(serviceProvider);
			for(ServiceProvider sp:serviceProvider){
				
				Integer matchedId=serviceBook.getAllServiceProvidersIdNotBookedAtDate(sp.getServiceProviderId(),bookingDate);
				if(matchedId!=null)
				spid.add(matchedId);
			}
			System.out.println(" matched array "+spid);
			if(spid!=null){
			for(Integer b:spid)
			{
				int index=serviceProvider.indexOf(serviceServiceProvider.getServiceProviderById(b));
				System.out.println("index ----"+index);
				if(index!=-1)
				serviceProvider.remove(index);
			}}
			
			System.out.println(serviceProvider);
			return ResponseEntity.ok().body(serviceProvider);
		} catch (Exception e) {
			return ResponseEntity.ok().body(null);
		}
	}
	
	@GetMapping("/services")
	public ResponseEntity<?> getAllServices(){
		try{
			return ResponseEntity.ok().body(servicesService.getAllServices());
		}catch(Exception e){
			return null;
		}
	}
	
	

}
