package com.AspasCabCustomer.Customer.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AspasCabCustomer.Customer.Model.RatesAndTypes;



@Repository
public interface CarTypesAndRatesRepo extends JpaRepository<RatesAndTypes, Long> {

	@Query("SELECT ratekm FROM RatesAndTypes a WHERE a.type=?1 AND a.capacity=?2")
	long fetchRates(String cabType, String cabCapacity);

	@Query("SELECT DISTINCT(type) FROM RatesAndTypes")
	List<String> getCabTypes();

	@Query("SELECT capacity,ratekm FROM RatesAndTypes a WHERE a.type=?1")
	RatesAndTypes fetchRatesAndCapacity(String cabType);

	@Query("SELECT ratekm FROM RatesAndTypes a WHERE a.type=?1")
	Long getRateByCabType(String cabType);
	
	@Query("SELECT capacity FROM RatesAndTypes a WHERE a.type=?1")
	String getCapcityByCabType(String cabType);

	

}
