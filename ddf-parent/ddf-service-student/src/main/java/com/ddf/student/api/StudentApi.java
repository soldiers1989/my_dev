/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.student.api;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.student.vo.StudentVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.ddf.entity.student.dto.Student;
import com.ddf.entity.student.query.StudentQuery;
import com.ddf.student.service.simple.StudentService;
import com.ddf.util.JsonUtil;

import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * student Api
 * @author robot
 * @version 2017-11-29
 */
@RestController
public class StudentApi {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/student/query",method = {RequestMethod.GET})
	@ApiOperation(value="查询单个Student")
	public Student query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return studentService.query4id(id);
	}
	
	@RequestMapping(value = "/student/query/{id}",method = {RequestMethod.GET})
	@ApiOperation(value="查询单个Student")
	public Student query4id(HttpServletRequest request,@PathVariable("id") String id) {
		return studentService.query4id(id);
	}

	@RequestMapping(value = "/student/create",method = {RequestMethod.POST})
	@ApiOperation(value="创建Student")
	public ApiResponse<Boolean> create(@Valid @ModelAttribute Student student,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
            return ApiResponse.fail4fieldError(bindingResult.getFieldErrors());
        }
    	studentService.create(student);
    	return ApiResponse.success(true);
	}

	@ApiOperation(value="修改学生信息")
	@RequestMapping(value = "/student/modify",method = {RequestMethod.PUT})
	public boolean modify(@ModelAttribute Student student){
		return studentService.modify(student);
	}

	@ApiOperation(value="删除学生信息")
	@RequestMapping(value = "/student/remove",method = {RequestMethod.DELETE})
	public boolean remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return studentService.remove(id);
	}

	@ApiOperation(value="获取学生列表")
	@RequestMapping(value = "/student/list",method = {RequestMethod.GET})
	public List<StudentVo> list(@ModelAttribute  StudentQuery studentQuery){
		StudentQuery query = new StudentQuery();
		query.setStudent(new Student());
		query.buildSortSql(" order by a.create_date desc");
		query.buildPageSql(2,3);
		return studentService.findList(query);
	}

	@RequestMapping(value = "/test",method = {RequestMethod.GET})
	public String list(){
		studentService.test();
		return "hello";
	}

	@ApiIgnore
	@ApiOperation(value = "hi")
	@RequestMapping(value = "hi",method = {RequestMethod.GET})
	public String hi(@ApiParam(value = "name")@RequestParam String name){
		return "hi"+name;
	}

}