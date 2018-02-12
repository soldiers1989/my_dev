package com.ddf.student.service.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.jdbc.base.CrudService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.student.dto.StuScore;
import com.ddf.entity.student.query.StuScoreQuery;
import com.ddf.entity.student.vo.StuScoreVo;
import com.ddf.student.dao.StuScoreDao;

/**
 * stu_score Service
 * @author robot
 * @version 2017-11-28
 */
@Service
public class StuScoreService extends CrudService<StuScore,StuScoreVo>{
	@Autowired
	private StuScoreDao stuScoreDao;
}