package com.ddf.feign.reference;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.student.dto.Student;

@FeignClient(value = "service-student",fallback = StudentReferenceFallBack.class)
public interface  StudentReference {

	@RequestMapping(value = "/student/query",method = RequestMethod.GET)
	Student queryStudent(@RequestParam(value = "id") String id);
	
	@RequestMapping(value = "/student/query/{id}",method = RequestMethod.GET)
	Student queryStudent4id(@PathVariable("id") String id);

}