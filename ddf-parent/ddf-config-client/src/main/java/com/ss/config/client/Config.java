package com.ss.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Configuration
/*@ImportResource(locations={"classpath:/component/redis/spring-redis.xml"})
@PropertySource("classpath:/redis.properties")*/
@RefreshScope
@Component
public class Config {
	
	@Value("${member}")
	private String member;
	

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}
	
}
