package com.Cabbooking.CabBooking.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Cabbooking.CabBooking.Model.Booking;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{

	@Query("SELECT a FROM Booking a WHERE a.status=0")
	List<Booking> findBookingByStatus();

}