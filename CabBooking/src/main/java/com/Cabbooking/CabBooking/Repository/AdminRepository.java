package com.Cabbooking.CabBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Cabbooking.CabBooking.Model.Admin;
import com.Cabbooking.CabBooking.Model.Customer;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT a FROM Admin a WHERE a.userName=?1")
	Admin getByUsername(String userName);
	

}
