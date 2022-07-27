package com.AspasCabAdmin.Admin.Controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AspasCabAdmin.Admin.Model.Admin;
import com.AspasCabAdmin.Admin.Response.AdminDetailsResponse;
import com.AspasCabAdmin.Admin.Response.CustomResponse;
import com.AspasCabAdmin.Admin.Response.LoginResponse;
import com.AspasCabAdmin.Admin.Security.JwtUtils;
import com.AspasCabAdmin.Admin.Security.UserDetailsImpl;
import com.AspasCabAdmin.Admin.Service.AdminAuthService;


@RequestMapping("/login")
@RestController
@CrossOrigin
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    AdminAuthService authService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;
	//Create Customer
    @PostMapping("/registerAdmin")
	public ResponseEntity<Object> createUser(@RequestBody Admin adminDetails){
    	Admin fetchCustomer = authService.fetchAdminByEmail(adminDetails.getEmail());

		if(fetchCustomer == null) {
			if (authService.emailValidation(adminDetails.getEmail())) {
				Admin admin= authService.createAdmin(adminDetails);

				AdminDetailsResponse response = new AdminDetailsResponse(new Date(), "Customer Created Succesfully", "200", admin);

				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
			else
			{
				CustomResponse response = new CustomResponse(new Date(),"Invalid Email id","409");
				return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
			}

		}else{

			CustomResponse response = new CustomResponse(new Date(),"Email id already exists","409");
			return new ResponseEntity<Object>(response,HttpStatus.CONFLICT);
		}
    }

    
	
    //Admin Login
	@PostMapping("/adminLogin")
	public ResponseEntity<Object> customerLogin(@RequestBody Admin adminDetails){
		
		Admin fetchAdmin = authService.fetchAdminByEmail(adminDetails.getEmail());
	        if(fetchAdmin!=null)//admin found
	        {
	            if(encoder.matches(adminDetails.getPassword(), fetchAdmin.getPassword()))//checking password
	            {
	                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDetails.getEmail(),adminDetails.getPassword()));
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	                String jwt = jwtUtils.generateJwtToken(authentication);

	                UserDetailsImpl details =(UserDetailsImpl)authentication.getPrincipal();
	                if(details != null)//generating token
	                {
	                    System.out.println(adminDetails.getPassword());
	                    LoginResponse response = new LoginResponse(new Date(),"Login Successfully","200",jwt, fetchAdmin.getEmail());
	                    return new ResponseEntity<Object>(response, HttpStatus.OK);
	                }
	                else {
	                    CustomResponse response = new CustomResponse(new Date(),"Error in authenticaion","401");
	                    return new ResponseEntity<Object>(response,HttpStatus.UNAUTHORIZED);

	                }

	            }else{
	            	CustomResponse response = new CustomResponse(new Date(),"Invalid Credentials","401");
	                return new ResponseEntity<Object>(response,HttpStatus.UNAUTHORIZED);
	            }
	        }
	        else
	        {
	        	CustomResponse response = new CustomResponse(new Date(),"User Not Found","401");
	            return new ResponseEntity<Object>(response,HttpStatus.UNAUTHORIZED);
	        }
	    }
}