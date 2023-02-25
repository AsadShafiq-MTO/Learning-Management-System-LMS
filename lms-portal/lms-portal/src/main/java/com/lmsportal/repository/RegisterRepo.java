package com.lmsportal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lmsportal.model.Register;

@Repository
public interface RegisterRepo extends JpaRepository<Register, Integer> {

	@Query("select r from Register r where r.email =:email")	  
	 public Register getUserByUserName(@Param("email")String email);
	 
	/// Pagination	
	@Query("from Register as r")
	 public Page<Register> findRegisterByUser(Pageable pageable);	
	
	 public boolean existsByEmail(String email);
		  
	 public Register findByEmail(String email);	 
	
     //Register findByUsername(String username);
}
