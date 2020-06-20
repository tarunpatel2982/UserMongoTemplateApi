package com.mongo.dao;

import java.util.List;

import com.mongo.bean.User;

public interface UserDao {

	public List<User> getUser();
	
	public Boolean AddUser(User user);
	public Boolean deleteUser(String id);

	
	
	
	
}
