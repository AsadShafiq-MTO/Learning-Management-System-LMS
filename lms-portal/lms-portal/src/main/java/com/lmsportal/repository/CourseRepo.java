package com.lmsportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmsportal.model.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {

}
