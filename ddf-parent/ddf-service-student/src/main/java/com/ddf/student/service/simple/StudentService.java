package com.ddf.student.service.simple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.base.query.Query;
import com.ddf.entity.student.dto.Student;
import com.ddf.entity.student.query.StudentQuery;
import com.ddf.entity.student.vo.StudentVo;
import com.ddf.student.dao.StudentDao;

/**
 * student Service
 * @author robot
 * @version 2017-11-29
 */
@Service
public class StudentService extends CrudServiceImpl<Student,StudentVo,StudentQuery>{
	@Autowired
	private StudentDao studentDao;
	
	
	public void test(){
		/*Query<StudentQuery> query = new Query<StudentQuery>();
		StudentQuery studentQuery = new StudentQuery();
		studentQuery.setRemark("aaa");
		query.setT(studentQuery);
		query.sort("order by a.create_date desc");
		//query.page(5, 3);
		List<StudentVo>  studentVo = studentDao.findList(query);
		System.out.println(studentVo);*/
		System.out.println("I am service");
	}
}