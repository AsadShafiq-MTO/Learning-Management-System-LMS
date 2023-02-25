package com.lmsportal.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "\"User\"")
public class Register {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;
		 
	  @NotBlank(message = "name can't be empty !!")	  
	  @Size(min = 3 ,max = 100,message ="name must be between 3 to 100 charactors")
	  private String name;
	  	
	  @NotBlank(message = "email can't be empty !!")	
	  @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message="use this format user@gmail.com") 
	  private String email;
	   
	  @NotBlank(message = "password can't be empty !!")	  
	  @Size(min = 8 ,max = 200,message="password must be between 8 to 200 charactors" ) 
	  private String password;
	  
	  //@AssertTrue(message="Must agree term and condition !!") 
	  private boolean agreed;
	  
	  	  	  
	  @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	    @JoinTable(
	            name = "user_role",
	            joinColumns = {@JoinColumn(name = "user_id")},
	            inverseJoinColumns = {@JoinColumn(name = "role_id")}
	    )
	    Set<Role> roles = new HashSet<>();
	  	  
	  
	  @OneToOne(mappedBy = "register" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	  private Profile profile;	   
	  
	    public Register() 
	    {
		
		}		

		public Register(int id, String name, String email, String password, 
				         boolean agreed,Set<Role> roles,Profile profile) 
		{
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.password = password;
			this.agreed = agreed;
			this.roles = roles;
			this.profile = profile;
			
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public boolean isAgreed() {
			return agreed;
		}

		public void setAgreed(boolean agreed) {
			this.agreed = agreed;
		}

		
		public Set<Role> getRoles() {
			return roles;
		}

		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}

		public Profile getProfile() {
			return profile;
		}

		public void setProfile(Profile profile) {
			this.profile = profile;
		}


		@Override
		public String toString() {
			return "Register [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", agreed="
					+ agreed + "]";
		}	
		
}
