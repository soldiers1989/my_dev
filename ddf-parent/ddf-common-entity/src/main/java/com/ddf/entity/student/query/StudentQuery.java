package com.ddf.entity.student.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.student.dto.Student;

/**
 * student EntityQuery
 * @author robot
 * @version 2017-11-29
 */
public class StudentQuery extends Query {

	private static final long serialVersionUID = 1L;

	private Student student;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}