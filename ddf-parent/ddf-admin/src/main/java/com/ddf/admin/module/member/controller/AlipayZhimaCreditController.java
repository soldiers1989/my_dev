package com.ddf.admin.module.member.controller;

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
 * 芝麻信用
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-03 14:27:03
 */
 
@Controller
@RequestMapping("/member/alipayZhimaCredit")
public class AlipayZhimaCreditController {
//	@Autowired
//	private AlipayZhimaCreditService alipayZhimaCreditService;
	
	@GetMapping()
	@RequiresPermissions("member:alipayZhimaCredit:alipayZhimaCredit")
	String AlipayZhimaCredit(){
	    return "member/alipayZhimaCredit/alipayZhimaCredit";
	}
	
//	@ResponseBody
//	@GetMapping("/list")
//	@RequiresPermissions("member:alipayZhimaCredit:alipayZhimaCredit")
//	public PageUtils list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//		List<AlipayZhimaCreditDO> alipayZhimaCreditList = alipayZhimaCreditService.list(query);
//		int total = alipayZhimaCreditService.count(query);
//		PageUtils pageUtils = new PageUtils(alipayZhimaCreditList, total);
//		return pageUtils;
//	}
//	
//	@GetMapping("/add")
//	@RequiresPermissions("member:alipayZhimaCredit:add")
//	String add(){
//	    return "member/alipayZhimaCredit/add";
//	}
//
//	@GetMapping("/edit/{id}")
//	@RequiresPermissions("member:alipayZhimaCredit:edit")
//	String edit(@PathVariable("id") String id,Model model){
//		AlipayZhimaCreditDO alipayZhimaCredit = alipayZhimaCreditService.get(id);
//		model.addAttribute("alipayZhimaCredit", alipayZhimaCredit);
//	    return "member/alipayZhimaCredit/edit";
//	}
//	
//	/**
//	 * 保存
//	 */
//	@ResponseBody
//	@PostMapping("/save")
//	@RequiresPermissions("member:alipayZhimaCredit:add")
//	public R save( AlipayZhimaCreditDO alipayZhimaCredit){
//		if(alipayZhimaCreditService.save(alipayZhimaCredit)>0){
//			return R.ok();
//		}
//		return R.error();
//	}
//	/**
//	 * 修改
//	 */
//	@ResponseBody
//	@RequestMapping("/update")
//	@RequiresPermissions("member:alipayZhimaCredit:edit")
//	public R update( AlipayZhimaCreditDO alipayZhimaCredit){
//		alipayZhimaCreditService.update(alipayZhimaCredit);
//		return R.ok();
//	}
//	
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/remove")
//	@ResponseBody
//	@RequiresPermissions("member:alipayZhimaCredit:remove")
//	public R remove( String id){
//		if(alipayZhimaCreditService.remove(id)>0){
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
//	@RequiresPermissions("member:alipayZhimaCredit:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		alipayZhimaCreditService.batchRemove(ids);
//		return R.ok();
//	}
	
}
