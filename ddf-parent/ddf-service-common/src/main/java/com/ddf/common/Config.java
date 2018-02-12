package com.ddf.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ImportResource(locations={"classpath:/service-beans.xml"})
public class Config {

}
