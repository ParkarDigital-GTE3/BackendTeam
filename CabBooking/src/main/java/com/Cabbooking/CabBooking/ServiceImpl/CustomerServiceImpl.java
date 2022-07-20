package com.Cabbooking.CabBooking.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Cabbooking.CabBooking.Model.Customer;
import com.Cabbooking.CabBooking.Repository.CustomerRepository;
import com.Cabbooking.CabBooking.Service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public List<Customer> fetchAllCustomers() {
		
		return customerRepository.findAll();
	}

	@Override
	public Long countCustomer() {

		return customerRepository.count();
	}

	@Override
	public Customer updateCustomer(Customer fetchCustomer) {
		return customerRepository.save(fetchCustomer);
	}

	@Override
	public Customer getById(long customerId) {

		return customerRepository.getCustomerById(customerId);
	}


	@Override
	public Customer fetchCustomerByEmail(String customerEmail) {
		// TODO Auto-generated method stub
		return  customerRepository.fetchCustomerByEmail(customerEmail);
	}

}
