package com.app.homeservices.pojos;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"locations","serviceProvider"})
public class Services {
	
	private Integer serviceId;
	private String serviceName;
	private List<ServiceProvider> serviceProvider=new ArrayList<>();
	private Set<Location> locations=new HashSet<>();
	
	public Services() {
		System.out.println("Service:In Default constructor");
	}

	public Services(String serviceName) {
		super();
		this.serviceName = serviceName;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="service_id")
	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	@Column(name="service_name",length=25)
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@OneToMany(mappedBy="service",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	public List<ServiceProvider> getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(List<ServiceProvider> serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	@ManyToMany(mappedBy="services",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	public Set<Location> getLocations() {
		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}
	
	
	//mapping method
	public void addServiceWithLocations(Set<Location> locs)
	{
		this.setLocations(locs);
		for(Location l:locs)
		{
			l.getServices().add(this);
		}
	}
	
	public void deleteService()
	{
		
		for(Location locs:locations)
		{
			locs.getServiceprovider().remove(this);
		}
		this.locations=null;
		
	}
	
	
	
	
	
	
}
