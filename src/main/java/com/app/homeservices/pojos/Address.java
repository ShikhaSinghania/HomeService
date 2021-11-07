package com.app.homeservices.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"relatedCustomer","relatedServicesProvider","relatedBooking"})
public class Address {
	private Integer addressId;
	private String flatNumber,colony,street,landmark,zipcode,city,state;
	private Customer relatedCustomer;
	private ServiceProvider relatedServicesProvider;
	private List<Booking> relatedBooking=new ArrayList<>();
	
	public Address() {
		System.out.println("Address:In Default constructor");
	}
	
	public Address(String flatNumber, String colony, String street, String landmark, String zipcode, String city,
			String state) {
		super();
		this.flatNumber = flatNumber;
		this.colony = colony;
		this.street = street;
		this.landmark = landmark;
		this.zipcode = zipcode;
		this.city = city;
		this.state = state;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	
	@Column(name="flat_number",length=10)
	public String getFlatNumber() {
		return flatNumber;
	}
	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}
	
	@Column(length=50)
	public String getColony() {
		return colony;
	}
	public void setColony(String area) {
		this.colony = area;
	}
	
	@Column(length=50)
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	@Column(length=50)
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	
	@Column(length=10)
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	@Column(length=20)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(length=30)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	public Customer getRelatedCustomer() {
		return relatedCustomer;
	}

	public void setRelatedCustomer(Customer relatedCustomer) {
		this.relatedCustomer = relatedCustomer;
	}
	
	
	@OneToOne
	@JoinColumn(name="service_provider_id")
	public ServiceProvider getRelatedServicesProvider() {
		return relatedServicesProvider;
	}
	
	@OneToMany(mappedBy="relatedAddresse",cascade=CascadeType.ALL)
	public List<Booking> getRelatedBooking() {
		return relatedBooking;
	}

	public void setRelatedBooking(List<Booking> relatedBooking) {
		this.relatedBooking = relatedBooking;
	}

	public void setRelatedServicesProvider(ServiceProvider relatedServicesProvider) {
		this.relatedServicesProvider = relatedServicesProvider;
	}

	public void setCustomer(Customer customer) {
		this.relatedCustomer = customer;
	}
	
	public void addBooking(Booking booking)
	{
		relatedBooking.add(booking);
		booking.setRelatedAddresse(this);
	}
	public void removeBooking(Booking booking)
	{
		relatedBooking.remove(booking);
		booking.setRelatedAddresse(null);
	}
	
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", flatNumber=" + flatNumber + ", colony=" + colony + ", street="
				+ street + ", landmark=" + landmark + ", zipcode=" + zipcode + ", city=" + city + ", state=" + state
				+ "]";
	}
}
