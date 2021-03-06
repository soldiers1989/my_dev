package com.ddf.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.student.dto.Student;
import com.ddf.feign.service.StudentService;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;
    
    @RequestMapping(value = "/student/query")
    public Student queryStudent(@RequestParam String id,boolean bz){
        return studentService.queryStudent(id,bz);
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "this is feign index";
    }

}