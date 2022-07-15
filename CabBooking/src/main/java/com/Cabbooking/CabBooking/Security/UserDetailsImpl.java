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

import com.Cabbooking.CabBooking.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;





public class UserDetailsImpl implements UserDetails {
	
	static final Logger log = LoggerFactory.getLogger(UserDetailsImpl.class);
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String email;
	
	
	private String password;
	
	
	public UserDetailsImpl(String email, String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.email = email;
		this.password = password;
	}
	

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


	public static Logger getLog() {
		return log;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	 private Collection<? extends GrantedAuthority> authorities;
	 
	 public static UserDetailsImpl build(User user) {
	        log.info("****Inside UserDetailsImpl build method***");

	        List<GrantedAuthority> authorities=new LinkedList<>();
	        log.info(" After authoritiesList-------");
	        authorities.add(new SimpleGrantedAuthority("user_role"));
	        log.info("Authorities-----"+authorities);
	        log.info(" Before build of UserDetailsImpl");
	        return new UserDetailsImpl(user.getEmail(),user.getPassword(),authorities);
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
	
	

    
