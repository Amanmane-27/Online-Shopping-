package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.example.demo.DTO.UserLoginDTO;
import com.example.demo.entity.User;
import com.example.demo.service.IMP.UserServicesImp;

@Controller
public class Authentication {

	@Autowired
	private UserServicesImp service;

	@GetMapping({"/", "/loginPage"})
	public String openLogin(Model model) {
		model.addAttribute("user", new UserLoginDTO());
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(
			@ModelAttribute("user") UserLoginDTO login,
			Model model) {

		User validUser = service.login(login);

		if (validUser != null) {
			// Redirect to main page with userId so cart/order features work
			return "redirect:/main/" + validUser.getId();
		} else {
			model.addAttribute("errorMsg", "Invalid email or password ❌");
			return "login";
		}
	}
}
