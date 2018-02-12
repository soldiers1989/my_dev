package com.ddf.student;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ImportResource(locations={"classpath:/component/redis/spring-redis.xml","classpath:/component/dao/dao.xml"})
@PropertySource({"classpath:/config.properties"})
public class Config {

}
