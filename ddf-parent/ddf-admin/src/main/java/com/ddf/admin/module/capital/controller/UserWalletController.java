package com.ddf.admin.module.capital.controller;

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

import com.ddf.admin.common.utils.PageUtils;
import com.ddf.admin.common.utils.Query;
import com.ddf.admin.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 14:57:00
 */
 
@Controller
@RequestMapping("/capital/userWallet")
public class UserWalletController {
	
	@GetMapping()
	@RequiresPermissions("capital:userWallet:userWallet")
	String UserWallet(){
	    return "capital/userWallet/userWallet";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("capital:userWallet:userWallet")
	public PageUtils list(@RequestParam Map<String, Object> params){
		
		return null;
	}
	
}
