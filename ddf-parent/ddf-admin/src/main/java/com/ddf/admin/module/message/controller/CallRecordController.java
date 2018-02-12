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
import com.ddf.entity.message.dto.CallRecord;
import com.ddf.entity.message.query.CallRecordQuery;
import com.ddf.entity.message.vo.CallRecordVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.reference.message.CallRecordReference;
import com.ddf.util.ListUtil;

/**
 * 通话记录
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 15:56:39
 */
 
@Controller
@RequestMapping("/message/callRecord")
public class CallRecordController {
	@Autowired
	private CallRecordReference callRecordReference;
	
	@GetMapping()
	@RequiresPermissions("message:callRecord:callRecord")
	String CallRecord(){
	    return "message/callRecord/callRecord";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("message:callRecord:callRecord")
	public PageUtils list(@ModelAttribute CallRecordQuery callRecordQuery){
		//查询列表数据
		ApiResponse<Page<CallRecordVo>> result=callRecordReference.batchquery(callRecordQuery);
		if( result != null && result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			Page<CallRecordVo> page=result.getData();
			if(page!=null&&!ListUtil.isEmpty(page.getList())) {
				PageUtils pageUtils = new PageUtils(page.getList(), (int)page.getTotalCount());
				return pageUtils;
			}
		}
		return new PageUtils(new ArrayList<CallRecordVo>(), 0);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("message:callRecord:add")
	String add(){
	    return "message/callRecord/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("message:callRecord:edit")
	String edit(@PathVariable("id") String id,Model model){
		ApiResponse<CallRecord> result = callRecordReference.query(id);
		if(result!=null && result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			model.addAttribute("callRecord", result.getData());
		}
	    return "message/callRecord/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("message:callRecord:add")
	public R save( CallRecord callRecord){
		ApiResponse<Boolean> result = callRecordReference.create(callRecord);
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
	@RequiresPermissions("message:callRecord:edit")
	public R update( CallRecord callRecord){
		ApiResponse<Boolean> result = callRecordReference.modify(callRecord);
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
	@RequiresPermissions("message:callRecord:remove")
	public R remove( String id){
		ApiResponse<Boolean> result = callRecordReference.remove(id);
		if(result!=null && result.getResult().equals(ApiResponseResult.SUCCESS.name()) && result.getData()==true){
			return R.ok();
		}else if(result!=null){
			return R.error(1,result.getMessage());
		}
		return R.error();
	}
//	
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/batchRemove")
//	@ResponseBody
//	@RequiresPermissions("message:callRecord:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		callRecordReference.batchRemove(ids);
//		return R.ok();
//	}
//	
}
