package com.mongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongo.bean.Account;
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

//	@Override
//	public void AddUser(User user) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public boolean AddUser(User user) {
		// TODO Auto-generated method stub
		return dao.AddUser(user);
	}

	@Override
	public Boolean deleteUser(String id) {
		// TODO Auto-generated method stub
		return dao.deleteUser(id);
	}

	@Override
	public Account login(String userName, String password) {
		// TODO Auto-generated method stub
		return dao.login(userName, password);
	}

	@Override
	public boolean signUp(Account account) {
		// TODO Auto-generated method stub
		
		return dao.signUp(account);
	}

	@Override
	public void changeProfile(Account account) {
		// TODO Auto-generated method stub
		dao.changeProfile(account);
	}
	
}
