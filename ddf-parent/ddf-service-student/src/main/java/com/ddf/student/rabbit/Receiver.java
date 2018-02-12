package com.ddf.student.rabbit;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  
@RabbitListener(queues = "hello")  
public class Receiver {  
  
    private static final Logger log = LoggerFactory.getLogger(Receiver.class);  
  
    @RabbitHandler  
    public void process(String hello) {  
        log.info("Receiver : " + hello);  
    }  
}
