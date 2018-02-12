/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.student.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.student.dto.User;
import com.ddf.student.service.simple.UserService;

/**
 * user Api
 * @author robot
 * @version 2018-01-02
 */
@RestController
public class UserApi {

	@Autowired
	private UserService userService;

	@ApiOperation(value="查询单个User")
	@RequestMapping(value = "/user/query",method = {RequestMethod.GET})
	public User query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return userService.query4id(id);
	}

	@ApiOperation(value="创建User")
	@RequestMapping(value = "/user/create",method = {RequestMethod.POST,RequestMethod.GET})
	public boolean create(@RequestBody User user){
		return userService.create(user);
	}

	@ApiOperation(value="修改User信息")
	@RequestMapping(value = "/user/modify",method = {RequestMethod.PUT})
	public boolean modify(@RequestBody User user){
		return userService.modify(user);
	}

	@ApiOperation(value="删除User信息")
	@RequestMapping(value = "/user/remove",method = {RequestMethod.DELETE})
	public boolean remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return userService.remove(id);
	}

}