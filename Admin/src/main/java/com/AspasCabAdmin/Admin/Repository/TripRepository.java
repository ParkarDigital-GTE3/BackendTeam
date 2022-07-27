package com.AspasCabAdmin.Admin.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AspasCabAdmin.Admin.Model.TripDetails;

@Repository
public interface TripRepository extends JpaRepository<TripDetails, Long> {
	
	@Query("SELECT a FROM TripDetails a WHERE a.driverEmail=?1")
	List<TripDetails> fetchTripByDriverEmail(String email);

	@Query("SELECT SUM(totalFare) FROM TripDetails a WHERE a.driverEmail=?1")
	long calculateTotalEarning(String email);
	
	@Query("SELECT a FROM TripDetails a WHERE a.customerEmail=?1")
	List<TripDetails> fetchTripByCustomerEmail(String email);

	@Query("SELECT SUM(totalFare) FROM TripDetails")
	Long fetchTotalEarning();

	@Query("SELECT SUM(totalFare) FROM TripDetails a WHERE a.tripDate=?1")
	Long fetchTodaysTotalEarning(String date);


	@Query("SELECT a FROM TripDetails a WHERE a.trip_id =?1")
	TripDetails getTripById(long trip_id);

	@Query("SELECT DISTINCT(tripDate),COUNT(*) FROM TripDetails a GROUP BY tripDate")
	List<String> countTripByDate();
	
	@Query("SELECT CONCAT(source,'-',destination),COUNT(*) FROM TripDetails GROUP BY source,destination")
	List<String> CountTripByLocation();

	@Query("SELECT a FROM TripDetails a WHERE booking_id=?1")
	TripDetails getTripByBookingId(long booking_id);

/*	
 * 
	@Query("SELECT SUM(totalfare) FROM TripDetails a")
	Long totalEarning();

	@Query("SELECT SUM(totalfare) FROM TripDetails a WHERE a.date=?1")
	Long todaysEarning(int date);

	@Query("SELECT a FROM TripDetails a WHERE a.driverEmail=?1")
	List<TripDetails> fetchTripByDriverEmail(String email);

	
	@Query("SELECT a FROM TripDetails a WHERE a.driverEmail=?1")
	TripDetails getTripByEmail(String email);

	@Query("SELECT SUM(totalFare) FROM TripDetails a WHERE a.driverEmail =?1 ")
	Long totalEarningByName(String email);


	@Query("SELECT a FROM TripDetails a WHERE a.CustomerEmail=?1")
	List<TripDetails> fetchTripByCustomerEmail(String email);

	@Query("SELECT a.trip_id FROM TripDetails a WHERE a.trip_id=:date")
	Long todaysEarning(int date);
*/


}
