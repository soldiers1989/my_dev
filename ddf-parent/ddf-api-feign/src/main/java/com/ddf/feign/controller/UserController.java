package com.ddf.feign.controller;

import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.student.dto.User;
import com.ddf.feign.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user/query")
    public User query(@RequestParam String id,boolean bz){
        return userService.query(id,bz);
    }
	
	@RequestMapping(value = "/user/create")
	public boolean create(@ModelAttribute User user){
		return userService.create(user);
	}

	@RequestMapping(value = "/user/modify")
	public boolean modify(@ModelAttribute User user){
		return userService.modify(user);
	}

	@RequestMapping(value = "/user/remove")
	public boolean remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return userService.remove(id);
	}
}
