package com.sachin.whitepanda.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "booking")
public class BookingEntity {
	
	@Id
	private int bookingId;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date issueDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date returnDate;
	private CustomerEntity customer;
	private VehicleEntity vehicle;
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public CustomerEntity getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
	public VehicleEntity getVehicle() {
		return vehicle;
	}
	public void setVehicle(VehicleEntity vehicle) {
		this.vehicle = vehicle;
	}


}
