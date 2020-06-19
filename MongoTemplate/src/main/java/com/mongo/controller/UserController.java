package com.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.bean.User;
import com.mongo.dao.UserDaoImpl;
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
	 @CrossOrigin(origins="http://localhost:4200") 
	 @RequestMapping(value = "/add",method = RequestMethod.POST)
	 public void addUser(@RequestBody User user)
	 {
		 service.AddUser(user);
		 System.out.println("addd ");
	 }
	 
	 @CrossOrigin(origins="http://localhost:4200") 
	 @DeleteMapping("/delete/{id}")
		public String delete(@PathVariable("id") String id ) {
		 service.deleteUser(id);
			return id;
			
		}
}
