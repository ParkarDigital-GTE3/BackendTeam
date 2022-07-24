package com.AspasCabDriver.Driver.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AspasCabDriver.Driver.Model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

	@Query("SELECT totalDistance FROM Location a WHERE a.source=?1 AND a.destination=?2")
	Long fetchTotalDistance(String source, String destination);

	@Query("SELECT DISTINCT(source) FROM Location")
	List<String> getSource();
	

	@Query("SELECT DISTINCT(destination) FROM Location")
	List<String> getDestination();

}