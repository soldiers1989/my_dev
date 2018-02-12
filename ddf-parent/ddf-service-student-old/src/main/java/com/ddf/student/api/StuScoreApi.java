/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.student.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.student.dto.StuScore;
import com.ddf.entity.student.query.StuScoreQuery;
import com.ddf.entity.student.vo.StuScoreVo;
import com.ddf.student.service.simple.StuScoreService;

/**
 * stu_score Api
 * @author robot
 * @version 2017-11-28
 */
@RestController
public class StuScoreApi {

	@Autowired
	private StuScoreService stuScoreService;

	@RequestMapping("/stuScore/query")
	public StuScore query(@RequestParam String id) {
		return stuScoreService.query4id(id);
	}

	@RequestMapping("/stuScore/create")
	public boolean create(StuScore stuScore){
		return stuScoreService.create(stuScore);
	}

	@RequestMapping("/stuScore/modify")
	public boolean modify(StuScore stuScore){
		return stuScoreService.modify(stuScore);
	}

	@RequestMapping("/stuScore/remove")
	public boolean remove(String id){
		return stuScoreService.remove(id);
	}

}