package com.app.homeservices.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	private Integer customerId;
	private String name,phoneNumber,email,password;
	
	//List of address from address table 
	private List<Address> addresses=new ArrayList<>();
	
	
	public Customer() {
		System.out.println("User:In Default constructor");
	}
	
	public Customer(String name, String phoneNumber, String email, String password) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		System.out.println("User:In parameterized constructor");
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id")
	public Integer getUserId() {
		return customerId;
	}
	public void setUserId(Integer customerId) {
		this.customerId = customerId;
	}
	
	@Column(length=25)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="phone_number",length=15)
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Column(unique=true,length=30)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(length=25)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@OneToMany(mappedBy="relatedCustomer",cascade=CascadeType.ALL)
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", password=" + password + "]";
	}

	
	public void addAddress(Address address)
	{
		addresses.add(address);
		address.setCustomer(this); 
	}
	public void removeAddress(Address address)
	{
		addresses.remove(address);
		address.setCustomer(null);
	}

}
