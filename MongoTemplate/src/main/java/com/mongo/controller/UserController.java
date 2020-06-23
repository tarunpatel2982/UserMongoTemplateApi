package com.mongo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.bean.Account;
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
	 @Autowired
	 UserDaoImpl u;
	 @CrossOrigin(origins="http://localhost:4200") 
	 @GetMapping("/edit/{id}")
	    public User editUser(@PathVariable("id") String id ) {
	            System.out.println("test" + id);
	        return  u.findUserId(id);
	    }
	 @CrossOrigin(origins="http://localhost:4200") 	
	 @PutMapping("/update/{id}")
	    public Boolean update(@PathVariable("id") String id, @RequestBody User user ) {
	            System.out.println("test" + id);
	          
	        return  u.update(id, user);
	    }
	 
	 @PostMapping("/login" )
	 public String signUp(@RequestBody Account account,HttpSession httpSession )
	 {
		 
		 System.out.println("test 1 ");
		 Account account2= service.login(account.getUserName(),account.getPassword());
		 System.out.println("ak : " + account2);
		 if(account2 == null)
		 {
			 System.out.println("invlid ");
			 return null;
			 
		 }
		 else
		 {
			
			 httpSession.setAttribute("userName", account.getUserName());
			 System.out.println("test session : " + httpSession);
			 return null;
		 }
	 }
	 
	 @PostMapping("/addAC")
	    public Boolean AddAC(@RequestBody Account account ) {
	            System.out.println("test ok" );
	          
	        return  u.signUp(account);
	    }
}
