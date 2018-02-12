package com.ddf.feign.reference;

import org.springframework.stereotype.Component;

import com.ddf.entity.student.dto.Student;

@Component
public class StudentReferenceFallBack implements StudentReference {
	
    @Override
    public Student queryStudent(String id) {
    	Student s = new Student();
    	s.setId("defaultId");
    	s.setAge(0);
    	s.setName("defaultName");
    	return s;
    }

	@Override
	public Student queryStudent4id(String id) {
		Student s = new Student();
    	s.setId("defaultId");
    	s.setAge(0);
    	s.setName("defaultName");
    	return s;
	}
}
