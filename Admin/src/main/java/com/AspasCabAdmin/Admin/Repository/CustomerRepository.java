package com.AspasCabAdmin.Admin.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.AspasCabAdmin.Admin.Model.Customer;




@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @Query("SELECT a FROM Customer a WHERE a.email=?1")
    Customer fetchCustomerByEmail(String email);


	@Query(value="SELECT COUNT(*) FROM Customer")
	Long countCustomer();


	@Query("SELECT a FROM Customer a WHERE a.id=?1")
	Customer getCustomerById(long customerId);

}
