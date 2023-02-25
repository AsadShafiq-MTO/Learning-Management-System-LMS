package com.lmsportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmsportal.model.Course;
import com.lmsportal.repository.CourseRepo;

@Service
public class CourseServiceImp implements CourseService {

	
	@Autowired
	private CourseRepo courseRepo;
	
	@Override
	public Course createCourse(Course course) {
		
		return courseRepo.save(course);
	}

	@Override
	public List<Course> findAll() {
		
		return courseRepo.findAll();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Course getCourse(int id) {
		
		return courseRepo.getById(id);
	}

}
