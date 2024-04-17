package com.ray.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ray.crm.dto.RegisteredUser;
import com.ray.crm.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	private final UserService userService;

	@Autowired
	public RegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	@GetMapping("/showRegistrationForm")
	public String showRegistrationForm(Model theModel) {
		theModel.addAttribute("registeredUser", new RegisteredUser());
		return "registration-form";
	}
	
	@PostMapping("processRegistrationForm")
	public String processRegistrationForm(@ModelAttribute("registeredUser") RegisteredUser registeredUser) {
		userService.save(registeredUser);
		return "login";
	}
}
