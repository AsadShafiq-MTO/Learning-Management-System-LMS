package com.lmsportal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;


@Entity
public class Profile {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	private String aboutMe;
	private String nikeName;
	private String image;	
	private int age;
	private String gender;
	private String phoneNumber;
	private String address;
	private String birthDate;
	private String startDate;
	private String work;
		
	@OneToOne
	@JoinColumn(name = "register_id")
	private Register register;
	
	public Profile() {
		
	}	

	public Profile(Integer id,String aboutMe, String nikeName, String image, int age, String gender,String phoneNumber, 
			        String address,String birthDate, String startDate, String work) {
		super();
		this.id = id;
		this.aboutMe = aboutMe;
		this.nikeName = nikeName;
		this.image = image;
		this.age = age;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.birthDate = birthDate;
		this.startDate = startDate;
		this.work = work;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getNikeName() {
		return nikeName;
	}

	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
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
         
        return "/user-photos/profile/" + id + "/" + image;
    }

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}	

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}	

	public Register getRegister() {
		return register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id +", aboutMe=" + aboutMe + ", nikeName=" + nikeName + ", image=" + image + ", age=" + age + ", gender="
				+ gender + ", phoneNumber=" + phoneNumber + ", address=" + address + ", startDate=" + startDate
				+ ", birthDate=" + birthDate+ ", work=" + work + "]";
	}	
	
}
