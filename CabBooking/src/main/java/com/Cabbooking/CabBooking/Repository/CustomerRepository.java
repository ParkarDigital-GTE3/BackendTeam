package com.Cabbooking.CabBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Cabbooking.CabBooking.Model.Customer;
import com.Cabbooking.CabBooking.Model.User;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @Query("SELECT a FROM Customer a WHERE a.email=?1")
    Customer fetchCustomerByEmail(String email);



}
