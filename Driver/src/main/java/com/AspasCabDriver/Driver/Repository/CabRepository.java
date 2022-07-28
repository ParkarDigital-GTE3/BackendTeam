package com.AspasCabDriver.Driver.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AspasCabDriver.Driver.Model.CabDetails;


@Repository
public interface CabRepository extends JpaRepository<CabDetails, Long>{

    @Query("SELECT a FROM CabDetails a WHERE a.cabRegistrationNo=?1")
    CabDetails fetchCabByRegistrationNo(String cabRegistrationNo);

    
    @Query("SELECT a FROM CabDetails a WHERE a.cab_id.cab_id=?1")
	CabDetails fetchCabById(long cab_id);

    @Query("SELECT a FROM CabDetails a WHERE a.licenseNo=?1")
	CabDetails fetchCabByLicenseNo(String licenseNo);

    @Query("SELECT a FROM CabDetails a WHERE a.cabInsuranceNo=?1")
	CabDetails fetchCabByInsuranceNo(String cabInsuranceNo);

    
    @Query("SELECT a FROM CabDetails a WHERE a.driverEmail=?1")
	CabDetails fetchCabByEmail(String driverEmail);


}