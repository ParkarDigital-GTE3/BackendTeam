package com.Cabbooking.CabBooking.ServiceImpl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Cabbooking.CabBooking.Model.CabDetails;
import com.Cabbooking.CabBooking.Model.CabDriver;
import com.Cabbooking.CabBooking.Model.Customer;

import com.Cabbooking.CabBooking.Repository.CabRepository;
import com.Cabbooking.CabBooking.Repository.CustomerRepository;
import com.Cabbooking.CabBooking.Repository.DriverRepository;

import com.Cabbooking.CabBooking.Service.AuthService;
import com.mysql.cj.jdbc.Driver;

@Service
public class AuthServiceImpl implements AuthService {

    
    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired
    DriverRepository driverRepository;

    @Autowired
    CabRepository cabRepository;

    @Autowired
    PasswordEncoder encoder;
    
    // Send Email OTP
	public boolean sendEmail(String subject,String message,String to) {
		boolean f = false;
		String from = "Pranavbharambe7@gmail.com";
		
		//Variable for gmail
		String host = "smtp.gmail.com";
		
		//get system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES"+properties);
		
		//setting important information to properties object
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", from);
		properties.put("mail.smtp.password", "rjuqotdpvnhtayzr");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//get Session object
		Session session = Session.getInstance(properties, new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				System.out.println("in password authen");
				return new PasswordAuthentication("Pranavbharambe7@gmail.com","rjuqotdpvnhtayzr");
				
			}
		});
		System.out.println("out password auth");
		session.setDebug(true);
		
		//Step 2 : compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);
		
		try {
		
		//from email
		m.setFrom(from);
		
		//adding recipient to message
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		//adding subject to message
		m.setSubject(subject);
	
		
		//adding Text to message.
		m.setText(message);
		
		
		//Step 3 : send the message using Transport class
		Transport.send(m);
		f = true;
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return f;
		
	}

	
	// Email validation
	@Override
	public boolean emailValidation(String email)
	    {
	        
	        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
	                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	        if (email!=null)
	        {
	            if(email.matches(regexPattern))
	            {
	                return true;
	            }
	        }
	        return false;
	    }
    
	
	//Creating  entities
    
    //creating customer
    @Override
    public Customer createCustomer(Customer customer) {
    	customer.setPassword(encoder.encode(customer.getPassword()));
        return  customerRepository.save(customer);
    }

    //creating driver
    @Override
    public CabDriver createDriver(CabDriver driver) {

    	driver.setPassword(encoder.encode(driver.getPassword()));
        return  driverRepository.save(driver);
    }

    
    
	//Fetching the details
	
	//fetching customer
    @Override
    public Customer fetchCustomerByEmail(String emailId) {
        // TODO Auto-generated method stub
        return customerRepository.fetchCustomerByEmail(emailId);
    }

    //fetching driver
    @Override
    public CabDriver fetchDriverByEmail(String emailId) {
        // TODO Auto-generated method stub
        return driverRepository.fetchDriverByEmail(emailId);
    }


	//Updating Password
	//updating customer password
	@Override
	public void updateCustomerPassword(String emailId, String newPassword) {
		Customer cust = customerRepository.fetchCustomerByEmail(emailId);
		cust.setPassword(encoder.encode(newPassword));
		customerRepository.save(cust);		
	}

	//updating driver password
	@Override
	public void updateDriverPassword(String emailId, String newPassword) {
		CabDriver driver  = driverRepository.fetchDriverByEmail(emailId);
		driver.setPassword(encoder.encode(newPassword));
		driverRepository.save(driver);
	}

	
	
	@Override
	public CabDriver fetchDriverById(long driver_id) {
		return driverRepository.getById(driver_id);
	}





}
