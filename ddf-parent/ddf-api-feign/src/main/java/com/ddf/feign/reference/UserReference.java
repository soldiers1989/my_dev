package com.ddf.feign.reference;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.student.dto.User;

@FeignClient(value = "service-student",fallback = UserReferenceFallBack.class)
public interface UserReference {

	@RequestMapping(value = "/user/query",method = RequestMethod.GET)
	User query(@RequestParam(value = "id") String id);
	
	@RequestMapping(value = "/user/create",method = RequestMethod.POST) 
	boolean create(User user);

	@RequestMapping(value = "/user/modify",method = RequestMethod.PUT) 
	boolean modify(User user);

	@RequestMapping(value = "/user/remove",method = RequestMethod.DELETE) 
	boolean remove(@RequestParam(value = "id") String id);
}
