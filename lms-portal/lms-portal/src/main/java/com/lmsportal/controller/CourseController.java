package com.lmsportal.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import com.lmsportal.model.Course;
import com.lmsportal.repository.CourseRepo;
import com.lmsportal.service.CourseService;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseRepo courseRepo;
	
		
	@GetMapping("/courses")
	public String getAllCourse(Model model)
	{
		model.addAttribute("courses",courseService.findAll());
		return "course/index";
	}
	
	@GetMapping("/create-course")
	public String createCourse(Model model)
	{
		model.addAttribute("courses","Add-Course - Learning-Management-System");
		model.addAttribute("course", new Course());
		return "course/create";
	}
	
	
	@PostMapping("/add-course")
	public String createCourse(@ModelAttribute("course")Course course, Model model,
			 @RequestParam("fileImage") MultipartFile multipartFile) throws IOException
	{
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		course.setImage(fileName);
		Course savedEmp = courseService.createCourse(course);		 
        String uploadDir = "./user-photos/course/" + savedEmp.getId();        
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
		
        courseService.createCourse(course);
		return "redirect:/courses";
  }
	
   @GetMapping("/editCourse/{id}")
   public String editCourse(@PathVariable("id") int id, Model model) 
	{
		Course course = courseService.getCourse(id);		
        model.addAttribute("course", course);
        
      return "course/update";
   }
   
	@PostMapping("/updateCourse/{id}")	 
	public String updateCourse(@ModelAttribute("course")Course course, Model model,
			 @RequestParam("fileImage") MultipartFile multipartFile) throws IOException
	{
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		course.setImage(fileName);
		Course savedEmp = courseService.createCourse(course);		 
       String uploadDir = "./user-photos/course/" + savedEmp.getId();        
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
		
       courseService.createCourse(course);
		return "redirect:/courses";
    }	
	@GetMapping("/deleteCourse/{id}")
	public String  deleteCourse(@PathVariable("id") int id)
	{
		Course course=courseService.getCourse(id);	
		courseRepo.delete(course);		
			return "redirect:/courses";
	}		
	
}
