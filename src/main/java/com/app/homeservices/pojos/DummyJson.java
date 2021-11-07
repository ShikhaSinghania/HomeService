package com.app.homeservices.pojos;

import java.util.HashSet;
import java.util.Set;

public class DummyJson {
	private String name,username,password;
	private Address address;
	private String phoneno;
	public String servicename;
	public Set<String> locations=new HashSet<>();
	public float ratePerHour,visitCharge;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "DummyJson [name=" + name + ", username=" + username + ", password=" + password + ", address=" + address
				+ ", phoneno=" + phoneno + ", servicename=" + servicename + ", locations=" + locations
				+ ", ratePerHour=" + ratePerHour + ", visitCharge=" + visitCharge + "]";
	}


}
