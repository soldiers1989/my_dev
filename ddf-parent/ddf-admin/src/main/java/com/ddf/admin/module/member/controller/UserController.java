package com.ddf.admin.module.member.controller;

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
import com.ddf.entity.member.dto.User;
import com.ddf.entity.member.eo.AlipayZhimaCreditStatus;
import com.ddf.entity.member.eo.RealNameStatus;
import com.ddf.entity.member.eo.RealNameType;
import com.ddf.entity.member.eo.UserSex;
import com.ddf.entity.member.eo.UserStatus;
import com.ddf.entity.member.query.UserQuery;
import com.ddf.entity.member.vo.UserVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.reference.member.UserReference;
import com.ddf.util.ListUtil;

/**
 * 用户信息
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-03 14:27:03
 */
 
@Controller(value="UserController2")
@RequestMapping("/member/user")
public class UserController {
	@Autowired
	private UserReference userReference;
	
	@GetMapping()
	@RequiresPermissions("member:user:user")
	String user(Model model){
		model.addAttribute("UserSex", UserSex.values());
		model.addAttribute("AlipayZhimaCreditStatus", AlipayZhimaCreditStatus.values());
		model.addAttribute("RealNameType", RealNameType.values());
		model.addAttribute("RealNameStatus", RealNameStatus.values());
		model.addAttribute("UserStatus", UserStatus.values());
	    return "member/user/user";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("member:user:user")
	public PageUtils list(@ModelAttribute UserQuery userQuery){
		//查询列表数据
		ApiResponse<Page<UserVo>> result=userReference.batchqueryByUserQuery(userQuery);
		if( result != null && result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			Page<UserVo> page=result.getData();
			if(page!=null&&!ListUtil.isEmpty(page.getList())) {
				PageUtils pageUtils = new PageUtils(page.getList(), (int)page.getTotalCount());
				return pageUtils;
			}
		}
		return new PageUtils(new ArrayList<UserVo>(), 0);
	}
	
	
	@GetMapping("/add")
	@RequiresPermissions("member:user:add")
	String add(Model model){
		model.addAttribute("UserSex", UserSex.values());
		model.addAttribute("AlipayZhimaCreditStatus", AlipayZhimaCreditStatus.values());
		model.addAttribute("RealNameType", RealNameType.values());
		model.addAttribute("RealNameStatus", RealNameStatus.values());
		model.addAttribute("UserStatus", UserStatus.values());
	    return "member/user/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("member:user:edit")
	public String edit(@PathVariable("id") String id,Model model){
		ApiResponse<User> result = userReference.query(id);
		if(result!=null && result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			model.addAttribute("user", result.getData());
		}
		model.addAttribute("UserSex", UserSex.values());
		model.addAttribute("AlipayZhimaCreditStatus", AlipayZhimaCreditStatus.values());
		model.addAttribute("RealNameType", RealNameType.values());
		model.addAttribute("RealNameStatus", RealNameStatus.values());
		model.addAttribute("UserStatus", UserStatus.values());
	    return "member/user/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("member:user:add")
	public R save( User user){
		if(!user.getUserName().equals(user.getMobile())){
			return R.error(1,"用户名和手机号必须一致");
		}
		ApiResponse<Boolean> result = userReference.create(user);
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
	@RequiresPermissions("member:user:edit")
	public R update( User user){
		if(!user.getUserName().equals(user.getMobile())){
			return R.error("用户名和手机号必须一致");
		}
		ApiResponse<Boolean> result = userReference.modify(user);
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
	@RequiresPermissions("member:user:remove")
	public R remove( String id){
		ApiResponse<Boolean> result = userReference.remove(id);
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
//	@RequiresPermissions("member:user:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		userService.batchRemove(ids);
//		return R.ok();
//	}
	
}
