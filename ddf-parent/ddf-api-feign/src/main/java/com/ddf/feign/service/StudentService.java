package com.ddf.feign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.entity.student.dto.Student;
import com.ddf.feign.reference.StudentReference;

@Service
public class  StudentService {

	@Autowired
	StudentReference studentReference;
	
	
	public Student queryStudent(String id,boolean bz){
		Student stu =  studentReference.queryStudent4id(id);
		if(bz){
			stu.setName("【"+stu.getName()+"】");
		}
		
		return stu;
	}

}