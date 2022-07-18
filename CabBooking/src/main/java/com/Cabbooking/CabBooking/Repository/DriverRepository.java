package com.Cabbooking.CabBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Cabbooking.CabBooking.Model.CabDetails;
import com.Cabbooking.CabBooking.Model.CabDriver;


@Repository
public interface DriverRepository extends JpaRepository<CabDriver, Long>{


    @Query("SELECT a FROM CabDriver a WHERE a.email=?1")
    CabDriver fetchDriverByEmail(String email);

    
    @Query("SELECT a FROM CabDriver a WHERE a.driver_id.driver_id=?1")
	CabDriver fetchDriverById(long driver_id);


}
