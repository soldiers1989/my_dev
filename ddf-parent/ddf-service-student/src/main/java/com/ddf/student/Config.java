package com.ddf.student;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ImportResource(locations={"classpath:/component/redis/spring-redis.xml"})
@PropertySource("classpath:/redis.properties")
public class Config {

	@Bean  
    public Queue helloQueue(){  
        return new Queue("hello");  
    } 
}
