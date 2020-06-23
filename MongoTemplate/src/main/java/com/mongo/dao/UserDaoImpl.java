package com.mongo.dao;

import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.mongo.bean.Account;
import com.mongo.bean.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;

@Repository
public class UserDaoImpl  implements UserDao{

	@Autowired(required=true)
	MongoTemplate mongoTemplate;
	
	public static final String NAME="user";
	
	public List<User> getUser()
	{
		return (List<User>) mongoTemplate.findAll(User.class,"user");
	}

	@Override
	public Boolean AddUser(User user) {
		boolean output=false;
		// TODO Auto-generated method stub
		if(!mongoTemplate.collectionExists(User.class))
		{
			mongoTemplate.createCollection(User.class);
			System.out.println("Cretae collection ");
			 output=false;
		}
		else
		{
			System.out.println("Already Created Collection ");
			user.setId(UUID.randomUUID().toString());
			mongoTemplate.insert(user, NAME);
			 output=true;
		}
		return output;
	}

	@Override
	public Boolean deleteUser(String id) {
		boolean status = false;
		
		// TODO Auto-generated method stub
		try {
			
//			DBObject query = new BasicDBObject();
//			query.put("id", id);
			
			mongoTemplate.remove(new Query(Criteria.where("id").is(id)), User.class, "user");
			status=true;
			System.out.println("delete");
		} catch (Exception e) {
			// TODO: handle exception
			status = false;
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	public User findUserId(String id)
	{
		Query query = new Query(Criteria.where("id").is(id));
		
		System.out.println("test " + query);
 
     // Return user object.
        return   mongoTemplate.findOne(query, User.class,NAME);
 
	}
	
	
	public boolean update(String id,User user)
	{
		boolean status=false;
		try {
			 status=true;  
				mongoTemplate.save(user, NAME);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;  
		
	}

	@Override
	public Account login(String userName, String password) {
		
		System.out.println("test user name : " + userName);
		try {
			Query query = new Query();
			Account account1 = mongoTemplate.findOne(query.addCriteria(
					Criteria.where("userName").is(userName)), Account.class);
			System.out.println("test  account  : "  + account1);
			System.out.println("test name : " + account1.getFullName());
			System.out.println("test password : " + account1.getPassword());
			if( account1 != null)
			{
				System.out.println("contorller password: " + password);
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				if(bCryptPasswordEncoder.matches( password, account1.getPassword()))
				{
					System.out.println("Login");
				}
				else
				{
					System.out.println("not ");
				}
			}
			return account1;
		} catch (Exception e) {
			// TODO: handle exception
			
			return null;
		}
	}

	@Override
	public boolean signUp(Account account) {
		boolean st=false;
		try {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	          account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
			mongoTemplate.insert(account);
			st=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return st;
		
	}

	@Override
	public void changeProfile(Account account) {
		mongoTemplate.save(account);
		
	}
}
