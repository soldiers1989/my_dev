/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.message.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.LodgerComplain;
import com.ddf.entity.message.query.LodgerComplainQuery;
import com.ddf.entity.message.vo.LodgerComplainVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.message.service.simple.LodgerComplainService;

/**
 * lodger_complain Api
 * @author robot
 * @version 2018-01-10
 */
@Api(value = "LodgerComplainApi", tags = "房东举报房客" )
@RestController
public class LodgerComplainApi extends BaseApi{
	
	@Autowired
	private LodgerComplainService lodgerComplainService;

	@ApiOperation(value="查询单个 举报房客")
	@RequestMapping(value = "/lodgerComplain/query",method = {RequestMethod.GET})
	public ApiResponse<LodgerComplain> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		try{
			return ApiResponse.success(lodgerComplainService.query4id(id));
		}catch(Exception e){
			logger.error("查询单个 举报房客，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="创建 举报房客")
	@RequestMapping(value = "/lodgerComplain/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(@RequestBody LodgerComplain lodgerComplain){
		try{
			return ApiResponse.success(lodgerComplainService.create(lodgerComplain));
		}catch(Exception e){
			logger.error("创建 举报房客，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改 举报房客 信息")
	@RequestMapping(value = "/lodgerComplain/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody LodgerComplain lodgerComplain){
		try{
			return ApiResponse.success(lodgerComplainService.modify(lodgerComplain));
		}catch(Exception e){
			logger.error("修改 举报房客 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="删除 举报房客 信息")
	@RequestMapping(value = "/lodgerComplain/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		try{
			return ApiResponse.success(lodgerComplainService.remove(id));
		}catch(Exception e){
			logger.error("删除 举报房客 信息，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="按房客查询 举报房客 列表")
	@RequestMapping(value = "/lodgerComplain/lodger/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<LodgerComplainVo>> query4lodgerId(
			@ApiParam(value = "房客ID",required = true) @RequestParam(required=true) String lodgerId,
			@ApiParam(value = "当前页",required = true) @RequestParam(required=true) int pageNum,
			@ApiParam(value = "每页条数",required = true) @RequestParam(required=true) int pageSize) {
		try{
			Page<LodgerComplainVo> page = lodgerComplainService.query4lodgerId(lodgerId,pageNum,pageSize);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("按房客查询 举报房客 列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="按  举报房客对象 查询 举报房客 列表")
	@RequestMapping(value = "/lodgerComplain/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<LodgerComplainVo>> batchquery(
			@ApiParam(value = "举报房客对象",required = false) @RequestBody(required = false) LodgerComplainQuery lodgerComplainQuery) {
		try{
			Page<LodgerComplainVo> page = lodgerComplainService.query4page(lodgerComplainQuery);
			return ApiResponse.success(page);
		}catch(Exception e){
			logger.error("按  举报房客对象 查询 举报房客 列表，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
}