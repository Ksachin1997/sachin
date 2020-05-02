package com.sachin.whitepanda.dao;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sachin.whitepanda.entity.BookingEntity;


@Repository
public interface VehicleDAO extends MongoRepository<BookingEntity, Integer>{
	
	BookingEntity findById(int id);
	
	@Query("{'customer.name':?0}")
	BookingEntity findByName(String name);
	
	@Query("{'vehicle.vehicleNumber':?0}")
	List<BookingEntity> findByNumber(String vehicleNumber);
	
	
}
