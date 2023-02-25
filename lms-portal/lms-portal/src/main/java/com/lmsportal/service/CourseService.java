package com.lmsportal.service;

import java.util.List;

import com.lmsportal.model.Course;


public interface CourseService {

    public Course createCourse(Course course);
	
	public List<Course> findAll();
	
	public Course getCourse(int id);
	
	
}
