package com.lmsportal.controller;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.lmsportal.config.Message;
import com.lmsportal.model.Profile;
import com.lmsportal.model.Register;
import com.lmsportal.repository.ProfileRepo;
import com.lmsportal.repository.RegisterRepo;
import com.lmsportal.service.ProfileService;

@Controller
//@RequestMapping("/user")
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private ProfileRepo profileRepo;	
	
	@Autowired
	private RegisterRepo registerRepo;
	
	@GetMapping("/index-profile")
	public String indexProfile(Profile profile,Model model,Principal principal)
	{
		String userName= principal.getName();
		Register register= this.registerRepo.getUserByUserName(userName);		
		List<Profile>profiles= this.profileRepo.findProfileByUser(register.getId());		
		model.addAttribute("profiles",profiles);	
		return "profile/index";
	}
	
	@GetMapping("/show-profile")
	public String createProfile(Profile profile,Model model,Principal principal)
	{		
		String userName= principal.getName();	     
	    Register register= this.registerRepo.getUserByUserName(userName);		
	    List<Profile>profiles= this.profileRepo.findProfileByUser(register.getId());		
	    model.addAttribute("profiles",profiles);	    
	    model.addAttribute("registers" ,registerRepo.findAll());	    
		model.addAttribute("profiles",new Profile());		
		return "profile/create";
	}	

	
	@PostMapping("/add-profile")
	public String addProfile(Register register, @ModelAttribute("profile") Profile profile,
			   Model model,
			  @RequestParam("fileImage") MultipartFile multipartFile) throws IOException
	{					   
		
		    if(multipartFile.isEmpty())
		    {
		    	System.out.println("File is Empty");
		    	profile.setImage("avatar7.png" );      	
		    			    	
		    }		    
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			profile.setImage(fileName);
			Profile savedPro = profileService.createProfile(profile);					
	        String uploadDir = "./user-photos/profile/" + savedPro.getId();        
	        Path uploadPath=Paths.get(uploadDir);	        
	        
	        if(!Files.exists(uploadPath))
	        {	        	
	        	Files.createDirectories(uploadPath);	        	
	        }
	        try(InputStream inputStream=multipartFile.getInputStream())
	        {
	            Path filePath=uploadPath.resolve(fileName);
	            System.out.println(filePath.toFile().getAbsolutePath());
	            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
	            			
			} 
	        catch (IOException e) 
	        {
	        	
	        	throw  new IOException("could not save upload file :"+fileName);
	        	
			}
	        profileRepo.save(profile);		
			return "redirect:/index-profile";			
    } 
	
	@GetMapping("/editProfile/{id}")
    public String editProfile(@PathVariable("id") int id, Model model) 
	{
		Profile profile = profileService.getProfile(id);		
      model.addAttribute("profile", profile);    
      return "profile/update";
    }
	
	@PostMapping("/updateProfile/{id}")
	public String updateProfile(@ModelAttribute("profile") Profile profile, Model model,
			  @RequestParam("fileImage") MultipartFile multipartFile ,HttpSession session) throws IOException
	{			
		
//		boolean f = profileService.createProfile((profile.getRegister()));
//				
//		if (f)
//		{						
//			session.setAttribute("message",new Message("Email Id alreday exists", "alert-danger"));
//			return "register";
//		}
//		else
//		{		
		    if(multipartFile.isEmpty())
		    {
		    	System.out.println("File is Empty");
		    	profile.setImage("avatar7.png" );		      
		    			    	
		    }
		   
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			profile.setImage(fileName);
			Profile savedPro = profileService.createProfile(profile);					 
	        String uploadDir = "./user-photos/profile/" + savedPro.getId();        
	        Path uploadPath=Paths.get(uploadDir);
	       
	        if(!Files.exists(uploadPath))
	        {
	        	
	        	Files.createDirectories(uploadPath);
	        	
	        }
	        try(InputStream inputStream=multipartFile.getInputStream())
	        {
	            Path filePath=uploadPath.resolve(fileName);
	            System.out.println(filePath.toFile().getAbsolutePath());
	            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
	            			
			} 
	        catch (IOException e) 
	        {
	        	
	        	throw  new IOException("could not save upload file :"+fileName);
	        	
			}
	        profileRepo.save(profile);
	        
//		}
			return "redirect:/index-profile";  			
    } 
	
	
	@GetMapping("/deleteProfile/{id}")
	public String  deleteProfile(@PathVariable("id") int id,Model model,HttpSession session)
	{			
		
	  Profile profile =this.profileRepo.findById(id).get();	  
	  System.out.println("Profile" +profile.getId());	  
	  profile.setRegister(null);
	  this.profileRepo.deleteAll();
	 	
	  
	  System.out.println("DELETED");
	  session.setAttribute("message", new Message("Register User Delete Successfully....","alert-success"));
	  return "redirect:/index-profile";
	}	
	
	
	
	
   //// showing particular profile detail	
	@GetMapping("/{id}/profile")
	public String showProfile( @PathVariable("id")int id,Model model,Principal principal)
	{
		System.out.println("id "+id);		
		Optional<Profile> profileOptional = this.profileService.singleProfile(id);				
		Profile profile= profileOptional.get();
		
		////
	    String userName	=principal.getName();
	    Register register=this.registerRepo.getUserByUserName(userName);
		
	    if(register.getId()==profile.getRegister().getId())
	    {
	    	model.addAttribute("pro",profile);
	    }
	    	
				
		return "profile/profile-detail";
	}
	
	
}










