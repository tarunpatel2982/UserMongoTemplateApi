package com.mongo.service;

import java.util.List;

import com.mongo.bean.User;

public interface UserService {

	public List<User> getUser();
	
	public boolean AddUser(User user);
	public Boolean deleteUser(String id);
}
