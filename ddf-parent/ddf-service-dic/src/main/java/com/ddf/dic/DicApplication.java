package com.ddf.dic;




import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.ddf.dic.dao")
@ComponentScan(basePackages={"com.ddf.dic","com.ddf.reference"})
@EnableFeignClients(basePackages={"com.ddf.reference"})
public class DicApplication {

    public static void main(String[] args) {
        SpringApplication.run(DicApplication.class, args);
    }
}
