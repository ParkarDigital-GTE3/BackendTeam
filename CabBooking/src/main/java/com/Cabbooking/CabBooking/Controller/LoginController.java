package com.Cabbooking.CabBooking.Controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.Cabbooking.CabBooking.Model.User;
import com.Cabbooking.CabBooking.Repository.UserRepository;
import com.Cabbooking.CabBooking.Response.CustomResponseForLogin;
import com.Cabbooking.CabBooking.Response.CustomResponseForNoUser;
import com.Cabbooking.CabBooking.Security.AuthEntryPointJwt;
import com.Cabbooking.CabBooking.Security.JwtUtils;
import com.Cabbooking.CabBooking.Security.UserDetailsImpl;
import com.Cabbooking.CabBooking.Service.AuthService;



@RestController
@CrossOrigin
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
    UserRepository userRepository;

    @Autowired
    AuthService authService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;
	
	@PostMapping("/userLogin")
	public ResponseEntity<Object> userlogin(@RequestBody User userDetails){
		
			User fetchUser = authService.fetchUserByEmail(userDetails.getEmail());
	        if(fetchUser!=null)
	        {
	            if(encoder.matches(userDetails.getPassword(), fetchUser.getPassword()))
	            {
	                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails.getEmail(),userDetails.getPassword()));
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	                String jwt = jwtUtils.generateJwtToken(authentication);

	                UserDetailsImpl details =(UserDetailsImpl)authentication.getPrincipal();
	                if(details != null)
	                {
	                    System.out.println(userDetails.getPassword());
	                    CustomResponseForLogin response = new CustomResponseForLogin(new Date(),"Login Successfull","200",jwt, fetchUser.getEmail());
	                    return new ResponseEntity<Object>(response, HttpStatus.OK);
	                }
	                else {
	                    CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Error in authenticaion","401");
	                    return new ResponseEntity<Object>(response,HttpStatus.UNAUTHORIZED);

	                }

	            }else{
	                CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"Invalid Credentials","401");
	                return new ResponseEntity<Object>(response,HttpStatus.UNAUTHORIZED);
	            }
	        }
	        else
	        {
	            CustomResponseForNoUser response = new CustomResponseForNoUser(new Date(),"User Not Found","401");
	            return new ResponseEntity<Object>(response,HttpStatus.UNAUTHORIZED);
	        }
	    }
}
	

