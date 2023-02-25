package com.lmsportal.controller;

import java.security.Principal;
import java.util.Random;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import com.lmsportal.config.Message;
import com.lmsportal.model.Register;
import com.lmsportal.repository.RegisterRepo;
import com.lmsportal.service.EmailService;
import com.lmsportal.service.RegisterService;
import com.lmsportal.service.RoleService;

@Controller
//@RequestMapping("/user")
public class RegisterController {
	
	Random random= new Random(1000);
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired 
	private RegisterService registerService;
	
	@Autowired 
	private RoleService roleService;
	
	@Autowired
	private RegisterRepo registerRepo;	
	
	@RequestMapping("/home")
	public String dashboard(Model model,Principal principal)
	{
		String userName=principal.getName();
		System.out.println("USERNAME "+userName);
		
		//// get the user using username(Email)
		
		Register register =registerRepo.getUserByUserName(userName);
		System.out.println("USER"+ register);		
		model.addAttribute("register",register);
		return "index";
	}
			
	  
	@GetMapping("/listUser/{page}")
	public String listUser(Register register, @PathVariable("page") Integer page, Model model,Principal principal)
	{
		model.addAttribute("title","ListUser - Learning-Management-System");	    
		///// Pagination 
		
	    Pageable pageable =PageRequest.of(page, 5);	    
	    Page<Register>registers= this.registerRepo.findRegisterByUser(pageable);	    			
	    model.addAttribute("registers",registers);	    
		model.addAttribute("currentPage", page);		
		model.addAttribute("totalPages", registers.getTotalPages());  				
		return  "admin/user-list";    
	}	
	
	
	@GetMapping("/security/users")
    public String getAll(Model model) {
        model.addAttribute("registers", registerService.findAll());
        return "/security/users";
    }
	
	// Manage Role userEdit or userDetails 
	@GetMapping("/security/user/{op}/{id}")
	public String editUser( Model model,@PathVariable Integer id, @PathVariable String op) {
	      Register register = registerService.findById(id);
	      model.addAttribute("register", register);
	      model.addAttribute("userRoles", roleService.getUserRoles(register));
	      model.addAttribute("userNotRoles", roleService.getUserNotRoles(register));
	      return "/security/user" + op; //returns userEdit or userDetails
	} 	
	
    @PostMapping("/users/addNew")
    public RedirectView addNew(Register register, RedirectAttributes redir)
    {
      registerService.save(register);
      RedirectView redirectView = new RedirectView("/login", true);
      redir.addFlashAttribute("message", "You have successfully registered a new user!");
      return redirectView;
    }
	
	
	@SuppressWarnings("unused")
	@PostMapping("/addRegister")
	public String addRegister(@Valid @ModelAttribute("register") Register register,BindingResult result,
	                          @RequestParam(value = "agreement",defaultValue = "false")boolean agreement,
	                          Model model, HttpSession session)  
	{
		try 
		{
			boolean f = registerService.checkEmail(register.getEmail());
			if (f)
			{						
				session.setAttribute("message",new Message("Email Id alreday exists", "alert-danger"));
				return "register";
			}	
			
			if(!agreement)
			{
				System.out.println(" You have not agreed the term and condition");
				throw new Exception(" You have not agreed the term and condition");
			}	
			
			if (result.hasErrors()) 
			{
				System.out.println("ERROR"+result.toString());
				model.addAttribute("register", register);
				return "register";
		    }
			
			System.out.println("Agreement " +agreement);
			System.out.println("USER"+register);		
			Register resDtls=registerService.createUser(register);	 ////		
			model.addAttribute("register", new Register());
			
			
			session.setAttribute("message",new Message("Successfully Registered !!", "alert-success"));			
			return "login";
			 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			model.addAttribute("register",register);
			session.setAttribute("message", new Message("Something went wrong !!"+e.getMessage(),"alert-danger"));
			return "register";
		}	
	}
	
     //// Delete Registration
	@GetMapping("/deleteRegisterUser/{id}")
	public String deleteRegisterUser(@PathVariable("id") int id,Model model,HttpSession session)
	{			
		
	  Register register =this.registerRepo.findById(id).get();	  
	  System.out.println("Register" +register.getId());	  
	  this.registerRepo.delete(register);	  
	  System.out.println("DELETED");
	  session.setAttribute("message", new Message("Register User Delete Successfully....","alert-success"));
	  return "redirect:/listUser/0";
	}	

	//// Forget password
	@GetMapping("/forgetPassword")
	public String logout(Model model)
	{
		model.addAttribute("title","ForgetPassword - Learning-Management-System");
		return "forget-password";
	}
		
	@PostMapping("/sendOtp")
	public String sendOTP(@RequestParam("email")String email, Model model,HttpSession session)
	{
		model.addAttribute("title","ForgetPassword - Learning-Management-System");
		System.out.println("Email"+email);
		
		
		// write code for send OTP to E-mail..
		
		int otp= random.nextInt(999999);
		System.out.println("OTP"+otp);
		
		String subject="OTP From AIE";
		
		//String message="This is My LMS Portal OTP="+otp+ " Hello !!";   // OR
		
		String message=""
				     +"<div style='border:1px solid #e2e2e2;padding:20px'>"
				     +"<h1>"
				     +"OTP is "
				     +"<b>"+ otp
				     +"</n>"
				     +"</h1>"
				     +"</div>";
		String to=email;
		
		
		boolean flag= this.emailService.sendEmail(subject, message, to);
		
		if(flag)
		{
			session.setAttribute("myotp", otp);
			session.setAttribute("email", email);
			return "verify-otp";
		}
		else 
		{
			session.setAttribute("message", "Check your E-mail id !!");
			
			return "forget-password";
		}		
	}
	
	//// verify otp
	
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam("otp")int otp,HttpSession session)
	{
		int myOtp=(int) session.getAttribute("myotp");
		String email=(String) session.getAttribute("email");
		if(myOtp==otp)
		{
			//password change form
			Register register= this.registerRepo.getUserByUserName(email);
			if(register==null)
			{
				//send error message
				session.setAttribute("message", "User does not exists with this E-mail !!");				
				return "forget-password";
			}
			else
			{
				//send change password form
			}
			return "change-password";
		}
		else 
		{
			session.setAttribute("message", "You have enter worng otp !!");
			return "verify-otp";
		}		
	}
	
	//// Change Password	
	@PostMapping("/change-password")
	public String changePassword(@RequestParam("newpassword")String newpassword,HttpSession session)
	{
		String email=(String)session.getAttribute("email");
		Register register =this.registerRepo.getUserByUserName(email);
		register.setPassword(this.bCryptPasswordEncoder.encode(newpassword));
        this.registerRepo.save(register);
        return "redirect:/login?change=password change successfully...";
             
	}
	
	
//	@PostMapping("/update/{id}")
//	public String updateRegister( @PathVariable("id")Integer id,Model model)
//	{
//		model.addAttribute("register","Update Register");
//		
//		Register register= this.registerService.getRegister(id).get();
//		model.addAttribute("register",register);
//		return "student/dashboard";
//		
//	}
	
	
//	@GetMapping("/{id}/profile")
//	public String showProfile( @PathVariable("id")Integer id,Model model)
//	{
//		System.out.println("id "+id);
//		
//		Optional<Register> registerOptional = this.registerService.getRegister(id);
//		Register register= registerOptional.get();
//		model.addAttribute("register",register);
//		return "student/index-profile";
//	}
	
}
