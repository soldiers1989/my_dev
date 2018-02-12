package com.ddf.admin.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddf.admin.system.domain.StudentDO;
import com.ddf.admin.system.domain.StudentType;
import com.ddf.admin.system.service.StudentService;
import com.ddf.admin.common.utils.PageUtils;
import com.ddf.admin.common.utils.Query;
import com.ddf.admin.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-01-27 16:11:43
 */
 
@Controller
@RequestMapping("/system/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@GetMapping()
	@RequiresPermissions("system:student:student")
	String Student(){
	    return "system/student/student";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:student:student")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentDO> studentList = studentService.list(query);
		int total = studentService.count(query);
		PageUtils pageUtils = new PageUtils(studentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:student:add")
	String add(Model model){
		model.addAttribute("types", StudentType.values());
	    return "system/student/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:student:edit")
	String edit(@PathVariable("id") String id,Model model){
		model.addAttribute("types", StudentType.values());
		StudentDO student = studentService.get(id);
		model.addAttribute("student", student);
	    return "system/student/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:student:add")
	public R save( StudentDO student){
		if(studentService.save(student)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:student:edit")
	public R update( StudentDO student){
		studentService.update(student);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:student:remove")
	public R remove( String id){
		if(studentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:student:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		studentService.batchRemove(ids);
		return R.ok();
	}
	
}
