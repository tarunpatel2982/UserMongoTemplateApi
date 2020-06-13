package com.mongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongo.bean.User;
import com.mongo.dao.UserDaoImpl;

@Service 
public class UserServiceImpl implements UserService {

	@Autowired
	UserDaoImpl dao;
	
	public List<User> getUser()
	{
		return dao.getUser();
	}
	
}
