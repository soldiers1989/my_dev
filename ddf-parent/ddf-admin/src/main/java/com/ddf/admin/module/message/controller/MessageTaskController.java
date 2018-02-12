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
import com.ddf.entity.message.dto.MessageTask;
import com.ddf.entity.message.query.MessageTaskQuery;
import com.ddf.entity.message.vo.MessageTaskVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.reference.message.MessageTaskReference;
import com.ddf.util.ListUtil;

/**
 * 消息库任务表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 17:27:17
 */
 
@Controller
@RequestMapping("/message/messageTask")
public class MessageTaskController {
	@Autowired
	private MessageTaskReference messageTaskReference;
	
	@GetMapping()
	@RequiresPermissions("message:messageTask:messageTask")
	String MessageTask(){
	    return "message/messageTask/messageTask";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("message:messageTask:messageTask")
	public PageUtils list(@ModelAttribute MessageTaskQuery messageTaskQuery){
		//查询列表数据
		ApiResponse<Page<MessageTaskVo>> result=messageTaskReference.batchquery(messageTaskQuery);
		if( result != null && result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			Page<MessageTaskVo> page=result.getData();
			if(page!=null&&!ListUtil.isEmpty(page.getList())) {
				PageUtils pageUtils = new PageUtils(page.getList(), (int)page.getTotalCount());
				return pageUtils;
			}
		}
		return new PageUtils(new ArrayList<MessageTaskVo>(), 0);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("message:messageTask:add")
	String add(){
	    return "message/messageTask/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("message:messageTask:edit")
	String edit(@PathVariable("id") String id,Model model){
		ApiResponse<MessageTask> result = messageTaskReference.query(id);
		if(result!=null && result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			model.addAttribute("messageTask", result.getData());
		}
	    return "message/messageTask/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("message:messageTask:add")
	public R save( MessageTask messageTask){
		ApiResponse<Boolean> result = messageTaskReference.create(messageTask);
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
	@RequiresPermissions("message:messageTask:edit")
	public R update( MessageTask messageTask){
		ApiResponse<Boolean> result = messageTaskReference.modify(messageTask);
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
	@RequiresPermissions("message:messageTask:remove")
	public R remove( String id){
		ApiResponse<Boolean> result = messageTaskReference.remove(id);
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
//	@RequiresPermissions("message:messageTask:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		messageTaskReference.batchRemove(ids);
//		return R.ok();
//	}
	
}
