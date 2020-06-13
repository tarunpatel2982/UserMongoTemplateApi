package com.mongo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongo.bean.User;

@Repository
public class UserDaoImpl  implements UserDao{

	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<User> getUser()
	{
		return (List<User>) mongoTemplate.findAll(User.class,"mycollection");
	}
}
