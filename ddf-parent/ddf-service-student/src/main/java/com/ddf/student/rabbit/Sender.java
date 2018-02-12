package com.ddf.student.rabbit;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  
public class Sender {  
  
    private static final Logger log = LoggerFactory.getLogger(Sender.class);  
    @Autowired  
    private AmqpTemplate amqpTemplate;  
  
    public void send() {  
        String context = "hello " + new Date();  
        log.info("Sender : " + context);  
        this.amqpTemplate.convertAndSend("hello", context);
    }  
}
