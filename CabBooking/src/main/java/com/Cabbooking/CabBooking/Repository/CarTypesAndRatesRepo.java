package com.Cabbooking.CabBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Cabbooking.CabBooking.Model.RatesAndTypes;

@Repository
public interface CarTypesAndRatesRepo extends JpaRepository<RatesAndTypes, Long> {

	@Query("SELECT ratekm FROM RatesAndTypes a WHERE a.type=?1 AND a.capacity=?2")
	long fetchRates(String cabType, String cabCapacity);

}
