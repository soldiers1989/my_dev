package com.ss.config.client.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.config.client.Config;



@RestController
public class NoRefreshApi {
	
	@Value("${member}")
	private String member;
	@Autowired
	private Config config;

	@RequestMapping("/no/refresh/test")
    public String test(){
		return member+"["+config.getMember()+"]";
    }
	
	
}
