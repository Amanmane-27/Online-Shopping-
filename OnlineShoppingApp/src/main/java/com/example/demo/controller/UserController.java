package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;
import com.example.demo.service.IMP.UserServicesImp;

import jakarta.validation.Valid;

@Controller

public class UserController {
	
	@Autowired
	private UserServicesImp service;	
		
		
	@GetMapping("/regPage")
	public String openRegister(Model model) {
		model.addAttribute("user",new User());
		return "register";
	}
	
	@PostMapping("/regForm")
	public String submitFrom(@Valid @ModelAttribute("user") User user,Model model,BindingResult result) {
		
		if(result.hasErrors()) {
			return "register";
		}
		
		boolean status = service.register(user); 
		if(status) {
			model.addAttribute("Success","User Register Successfully !!!!!!!!!!");
			return "redirect:/loginPage";
		}else {
			model.addAttribute("errorMsg","User Failed due to some issues !!!!!!!!!!");
			return "register";
		}		
	}
}
