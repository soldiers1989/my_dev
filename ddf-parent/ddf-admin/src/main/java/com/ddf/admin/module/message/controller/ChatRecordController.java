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
 * 聊天记录
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 15:56:39
 */
 
@Controller
@RequestMapping("/message/chatRecord")
public class ChatRecordController {
//	@Autowired
//	private ChatRecordService chatRecordService;
	
	@GetMapping()
	@RequiresPermissions("message:chatRecord:chatRecord")
	String ChatRecord(){
	    return "message/chatRecord/chatRecord";
	}
	
//	@ResponseBody
//	@GetMapping("/list")
//	@RequiresPermissions("message:chatRecord:chatRecord")
//	public PageUtils list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//		List<ChatRecordDO> chatRecordList = chatRecordService.list(query);
//		int total = chatRecordService.count(query);
//		PageUtils pageUtils = new PageUtils(chatRecordList, total);
//		return pageUtils;
//	}
//	
//	@GetMapping("/add")
//	@RequiresPermissions("message:chatRecord:add")
//	String add(){
//	    return "message/chatRecord/add";
//	}
//
//	@GetMapping("/edit/{id}")
//	@RequiresPermissions("message:chatRecord:edit")
//	String edit(@PathVariable("id") String id,Model model){
//		ChatRecordDO chatRecord = chatRecordService.get(id);
//		model.addAttribute("chatRecord", chatRecord);
//	    return "message/chatRecord/edit";
//	}
//	
//	/**
//	 * 保存
//	 */
//	@ResponseBody
//	@PostMapping("/save")
//	@RequiresPermissions("message:chatRecord:add")
//	public R save( ChatRecordDO chatRecord){
//		if(chatRecordService.save(chatRecord)>0){
//			return R.ok();
//		}
//		return R.error();
//	}
//	/**
//	 * 修改
//	 */
//	@ResponseBody
//	@RequestMapping("/update")
//	@RequiresPermissions("message:chatRecord:edit")
//	public R update( ChatRecordDO chatRecord){
//		chatRecordService.update(chatRecord);
//		return R.ok();
//	}
//	
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/remove")
//	@ResponseBody
//	@RequiresPermissions("message:chatRecord:remove")
//	public R remove( String id){
//		if(chatRecordService.remove(id)>0){
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
//	@RequiresPermissions("message:chatRecord:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		chatRecordService.batchRemove(ids);
//		return R.ok();
//	}
	
}
