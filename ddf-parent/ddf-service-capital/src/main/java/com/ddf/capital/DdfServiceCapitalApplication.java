package com.ddf.capital;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.ddf.capital.dao")
@EnableFeignClients(basePackages={"com.ddf.reference"})
@EnableCircuitBreaker
@ComponentScan(basePackages={"com.ddf.reference","com.ddf.capital"})
public class DdfServiceCapitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(DdfServiceCapitalApplication.class, args);
	}
}
