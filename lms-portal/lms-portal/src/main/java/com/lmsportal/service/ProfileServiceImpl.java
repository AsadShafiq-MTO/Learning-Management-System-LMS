package com.lmsportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lmsportal.model.Profile;
import com.lmsportal.repository.ProfileRepo;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepo profileRepo;
	
	@Override
	public List<Profile> findAll() {
		
		return profileRepo.findAll();
	}
	
	@Override
	public Profile createProfile(Profile profile) {
		
		return profileRepo.save(profile);
	}	

	
	@Override
	public Profile getProfile(int id) {
		
		return profileRepo.findById(id).orElse(null);
	}		

	@Override
	public Optional<Profile> singleProfile(int id) {
		
		return profileRepo.findById(id);
	}
	
	
	
	
	
	

	

	
	
	

	

	

}
