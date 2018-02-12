package com.ddf.admin.module.message.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddf.admin.common.utils.PageUtils;
import com.ddf.admin.common.utils.Query;
import com.ddf.admin.common.utils.R;

/**
 * 手机短信
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 17:27:19
 */
 
@Controller
@RequestMapping("/message/mobileMessage")
public class MobileMessageController {
//	@Autowired
//	private MobileMessageReference mobileMessageReference;
	
	@GetMapping()
	@RequiresPermissions("message:mobileMessage:mobileMessage")
	String MobileMessage(){
	    return "message/mobileMessage/mobileMessage";
	}
	
//	@ResponseBody
//	@GetMapping("/list")
//	@RequiresPermissions("message:mobileMessage:mobileMessage")
//	public PageUtils list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//		List<MobileMessageDO> mobileMessageList = mobileMessageReference.list(query);
//		int total = mobileMessageReference.count(query);
//		PageUtils pageUtils = new PageUtils(mobileMessageList, total);
//		return pageUtils;
//	}
//	
//	@GetMapping("/add")
//	@RequiresPermissions("message:mobileMessage:add")
//	String add(){
//	    return "message/mobileMessage/add";
//	}
//
//	@GetMapping("/edit/{id}")
//	@RequiresPermissions("message:mobileMessage:edit")
//	String edit(@PathVariable("id") String id,Model model){
//		MobileMessageDO mobileMessage = mobileMessageReference.get(id);
//		model.addAttribute("mobileMessage", mobileMessage);
//	    return "message/mobileMessage/edit";
//	}
//	
//	/**
//	 * 保存
//	 */
//	@ResponseBody
//	@PostMapping("/save")
//	@RequiresPermissions("message:mobileMessage:add")
//	public R save( MobileMessageDO mobileMessage){
//		if(mobileMessageReference.save(mobileMessage)>0){
//			return R.ok();
//		}
//		return R.error();
//	}
//	/**
//	 * 修改
//	 */
//	@ResponseBody
//	@RequestMapping("/update")
//	@RequiresPermissions("message:mobileMessage:edit")
//	public R update( MobileMessageDO mobileMessage){
//		mobileMessageReference.update(mobileMessage);
//		return R.ok();
//	}
//	
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/remove")
//	@ResponseBody
//	@RequiresPermissions("message:mobileMessage:remove")
//	public R remove( String id){
//		if(mobileMessageReference.remove(id)>0){
//		return R.ok();
//		}
//		return R.error();
//	}
//	
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/batchRemove")
//	@ResponseBody
//	@RequiresPermissions("message:mobileMessage:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		mobileMessageReference.batchRemove(ids);
//		return R.ok();
//	}
	
}
