package com.ddf.feign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.entity.student.dto.User;
import com.ddf.feign.reference.UserReference;

@Service
public class UserService {

	@Autowired
	private UserReference userReference;
	
	public User query(String id,boolean bz){
		return userReference.query(id);
	}
	
	public boolean create(User user) {
		return userReference.create(user);
	}

	public boolean modify(User user) {
		return userReference.modify(user);
	}

	public boolean remove(String id) {
		return userReference.remove(id);
	}
	
}
