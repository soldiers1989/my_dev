package com.ddf.student.service.simple;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.jdbc.base.CrudService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.student.dto.Student;
import com.ddf.entity.student.query.StudentQuery;
import com.ddf.entity.student.vo.StudentVo;
import com.ddf.student.dao.StudentDao;


@Service
public class StudentService extends CrudService<Student,StudentVo>{
	
	@Autowired
	private StudentDao studentDao;

	
}
