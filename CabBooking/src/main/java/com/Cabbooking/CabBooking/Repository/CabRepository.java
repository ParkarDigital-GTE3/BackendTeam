package com.Cabbooking.CabBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Cabbooking.CabBooking.Model.CabDetails;
import com.Cabbooking.CabBooking.Model.CabDriver;

@Repository
public interface CabRepository extends JpaRepository<CabDetails, Long>{

    @Query("SELECT a FROM CabDetails a WHERE a.cabRegistrationNo=?1")
    CabDetails fetchCabByRegistrationNo(String cabRegistrationNo);

    
    @Query("SELECT a FROM CabDetails a WHERE a.cab_id.cab_id=?1")
	CabDetails fetchCabById(long cab_id);


}