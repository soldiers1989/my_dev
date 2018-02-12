package com.ddf.student.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.student.query.StudentQuery;
import com.ddf.entity.student.vo.StudentVo;
import com.ddf.entity.student.dto.Student;

/**
 * student DAO接口
 * @author robot
 * @version 2017-11-29
 */
public interface StudentDao extends CrudDao<Student,StudentVo,StudentQuery> {
	
}