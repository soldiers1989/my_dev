package com.ddf.feign.reference;

import org.springframework.stereotype.Component;

import com.ddf.entity.student.dto.User;

@Component
public class UserReferenceFallBack implements UserReference {

	@Override
	public User query(String id) {
		System.out.println("query报错啦");
		return null;
	}

	@Override
	public boolean create(User user) {
		System.out.println("create报错啦");
		return false;
	}

	@Override
	public boolean modify(User user) {
		System.out.println("modify报错啦");
		return false;
	}

	@Override
	public boolean remove(String id) {
		System.out.println("remove报错啦");
		return false;
	}

}
