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
import com.ddf.entity.message.dto.SysMessage;
import com.ddf.entity.message.query.SysMessageQuery;
import com.ddf.entity.message.vo.SysMessageVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.reference.message.SysMessageReference;
import com.ddf.util.ListUtil;

/**
 * 消息通知
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 17:27:19
 */
 
@Controller
@RequestMapping("/message/sysMessage")
public class SysMessageController {
	@Autowired
	private SysMessageReference sysMessageReference;
	
	@GetMapping()
	@RequiresPermissions("message:sysMessage:sysMessage")
	String SysMessage(){
	    return "message/sysMessage/sysMessage";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("message:sysMessage:sysMessage")
	public PageUtils list(@ModelAttribute SysMessageQuery sysMessageQuery){
		//查询列表数据
		ApiResponse<Page<SysMessageVo>> result=sysMessageReference.batchquery(sysMessageQuery);
		if( result != null && result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			Page<SysMessageVo> page=result.getData();
			if(page!=null&&!ListUtil.isEmpty(page.getList())) {
				PageUtils pageUtils = new PageUtils(page.getList(), (int)page.getTotalCount());
				return pageUtils;
			}
		}
		return new PageUtils(new ArrayList<SysMessageVo>(), 0);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("message:sysMessage:add")
	String add(){
	    return "message/sysMessage/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("message:sysMessage:edit")
	String edit(@PathVariable("id") String id,Model model){
		ApiResponse<SysMessage> result = sysMessageReference.query(id);
		if(result!=null && result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			model.addAttribute("sysMessage", result.getData());
		}
	    return "message/sysMessage/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("message:sysMessage:add")
	public R save( SysMessage sysMessage){
		ApiResponse<Boolean> result = sysMessageReference.create(sysMessage);
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
	@RequiresPermissions("message:sysMessage:edit")
	public R update( SysMessage sysMessage){
		ApiResponse<Boolean> result = sysMessageReference.modify(sysMessage);
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
	@RequiresPermissions("message:sysMessage:remove")
	public R remove( String id){
		ApiResponse<Boolean> result = sysMessageReference.remove(id);
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
//	@RequiresPermissions("message:sysMessage:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		sysMessageReference.batchRemove(ids);
//		return R.ok();
//	}
	
}
