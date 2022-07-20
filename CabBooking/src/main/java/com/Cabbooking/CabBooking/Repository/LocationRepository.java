package com.Cabbooking.CabBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Cabbooking.CabBooking.Model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

	@Query("SELECT totalDistance FROM Location a WHERE a.source=?1 AND a.destination=?2")
	long fetchTotalDistance(String source, String destination);

}
