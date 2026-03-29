package com.example.demo.service;

import com.example.demo.DTO.UserLoginDTO;
import com.example.demo.entity.User;

public interface UserService  {

	 boolean register (User user);
	
	 User login(UserLoginDTO login);
	
}
