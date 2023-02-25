package com.lmsportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.lmsportal.model.Register;

@Controller
//@RequestMapping("/user")
public class HomeController {
		
	@GetMapping("/home1")
	public String home1(Model model)
	{
		model.addAttribute("title","Home - Learning-Management-System");		
		return  "index-2";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model)
	{
		model.addAttribute("title","Dashboard - Learning-Management-System");
		return "admin/index";
	}
	
	@GetMapping("/login")
	public String login(Model model)
	{
		model.addAttribute("title","Login - Learning-Management-System");
		return "login";
	}	
	
	@GetMapping("/register")
	public String register(Model model)       
	{
		model.addAttribute("title","Register - Learning-Management-System");
        model.addAttribute("register", new Register());
        return "register";
	}
	
	@GetMapping("/accessDenied")
    public String accessDenied() {
        return "error-404";
    }
}
