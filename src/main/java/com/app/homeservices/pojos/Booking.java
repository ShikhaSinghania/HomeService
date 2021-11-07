package com.app.homeservices.pojos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Booking {
	
	private Integer bookingId;
	private int customerId;
	private int serviceId;
	private int serviceProviderId;
	private Date currentDate;
	private Date bookingDate;
	private int workingHours;
	private int status;
	private int time;
	private Address relatedAddresse;
	private double finalPrice;
	

	public Booking(){
		System.out.println("Booking:In Default constructor");
	}

	
	


	public Booking(int customerId, int serviceId, int serviceProviderId, Date currentDate, Date bookingDate,
			int workingHours, int status, int time,  double finalPrice) {
		super();
		this.customerId = customerId;
		this.serviceId = serviceId;
		this.serviceProviderId = serviceProviderId;
		this.currentDate = currentDate;
		this.bookingDate = bookingDate;
		this.workingHours = workingHours;
		this.status = status;
		this.time = time;
		this.finalPrice = finalPrice;
	}





	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="booking_id")
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	@Column(name="customer_id")
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Column(name="service_id")
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	@Column(name="service_provider_id")
	public int getServiceProviderId() {
		return serviceProviderId;
	}
	public void setServiceProviderId(int serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="present_date")
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="booking_date")
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	@Column(name="working_hours")
	public int getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(int workingHours) {
		this.workingHours = workingHours;
	}
	
	@ManyToOne
	@JoinColumn(name="address_id")
	public Address getRelatedAddresse() {
		return relatedAddresse;
	}


	public void setRelatedAddresse(Address relatedAddresse) {
		this.relatedAddresse = relatedAddresse;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}


	
	public double getFinalPrice() {
		return finalPrice;
	}


	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}


	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", customerId=" + customerId + ", serviceId=" + serviceId
				+ ", serviceProviderId=" + serviceProviderId + ", currentDate=" + currentDate + ", bookingDate="
				+ bookingDate + ", workingHours=" + workingHours + ", status=" + status + ", time=" + time
				+ ", relatedAddresse=" + relatedAddresse + ", finalPrice=" + finalPrice + "]";
	}

}
