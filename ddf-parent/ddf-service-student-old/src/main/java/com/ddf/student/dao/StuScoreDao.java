package com.ddf.student.dao;

import org.springframework.stereotype.Repository;

import com.ddf.component.jdbc.base.CrudDao;
import com.ddf.component.jdbc.base.Query;
import com.ddf.component.jdbc.dto.query.Condition;
import com.ddf.component.jdbc.dto.query.Conditions;
import com.ddf.component.jdbc.dto.query.Sort;
import com.ddf.component.jdbc.eo.OperatorType;
import com.ddf.component.jdbc.eo.SortType;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.student.query.StuScoreQuery;
import com.ddf.entity.student.vo.StuScoreVo;
import com.ddf.entity.student.dto.StuScore;

/**
 * stu_score DAO接口
 * @author robot
 * @version 2017-11-28
 */
 @Repository
public class StuScoreDao extends CrudDao<StuScore,StuScoreVo> {
	
}