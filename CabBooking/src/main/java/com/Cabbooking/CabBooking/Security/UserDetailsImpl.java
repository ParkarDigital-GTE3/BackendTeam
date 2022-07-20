package com.Cabbooking.CabBooking.Security;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Cabbooking.CabBooking.Model.CabDriver;
import com.Cabbooking.CabBooking.Model.Customer;

import com.fasterxml.jackson.annotation.JsonIgnore;





public class UserDetailsImpl implements UserDetails {
	
	static final Logger log = LoggerFactory.getLogger(UserDetailsImpl.class);
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String email;
	
	private String role;
	private String password;

	private Collection<? extends GrantedAuthority> authorities;
	 
	
	
 public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static Logger getLog() {
		return log;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

public UserDetailsImpl() {
		// TODO Auto-generated constructor stub
	}

public UserDetailsImpl(Long id, String email, String role, String password,
		Collection<? extends GrantedAuthority> authorities) {
	this.id = id;
	this.email = email;
	this.role = role;
	this.password = password;
	this.authorities = authorities;
}

public static UserDetailsImpl buildDriver(CabDriver driver){
    log.info("****Inside UserDetailsImpl buildDriver method***");

    List<GrantedAuthority> authorities=new LinkedList<>();
    log.info(" After authoritiesList-------");
    authorities.add(new SimpleGrantedAuthority("user_role"));
    log.info("Authorities-----"+authorities);
    log.info(" Before build of UserDetailsImpl");
    return new UserDetailsImpl(driver.getDriver_id(),driver.getRole(),driver.getEmail(),driver.getPassword(),authorities);
}

public static UserDetailsImpl build(Customer customer){
	        log.info("****Inside UserDetailsImpl build method***");

	        List<GrantedAuthority> authorities=new LinkedList<>();
	        log.info(" After authoritiesList-------");
	        authorities.add(new SimpleGrantedAuthority("user_role"));
	        log.info("Authorities-----"+authorities);
	        log.info(" Before build of UserDetailsImpl");
	        return new UserDetailsImpl(customer.getId(),customer.getRole(),customer.getEmail(),customer.getPassword(),authorities);
	    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	

	
}
	
	

    

