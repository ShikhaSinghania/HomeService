package com.app.homeservices.pojos;

public class CustomerAddress {
	
	private Customer customer;
	private Address address;
	public CustomerAddress(Customer customer, Address address){
		this.customer=customer;
		this.address=address;
	}
	public CustomerAddress(){}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "CustomerAddres [customer=" + customer + ", address=" + address + "]";
	}

}
