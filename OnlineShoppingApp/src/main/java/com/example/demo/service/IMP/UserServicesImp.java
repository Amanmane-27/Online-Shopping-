package com.example.demo.service.IMP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.UserLoginDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;

@Service
public class UserServicesImp implements UserService {

	@Autowired
	private UserRepo repo;
	
	
	@Override
	public boolean register(User user) {
		    try {

		        if (repo.findByEmail(user.getEmail()) != null) {
		            return false; 
		        }

		        if (repo.findByPhoneNumber(user.getPhoneNumber()) != null) {
		            return false; 
		        }
		        repo.save(user);
		        return true;

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return false;
		}
	

	
	
	@Override
	public User login(UserLoginDTO login) {

	    try {

	        User user = repo.findByEmail(login.getEmail());

	        if (user != null && user.getPassword().equals(login.getPassword()) ) {

	                return user;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}
	

	
	
	
}

