package com.Cabbooking.CabBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Cabbooking.CabBooking.Model.User;

@Repository
public interface DriverRepository extends JpaRepository<User, Long>{

    @Query("SELECT a FROM User a WHERE a.email=?1")
    User fetchUserByEmail(String email);


}
