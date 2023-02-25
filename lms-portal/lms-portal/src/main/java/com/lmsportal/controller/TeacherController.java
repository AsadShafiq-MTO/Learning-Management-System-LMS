package com.lmsportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/teacher")
public class TeacherController {

	
	@GetMapping("/teacher-dashboard")
	private String teacherDashboard(Model model)
	{
		model.addAttribute("teacher","Dashboard LMS-Teacher Dashboard")	;	
		
		return "teacher/dashboard";
	}	
	
	@GetMapping("/mailbox-teacher")
	public String mailboxTeacher(Model model)
	{
		model.addAttribute("title","Teacher Mailbox - Learning-Management-System");
		return "mailbox/mailbox";
	}
	
	@GetMapping("/mailbox-compose-teacher")
	public String mailboxComposeTeacher(Model model)
	{
		model.addAttribute("title","Teacher Mailbox Compose - Learning-Management-System");
		return "mailbox/mailbox-compose";
	}
	
	@GetMapping("/mailbox-read-teacher")
	public String mailboxReadTeacher(Model model)
	{
		model.addAttribute("title","Teacher Mailbox Read - Learning-Management-System");
		return "mailbox/mailbox-read";
	}
	
	@GetMapping("/basic-calendar-teacher")
	public String basicCalenderTeacher(Model model)
	{
		model.addAttribute("title","Teacher Basic Calendar - Learning-Management-System");
		return "calendar/basic-calendar";
	}
	
	@GetMapping("/list-view-calendar-teacher")
	public String ListViewCalenderTeacher(Model model)
	{
		model.addAttribute("title","Teacher List View Calendar - Learning-Management-System");
		return "calendar/list-view-calendar";
	}
	
}
