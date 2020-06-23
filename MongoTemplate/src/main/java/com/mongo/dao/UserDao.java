package com.mongo.dao;

import java.util.List;

import com.mongo.bean.Account;
import com.mongo.bean.User;

public interface UserDao {

	public List<User> getUser();
	
	public Boolean AddUser(User user);
	public Boolean deleteUser(String id);

	
	public Account login(String userName ,String password);
	public boolean signUp(Account account);
	public void changeProfile(Account account);
	
	
}
