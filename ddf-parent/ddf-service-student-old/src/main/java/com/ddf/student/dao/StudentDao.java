package com.ddf.student.dao;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ddf.component.jdbc.base.CrudDao;
import com.ddf.component.jdbc.base.Query;
import com.ddf.component.jdbc.dto.query.Condition;
import com.ddf.component.jdbc.dto.query.Conditions;
import com.ddf.component.jdbc.dto.query.Sort;
import com.ddf.component.jdbc.eo.OperatorType;
import com.ddf.component.jdbc.eo.SortType;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.student.dto.Student;
import com.ddf.entity.student.query.StudentQuery;
import com.ddf.entity.student.vo.StudentVo;

@Repository
public class StudentDao extends CrudDao<Student,StudentVo>{
	
    

}
