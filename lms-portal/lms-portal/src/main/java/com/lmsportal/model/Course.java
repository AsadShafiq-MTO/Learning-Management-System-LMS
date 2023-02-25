package com.lmsportal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;	
	private String name;
	private String image;
	private  String startDate;
	private  String endDate;
	private String description;
	
	public Course() {
		
	}

	public Course(Integer id, String name, String image, String startDate, String endDate, String description) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Transient
    public String getImagePath() 
	{
        
		if (image == null || id == null ) return null;     //// 
         
        return "/user-photos/course/" + id + "/" + image;
    }

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", image=" + image + ", startDate=" + startDate + ", endDate="
				+ endDate + ", description=" + description + "]";
	}
	
}
