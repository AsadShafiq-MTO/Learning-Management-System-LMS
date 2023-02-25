package com.lmsportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/student")
public class StudentController {
	
					
	@GetMapping("/student-dashboard")
	public String studentDashboard(Model model)
	{
		model.addAttribute("student","Student Dashboard");
		return "student/dashboard";
	}
	
	@GetMapping("/mailbox-student")
	public String mailboxStudent(Model model)
	{
		model.addAttribute("title","Student Mailbox - Learning-Management-System");
		return "mailbox/mailbox";
	}
	
	@GetMapping("/mailbox-compose-student")
	public String mailboxComposeStudent(Model model)
	{
		model.addAttribute("title","Student Mailbox Compose - Learning-Management-System");
		return "mailbox/mailbox-compose";
	}
	
	@GetMapping("/mailbox-read-student")
	public String mailboxReadStudent(Model model)
	{
		model.addAttribute("title","Student Mailbox Read - Learning-Management-System");
		return "mailbox/mailbox-read";
	}
	
	@GetMapping("/basic-calendar-student")
	public String basicCalenderStudent(Model model)
	{
		model.addAttribute("title","Student Basic Calendar - Learning-Management-System");
		return "calendar/basic-calendar";
	}
	
	@GetMapping("/list-view-calendar-student")
	public String ListViewCalenderStudent(Model model)
	{
		model.addAttribute("title","Student List View Calendar - Learning-Management-System");
		return "calendar/list-view-calendar";
	}

	
	
	
	
	
	
	
	
	
	
	
}
