package com.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.bean.User;
import com.mongo.service.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	UserServiceImpl service;
	
	
	 @CrossOrigin(origins="http://localhost:4200")  
	 @RequestMapping(value = "/get",method = RequestMethod.GET)
	 public List<User> getData(){
		 return service.getUser();
	 }
	 
	 
	 
	 
}
