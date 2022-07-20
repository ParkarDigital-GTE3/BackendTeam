package com.Cabbooking.CabBooking.Service;

import java.util.List;

import com.Cabbooking.CabBooking.Model.Customer;

public interface CustomerService {

	List<Customer> fetchAllCustomers();

	Long countCustomer();

	Customer updateCustomer(Customer fetchCustomer);

	Customer getById(long customerId);

	Customer fetchCustomerByEmail(String customerEmail);

}
