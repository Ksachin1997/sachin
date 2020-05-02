package com.sachin.whitepanda.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vehicle")
public class VehicleEntity {
	
	@Id
	private String vehicleNumber;
	private int rentPerDay;
	private int seatingCapacity;
	private String model;
	
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public int getRentPerDay() {
		return rentPerDay;
	}
	public void setRentPerDay(int rentPerDay) {
		this.rentPerDay = rentPerDay;
	}
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	
	
}
