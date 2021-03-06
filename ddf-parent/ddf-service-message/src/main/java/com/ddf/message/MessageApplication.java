package com.ddf.message;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.ddf.message.dao")
@ComponentScan(basePackages={"com.ddf.component,com.ddf.message,com.ddf.reference"})
@EnableFeignClients(basePackages={"com.ddf.reference"})
public class MessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }
}
