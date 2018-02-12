package com.ddf.admin.module.capital.controller;

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
import com.ddf.admin.module.capital.service.BondOrderRefundApplyService;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-08 15:05:59
 */
 
@Controller
@RequestMapping("/capital/bondOrderRefundApply")
public class BondOrderRefundApplyController {
	@Autowired
	private BondOrderRefundApplyService bondOrderRefundApplyService;
	
	@GetMapping()
	@RequiresPermissions("capital:bondOrderRefundApply:bondOrderRefundApply")
	String BondOrderRefundApply(){
	    return "capital/bondOrderRefundApply/bondOrderRefundApply";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("capital:bondOrderRefundApply:bondOrderRefundApply")
	public PageUtils list(@RequestParam Map<String, Object> params){
		return null;
	}
	
	@GetMapping("/edit/{id}")
	@RequiresPermissions("capital:bondOrderRefundApply:edit")
	String edit(@PathVariable("id") String id,Model model){
		model.addAttribute("bondOrderRefundApply", null);
	    return "capital/bondOrderRefundApply/edit";
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("capital:bondOrderRefundApply:edit")
	public R update( ){
		return R.ok();
	}
	
}
