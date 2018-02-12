package com.ss.config.client.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.config.client.Config;



@RefreshScope
@RestController
public class RefreshApi {
	
	@Autowired
	private Config config;
	
	@Value("${member}")
	private String member;

	@RequestMapping("/refresh/test")
    public String test(){
        return member+"["+config.getMember()+"]";
    }
	
	
}
