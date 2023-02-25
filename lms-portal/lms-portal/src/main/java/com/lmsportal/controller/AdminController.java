package com.lmsportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/admin")

public class AdminController {

	//@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/dashboard-admin")
	public String dashboardAdmin(Model model)
	{
		model.addAttribute("title","Admin Dashboard - Learning-Management-System");
		return "admin/dashboard";
	}
	
	@GetMapping("/mailbox-admin")
	public String mailboxAdmin(Model model)
	{
		model.addAttribute("title","Admin Mailbox - Learning-Management-System");
		return "mailbox/mailbox";
	}
	
	@GetMapping("/mailbox-compose-admin")
	public String mailboxComposeAdmin(Model model)
	{
		model.addAttribute("title","Admin Mailbox Compose - Learning-Management-System");
		return "mailbox/mailbox-compose";
	}
	
	@GetMapping("/mailbox-read-admin")
	public String mailboxReadAdmin(Model model)
	{
		model.addAttribute("title","Admin Mailbox Read - Learning-Management-System");
		return "mailbox/mailbox-read";
	}
	
	@GetMapping("/basic-calendar-admin")
	public String basicCalenderAdmin(Model model)
	{
		model.addAttribute("title","Admin Basic Calendar - Learning-Management-System");
		return "calendar/basic-calendar";
	}
	
	@GetMapping("/list-view-calendar-admin")
	public String ListViewCalenderAdmin(Model model)
	{
		model.addAttribute("title","Admin List View Calendar - Learning-Management-System");
		return "calendar/list-view-calendar";
	}
	
	@GetMapping("/review")
	public String review(Model model)
	{
		model.addAttribute("title","Review - Learning-Management-System");
		return "admin/review";
	}
	
	@GetMapping("/add-listing")
	public String addlisting(Model model)
	{
		model.addAttribute("title","Add-listing - Learning-Management-System");
		return "admin/add-listing";
	}	
	
}
