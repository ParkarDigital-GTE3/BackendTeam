package com.Cabbooking.CabBooking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Cabbooking.CabBooking.Model.Booking;
import com.Cabbooking.CabBooking.Model.TripDetails;

@Repository
public interface TripRepository extends JpaRepository<TripDetails, Long> {

/*	@Query("INSERT INTO TripDetails(source,destination,driverEmail,CustomerEmail)"
			+ "VALUES(source,destination, driverEmail, CustomerEmail)")
	TripDetails saveAll(String source,String destination,String driverEmail,String CustomerEmail);

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
