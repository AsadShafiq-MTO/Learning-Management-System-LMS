package com.lmsportal.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.lmsportal.model.Register;
import com.lmsportal.repository.RegisterRepo;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	  @Autowired 
	  private RegisterRepo  registerRepo;
	  
	  	  
	  @Autowired 
	  private BCryptPasswordEncoder passwordEncode;	 
     
	//Update User
    @Override
    public void save(Register register) 
    {
    	register.setPassword(passwordEncode.encode(register.getPassword()));
		registerRepo.save(register);
    }  
	  
	  
	@Override
	public Register createUser(Register register) throws Exception
	{
		  register.setPassword(passwordEncode.encode(register.getPassword()));	
		  
		  register.setAgreed(true);
		  
		  Register local=this.registerRepo.findByEmail(register.getEmail());
				 			 
			if(local!=null)
			{
				System.out.println("User is aleready there !!");
				throw new Exception("User already Present !!");			
			}
			else
			{				
				register.getRoles();           //.addAll(userRoles);				
				local= this.registerRepo.save(register);
			}
			return local;		
	}	
	
	
	 //Get All Users
	@Override
    public List<Register> findAll() {
        return registerRepo.findAll();
    }

    //Get User By Id
	@Override
    public Register findById(int id) {
        return registerRepo.findById(id).orElse(null);
    }

    //Delete User
	@Override
    public void delete(int id) {
        registerRepo.deleteById(id);
    }

    
	
	
    
    
    @Override
	public boolean checkEmail(String email) {
		
		  return registerRepo.existsByEmail(email); 
	}
	
	@Override
	public Optional<Register> getRegister(int id) {

		return registerRepo.findById(id);
	}

    
    @Override
	public Register findByEmails(String email) {


		return registerRepo.findByEmail(email);
	}
    

}

