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
import org.springframework.stereotype.Repository;

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
	
}
