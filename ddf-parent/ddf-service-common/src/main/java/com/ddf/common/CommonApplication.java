package com.ddf.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.ddf.common.dao")
@ComponentScan(basePackages={"com.ddf.component","com.ddf.common","com.ddf.reference"})
@EnableFeignClients(basePackages={"com.ddf.reference"})
public class CommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }
}
