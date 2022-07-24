package com.AspasCabAdmin.Admin.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.AspasCabAdmin.Admin.Model.CabDriver;


@Repository
public interface DriverRepository extends JpaRepository<CabDriver, Long>{


    @Query("SELECT a FROM CabDriver a WHERE a.email=?1")
    CabDriver fetchDriverByEmail(String email);

//    
//    @Query("SELECT a FROM CabDriver a WHERE a.driver_id.driver_id=?1")
//	CabDriver fetchDriverById(long driver_id);

    @Query("SELECT a FROM CabDriver a WHERE a.activationStatus =0")
	List<CabDriver> findAllByActivationStatus();


	@Query("SELECT COUNT(*) FROM CabDriver a")
	Long countDriver();
	
	@Query("SELECT a FROM CabDriver a WHERE a.driver_id=?1")
	CabDriver getById(long driver_id);



	


}
