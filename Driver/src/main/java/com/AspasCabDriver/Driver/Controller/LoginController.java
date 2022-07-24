package com.AspasCabDriver.Driver.Controller;

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

import com.AspasCabDriver.Driver.Model.CabDriver;
import com.AspasCabDriver.Driver.Repository.DriverRepository;
import com.AspasCabDriver.Driver.Response.CustomResponse;
import com.AspasCabDriver.Driver.Response.CustomResponseForLogin;
import com.AspasCabDriver.Driver.Security.JwtUtils;
import com.AspasCabDriver.Driver.Security.UserDetailsImpl;
import com.AspasCabDriver.Driver.Service.AuthService;



@RequestMapping("/login")
@RestController
@CrossOrigin
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	
	@Autowired
	DriverRepository driverRepository;

    @Autowired
    AuthService authService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;
	
	//Cab Driver Login
	@PostMapping("/driverLogin")
	public ResponseEntity<Object> driverLogin(@RequestBody CabDriver cabDriverDetails){
		
		CabDriver fetchDriver = authService.fetchDriverByEmail(cabDriverDetails.getEmail());
	        if(fetchDriver!=null)
	        {
	            if(encoder.matches(cabDriverDetails.getPassword(), fetchDriver.getPassword()))
	            {
	                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(cabDriverDetails.getEmail(),cabDriverDetails.getPassword()));
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	                String jwt = jwtUtils.generateJwtToken(authentication);

	                UserDetailsImpl details =(UserDetailsImpl)authentication.getPrincipal();
	                if(details != null)
	                {
	                    System.out.println(cabDriverDetails.getPassword());
	                    CustomResponseForLogin response = new CustomResponseForLogin(new Date(),"Login Successfully","200",jwt, fetchDriver.getEmail());
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
	

