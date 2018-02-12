package com.ddf.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ddf.entity.student.dto.Student;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class StudentService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "queryError")
    public Student queryStudent(String id) {
    	if(id.equals("fallback")){
    		System.out.println(0/0);
    	}
        Student stu = restTemplate.getForObject("http://service-student-old/student/query?id="+id,Student.class);
        return stu;
    }
    
    
    public Student queryError(String id) {
    	Student s = new Student();
    	s.setId("defaultId");
    	s.setAge(0);
    	s.setName("defaultName");
    	return s;
    }

}