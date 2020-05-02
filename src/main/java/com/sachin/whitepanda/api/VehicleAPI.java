package com.sachin.whitepanda.api;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.whitepanda.dao.VehicleDAO;
import com.sachin.whitepanda.dao.VehicleRepository;
import com.sachin.whitepanda.entity.BookingEntity;
import com.sachin.whitepanda.entity.VehicleEntity;


@RestController
@RequestMapping("/book.service")
public class VehicleAPI {
	
	@Autowired
	private VehicleDAO vehicleDAO;
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@PostMapping("/bookCarNow")
	public String bookCar(@RequestBody BookingEntity booking) {
		if(vehicleRepository.existsById(booking.getVehicle().getVehicleNumber())) {
			vehicleDAO.save(booking);
			vehicleRepository.deleteById(booking.getVehicle().getVehicleNumber());
			return "Booking successfull";
		}
		else if(!(vehicleRepository.existsById(booking.getVehicle().getVehicleNumber()))) {
			List<BookingEntity> l = new ArrayList<BookingEntity>();
			List<Date> li = new ArrayList<Date>();
			l = vehicleDAO.findAll();
			Date d = new Date();
			for(BookingEntity b : l) {
				if(booking.getVehicle().getVehicleNumber().equals(b.getVehicle().getVehicleNumber())) {
					d = b.getReturnDate();
					li.add(d);
				}
			}
			if(!(li.isEmpty())) {
				if((booking.getIssueDate()).before(Collections.max(li))) {
					return "Car not available on given date";
				}
				else {
					vehicleDAO.save(booking);
					return "Booking Successfull";
				}
			}
			else if(li.isEmpty()) {
				return "No such car available in repository";
			}
		}
		return "XXXXX";
	}
	@PostMapping("/addNewCar")
	public String addNewCar(@RequestBody VehicleEntity car) {
		vehicleRepository.save(car);
		return "Car added successfully";
	}
	
	@PostMapping("/ReturnCar")
	public String addReturnedCar(@RequestBody BookingEntity booking) {
		if((vehicleDAO.findByNumber(booking.getVehicle().getVehicleNumber())).size() == 1) {
			vehicleRepository.save(booking.getVehicle());
		}
		vehicleDAO.deleteById(booking.getBookingId());
		return "Car returned successfully";
	}
	
	@GetMapping("/getCarByAvailabilityOnDate/{date}")
	public List<VehicleEntity> getCarByAvailabilityOnDate(@PathVariable String date) throws ParseException{
		Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(date); 
		List<VehicleEntity> l = vehicleRepository.findAll();
		List<BookingEntity> li = new ArrayList<BookingEntity>();
		li = vehicleDAO.findAll();
		for(BookingEntity b : li) {
			if(b.getReturnDate().before(date1)) {
				l.add(b.getVehicle());
			}		
		}
		return l;
	}
	
	@GetMapping("/getCarBySeatingCapacity/{seatingCapacity}")
	public List<VehicleEntity> getCarBySeatingCapacity(@PathVariable int seatingCapacity){
		return vehicleRepository.findBySeatingCapacity(seatingCapacity);
	}
	
	
	@GetMapping("/getBookingByCustomer/{name}")
	public BookingEntity getBookingByCustomer(@PathVariable String name){
		return vehicleDAO.findByName(name);
	}
	
	@GetMapping("/getBookingByVehicle/{vehicleNumber}")
	public List<BookingEntity> getBookingByVehicle(@PathVariable String vehicleNumber){
		return vehicleDAO.findByNumber(vehicleNumber);
	}
	
	@GetMapping("/getBookingById/{id}")
	public BookingEntity getBookingById(@PathVariable int id) {
		return vehicleDAO.findById(id);
	}

}
