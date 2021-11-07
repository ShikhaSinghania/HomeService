package com.app.homeservices.pojos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="service_provider")
@JsonIgnoreProperties({"services","addresse","username","password","authenticStatus","workingStatus"})
public class ServiceProvider {
	private Integer serviceProviderId;
	private String name,username,password,phoneno;
	private float ratePerHour,visitCharge;
	private int authenticStatus,workingStatus;
	
	
	//List of address from address table 
	private Address addresse;
	private Services service;
	private Set<Location> locations=new HashSet<>();

	
	public ServiceProvider() {
		System.out.println("ServiceProvider:In Default constructor");
	}

	public ServiceProvider(String name, String username, String password, float ratePerHour, float visitCharge,
			int authenticStatus,int workingStatus) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.ratePerHour = ratePerHour;
		this.visitCharge = visitCharge;
		this.authenticStatus = authenticStatus;
		this.workingStatus = workingStatus;
	}

	public ServiceProvider(int id,String name,Set<Location> locations)
	{
		this.serviceProviderId=id;
		this.name=name;
		this.locations=locations;
	}
	
	public ServiceProvider(String name, String username, String password,String phoneno) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.phoneno=phoneno;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="service_provider_id")
	public Integer getServiceProviderId() {
		return serviceProviderId;
	}
	public void setServiceProviderId(Integer serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	@Column(length=25)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(length=25)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Column(length=25)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public float getRatePerHour() {
		return ratePerHour;
	}
	public void setRatePerHour(float ratePerHour) {
		this.ratePerHour = ratePerHour;
	}

	public float getVisitCharge() {
		return visitCharge;
	}
	public void setVisitCharge(float visitCharge) {
		this.visitCharge = visitCharge;
	}

	
	@OneToOne(cascade=CascadeType.ALL,mappedBy="relatedServicesProvider")
	public Address getAddresse() {
		return addresse;
	}

	public void setAddresse(Address addresse) {
		this.addresse = addresse;
	}

	@ManyToOne
	@JoinColumn(name="service_id")
	public Services getService() {
		return service;
	}
	public void setService(Services service) {
		this.service = service;
	}
	
	@ManyToMany(mappedBy="serviceprovider",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	public Set<Location> getLocations() {
		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}
		
	
	@Column(name="phone_no",unique=true,nullable=false)
	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}


	@Column
	public int getAuthenticStatus() {
		return authenticStatus;
	}

	public void setAuthenticStatus(int authenticStatus) {
		this.authenticStatus = authenticStatus;
	}

	@Column
	public int getWorkingStatus() {
		return workingStatus;
	}

	public void setWorkingStatus(int workingStatus) {
		this.workingStatus = workingStatus;
	}

		//Methods for mapping
		public void addAddress(Address adr)
		{
			addresse=adr;
			addresse.setRelatedServicesProvider(this);
		}
		public void addService(Services service)
		{
			this.service=service;
			service.getServiceProvider().add(this);
		}
		public void removeService(Services service)
		{
			service.getServiceProvider().remove(this);
			this.service=null;
		}
		public void addOneLocation(Location loc)
		{
			this.locations.add(loc);
			loc.getServiceprovider().add(this);
			
		}
		public void removeOneLocation(Location loc)
		{
			this.locations.remove(loc);
			loc.getServiceprovider().remove(this);
		}
	  public void addLocations(Set<Location> loc)
		{
			this.locations=loc;
			for(Location l:loc)
			{
				l.getServiceprovider().add(this);
			}
		} 
	  public void removeLocations(Set<Location> locs)
		{
		
			this.locations=null;
			
			for(Location l:locs)
			{
				l.getServiceprovider().remove(this);
			}
		}
	  
	  
	  public void deleteServiceprovider()
	  {
		 addresse.setRelatedServicesProvider(null);
		 service.getServiceProvider().remove(this);
		 for(Location l:locations)
		 {
			 l.getServiceprovider().remove(this);
		 }
	  }

}
