package com.ddf.rent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.ddf.rent.dao")
@ComponentScan(basePackages={"com.ddf.component","com.ddf.rent","com.ddf.reference"})
@EnableFeignClients(basePackages={"com.ddf.reference"})
public class RentApplication {
	public static void main(String[] args) {
		SpringApplication.run(RentApplication.class, args);
	}
}
