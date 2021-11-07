package com.app.homeservices.pojos;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({"services","serviceprovider"})
public class Location {
	
	private Integer Id;
	private String location;
	private Set<Services> services=new HashSet<>();
	private Set<ServiceProvider> serviceprovider=new HashSet<>(); 
	public Location() {
		System.out.println("Location:In locationPojo");
	}
	
	public Location(String location) {
		this.location = location;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	@Column(length=50)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="location_services")
	public Set<Services> getServices() {
		return services;
	}

	public void setServices(Set<Services> services) {
		this.services = services;
	}
	
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="location_serviceproviders")
	public Set<ServiceProvider> getServiceprovider() {
		return serviceprovider;
	}

	public void setServiceprovider(Set<ServiceProvider> serviceprovider) {
		this.serviceprovider = serviceprovider;
	}

	//Method for Mapping
	public void addLocationWithServices(Set<Services> setservices)
	{
		this.services=setservices;
		for(Services service:setservices)
		{
			service.getLocations().add(this);
		}
		
	}
	
	public void deleteLocationWithServices()
	{
		for(Services service:this.getServices())
		{
			service.getLocations().remove(this);
		}
		for(ServiceProvider serviceprovider:this.getServiceprovider())
		{
			serviceprovider.getLocations().remove(this);
		}
		this.serviceprovider=null;
		this.services=null;
		
		
	}
	
	
	@Override
	public String toString() {
		return "Location [Id=" + Id + ", location=" + location + "]";
	}
}
