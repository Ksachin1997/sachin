package com.sachin.whitepanda.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.sachin.whitepanda.entity.VehicleEntity;

public interface VehicleRepository extends MongoRepository<VehicleEntity, String> {
	
	
	List<VehicleEntity> findBySeatingCapacity(int seatingCapacity);
	

}
