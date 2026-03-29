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
	
	
	@GetMapping({"/","/loginPage"})
	public String openLogin(Model model) {
		model.addAttribute("user",new UserLoginDTO());
		return "login";
	}
//	@PostMapping("/login")
//	public String loginUser(@ModelAttribute("user") UserLoginDTO login,Model model,UserLoginDTO userlogin) {
//		
//
//	    boolean status = service.login(login);
//
//	    if (status) {
//	    		System.out.println(login);
//	    		model.addAttribute("Success","User Loged in successfully"+ userlogin.getEmail());
//	    		return "main";
//	    } else {
//	    	model.addAttribute("error","invalid email or password");
//	    }
//	    System.out.println("some thing went wrong");
//	    return "login";
//
//	}
	
	@PostMapping("/login")
	public String loginUser(
			@ModelAttribute("user") UserLoginDTO login,
			Model model) {
		
		User validUser = service.login(login);
		
		if (validUser != null) {
			
//			return "redirect:/main/" + validUser.getId();
			return "main";
			
		} else {
			model.addAttribute("errorMsg", "Invalid email or password ❌");
			return "login";
		}
	}
}
