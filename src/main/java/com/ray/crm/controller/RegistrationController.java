package com.ray.crm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ray.crm.dao.UserDAO;
import com.ray.crm.dto.RegisteredUser;
import com.ray.crm.entity.User;
import com.ray.crm.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	private final UserService userService;
	private final UserDAO userDAO;

	@Autowired
	public RegistrationController(UserService userService, UserDAO userDAO) {
		super();
		this.userService = userService;
		this.userDAO = userDAO;
	}
	
	
	@GetMapping("/showRegistrationForm")
	public String showRegistrationForm(Model theModel) {
		theModel.addAttribute("registeredUser", new RegisteredUser());
		return "registration-form";
	}
	
	@PostMapping("processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("registeredUser") RegisteredUser registeredUser,
			BindingResult theBindingResult, Model model) {
		
		if (theBindingResult.hasErrors()) {
			return "registration-form";
		}
		
		User existingUser = userDAO.findByUserName(registeredUser.getUserName());
		if (existingUser != null) {
			model.addAttribute("registrationError", "Username already exist");
			return "registration-form";
		}
		
		userService.save(registeredUser);
		return "login";
	}
}
