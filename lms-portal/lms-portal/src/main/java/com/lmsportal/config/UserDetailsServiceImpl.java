package com.lmsportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lmsportal.model.Register;
import com.lmsportal.repository.RegisterRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private RegisterRepo registerRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//fetching user from database
		Register register = registerRepo.getUserByUserName(username);		

		if (register != null)
		{						
			return new CustomUserDetails(register);
		}

		throw new UsernameNotFoundException("user not available");
	}  
}