package com.ddf.admin.module.message.controller;

import java.util.ArrayList;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddf.admin.common.utils.PageUtils;
import com.ddf.admin.common.utils.R;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.SysMessageAdmin;
import com.ddf.entity.message.query.SysMessageAdminQuery;
import com.ddf.entity.message.vo.SysMessageAdminVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.reference.message.SysMessageAdminReference;
import com.ddf.util.ListUtil;

/**
 * 消息管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 17:27:19
 */
 
@Controller
@RequestMapping("/message/sysMessageAdmin")
public class SysMessageAdminController {
	@Autowired
	private SysMessageAdminReference sysMessageAdminReference;
	
	@GetMapping()
	@RequiresPermissions("message:sysMessageAdmin:sysMessageAdmin")
	String SysMessageAdmin(){
	    return "message/sysMessageAdmin/sysMessageAdmin";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("message:sysMessageAdmin:sysMessageAdmin")
	public PageUtils list(@ModelAttribute SysMessageAdminQuery sysMessageAdminQuery){
		//查询列表数据
		ApiResponse<Page<SysMessageAdminVo>> result=sysMessageAdminReference.batchquery(sysMessageAdminQuery);
		if( result != null && result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			Page<SysMessageAdminVo> page=result.getData();
			if(page!=null&&!ListUtil.isEmpty(page.getList())) {
				PageUtils pageUtils = new PageUtils(page.getList(), (int)page.getTotalCount());
				return pageUtils;
			}
		}
		return new PageUtils(new ArrayList<SysMessageAdminVo>(), 0);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("message:sysMessageAdmin:add")
	String add(){
	    return "message/sysMessageAdmin/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("message:sysMessageAdmin:edit")
	String edit(@PathVariable("id") String id,Model model){
		ApiResponse<SysMessageAdmin> result = sysMessageAdminReference.query(id);
		if(result!=null && result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			model.addAttribute("sysMessageAdmin", result.getData());
		}
	    return "message/sysMessageAdmin/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("message:sysMessageAdmin:add")
	public R save( SysMessageAdmin sysMessageAdmin){
		ApiResponse<Boolean> result = sysMessageAdminReference.create(sysMessageAdmin);
		if(result!=null && result.getResult().equals(ApiResponseResult.SUCCESS.name()) && result.getData()==true){
			return R.ok();
		}else if(result!=null){
			return R.error(result.getMessage());
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("message:sysMessageAdmin:edit")
	public R update( SysMessageAdmin sysMessageAdmin){
		ApiResponse<Boolean> result = sysMessageAdminReference.modify(sysMessageAdmin);
		if(result!=null && result.getResult().equals(ApiResponseResult.SUCCESS.name()) && result.getData()==true){
			return R.ok();
		}else if(result!=null){
			return R.error(1,result.getMessage());
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("message:sysMessageAdmin:remove")
	public R remove( String id){
		ApiResponse<Boolean> result = sysMessageAdminReference.remove(id);
		if(result!=null && result.getResult().equals(ApiResponseResult.SUCCESS.name()) && result.getData()==true){
			return R.ok();
		}else if(result!=null){
			return R.error(1,result.getMessage());
		}
		return R.error();
	}
	
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/batchRemove")
//	@ResponseBody
//	@RequiresPermissions("message:sysMessageAdmin:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		sysMessageAdminReference.batchRemove(ids);
//		return R.ok();
//	}
//	
}
