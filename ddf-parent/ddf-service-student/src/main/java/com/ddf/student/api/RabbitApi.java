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
import com.ddf.student.rabbit.Sender;
import com.ddf.student.service.simple.UserService;

/**
 * user Api
 * @author robot
 * @version 2018-01-02
 */
@RestController
public class RabbitApi {

	 @Autowired  
	 private Sender sender;

	@RequestMapping(value = "/rabbit/test")
	public void remove(){
		 sender.send();  
	}

}