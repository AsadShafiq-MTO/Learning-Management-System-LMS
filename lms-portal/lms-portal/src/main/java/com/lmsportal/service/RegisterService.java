package com.lmsportal.service;

import java.util.List;
import java.util.Optional;
import com.lmsportal.model.Register;

public interface RegisterService {

	//create user		
	public Register createUser(Register register) throws Exception; 	

	public boolean checkEmail(String email);	
	
	public Optional<Register> getRegister(int id); 
	
	public Register findByEmails(String email);	
	
	
	//Get All Users
    public List<Register> findAll();
    
    //Get User By Id
    public Register findById(int id); 
    
    //Delete User
    public void delete(int id);
    
    //Update User
    public void save(Register user);

    
    
	
	
	
	

		
}
