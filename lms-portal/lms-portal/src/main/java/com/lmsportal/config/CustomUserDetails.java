package com.lmsportal.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.lmsportal.model.Register;
import com.lmsportal.model.Role;

@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails {

	private Register register;

	public CustomUserDetails(Register register)
	{
		super();
		this.register = register;
	}

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() 
//	{		
//		  SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(register.getName());				 
//		  return List.of(simpleGrantedAuthority);
//
//	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : register.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getDescription()));
        }
        return authorities;

        //return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

	@Override
	public String getPassword() 
	{
		return register.getPassword();
	}

	@Override
	public String getUsername() 
	{
		return register.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() 
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked() 
	{
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		
		return true;
	}

	@Override
	public boolean isEnabled() 
	{
		
		return true;
	}

	
}
