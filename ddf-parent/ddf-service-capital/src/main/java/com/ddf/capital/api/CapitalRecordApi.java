/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.capital.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.ddf.entity.capital.dto.CapitalRecord;
import com.ddf.capital.service.simple.CapitalRecordService;

/**
 * capital_record Api
 * @author robot
 * @version 2018-01-10
 */
@RestController
public class CapitalRecordApi extends BaseApi{

	@Autowired
	private CapitalRecordService capitalRecordService;

	@ApiOperation(value="查询单个CapitalRecord")
	@RequestMapping(value = "/capitalRecord/query",method = {RequestMethod.GET})
	public CapitalRecord query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return capitalRecordService.query4id(id);
	}

	@ApiOperation(value="创建CapitalRecord")
	@RequestMapping(value = "/capitalRecord/create",method = {RequestMethod.POST})
	public boolean create(@ModelAttribute CapitalRecord capitalRecord){
		return capitalRecordService.create(capitalRecord);
	}

	@ApiOperation(value="修改CapitalRecord信息")
	@RequestMapping(value = "/capitalRecord/modify",method = {RequestMethod.POST})
	public boolean modify(@ModelAttribute CapitalRecord capitalRecord){
		return capitalRecordService.modify(capitalRecord);
	}

	@ApiOperation(value="删除CapitalRecord信息")
	@RequestMapping(value = "/capitalRecord/remove",method = {RequestMethod.POST})
	public boolean remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return capitalRecordService.remove(id);
	}

}