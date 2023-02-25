package com.lmsportal.service;

import java.util.List;
import java.util.Optional;
import com.lmsportal.model.Profile;
public interface ProfileService {

	
	  
  public Profile createProfile(Profile profile); 
  
  public List<Profile> findAll();  
 	
  public Profile getProfile(int id);   
  

  public Optional<Profile> singleProfile(int id);  
  
}
