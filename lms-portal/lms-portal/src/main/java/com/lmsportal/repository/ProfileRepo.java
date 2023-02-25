package com.lmsportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lmsportal.model.Profile;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Integer> {

	@Query("from Profile as pro where pro.register.id =:register_id")
	public List<Profile> findProfileByUser(@Param("register_id")int register_id);
	
}
