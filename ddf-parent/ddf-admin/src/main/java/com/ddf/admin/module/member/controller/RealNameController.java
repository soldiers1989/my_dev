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
import com.ddf.entity.member.dto.RealName;
import com.ddf.entity.member.dto.User;
import com.ddf.entity.member.eo.RealNameStatus;
import com.ddf.entity.member.eo.RealNameType;
import com.ddf.entity.member.query.RealNameQuery;
import com.ddf.entity.member.vo.RealNameVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.reference.member.RealNameReference;
import com.ddf.reference.member.UserReference;
import com.ddf.util.ListUtil;

/**
 * 实名认证
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-03 14:27:03
 */
 
@Controller
@RequestMapping("/member/realName")
public class RealNameController {
	@Autowired
	private RealNameReference realNameReference;
	
	@Autowired
	private UserReference userReference;
	
	@GetMapping()
	@RequiresPermissions("member:realName:realName")
	String RealName(Model model){
		model.addAttribute("RealNameType", RealNameType.values());
		model.addAttribute("RealNameStatus", RealNameStatus.values());
	    return "member/realName/realName";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("member:realName:realName")
	public PageUtils list(@ModelAttribute RealNameQuery realNameQuery){
		//查询列表数据
		ApiResponse<Page<RealNameVo>> result = realNameReference.batchqueryByRealNameQuery(realNameQuery);
		if( result != null && result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			Page<RealNameVo> page=result.getData();
			if(page!=null&&!ListUtil.isEmpty(page.getList())) {
				PageUtils pageUtils = new PageUtils(page.getList(), (int)page.getTotalCount());
				return pageUtils;
			}
		}
		return new PageUtils(new ArrayList<RealNameVo>(), 0);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("member:realName:add")
	String add(Model model){
		model.addAttribute("RealNameType", RealNameType.values());
		model.addAttribute("RealNameStatus", RealNameStatus.values());
	    return "member/realName/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("member:realName:edit")
	String edit(@PathVariable("id") String id,Model model){
		ApiResponse<RealName> result =  realNameReference.query(id);
		if(result!=null && result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			model.addAttribute("realName", result.getData());
		}
		model.addAttribute("RealNameType", RealNameType.values());
		model.addAttribute("RealNameStatus", RealNameStatus.values());
	    return "member/realName/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("member:realName:add")
	public R save( RealName realName){
		//判断用户ID是否存在
		ApiResponse<User> result_user = userReference.query(realName.getUserId());
		if(result_user!=null && result_user.getResult().equals(ApiResponseResult.SUCCESS.name())){
			User user = result_user.getData();
			if(user==null){
				return R.error("用户ID不存在，添加失败");
			}
		}else{
			return R.error(result_user.getMessage());
		}
		
		//判断用户是否有实名认证信息
		ApiResponse<RealNameVo> result_realNameVo = realNameReference.query4user(realName.getUserId());
		if(result_realNameVo!=null && result_realNameVo.getResult().equals(ApiResponseResult.SUCCESS.name())){
			RealNameVo realNameVo = result_realNameVo.getData();
			if(realNameVo!=null){
				return R.error("该用户实名认证信息已存在，添加失败");
			}
		}else{
			return R.error(result_realNameVo.getMessage());
		}
		
		// 添加
		ApiResponse<Boolean> result =  realNameReference.create(realName);
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
	@RequiresPermissions("member:realName:edit")
	public R update( RealName realName){
		//判断用户ID是否存在
		ApiResponse<User> result_user = userReference.query(realName.getUserId());
		if(result_user!=null && result_user.getResult().equals(ApiResponseResult.SUCCESS.name())){
			User user = result_user.getData();
			if(user==null){
				return R.error("用户ID不存在，添加失败");
			}
		}else{
			return R.error(result_user.getMessage());
		}
		
		//判断用户是否有实名认证信息
		ApiResponse<RealNameVo> result_realNameVo = realNameReference.query4user(realName.getUserId());
		if(result_realNameVo!=null && result_realNameVo.getResult().equals(ApiResponseResult.SUCCESS.name())){
			RealNameVo realNameVo = result_realNameVo.getData();
			if(realNameVo==null || realNameVo.getId()!=realName.getId()){
				return R.error("该用户实名认证信息已存在，修改失败");
			}
		}else{
			return R.error(result_realNameVo.getMessage());
		}
		
		//修改
		ApiResponse<Boolean> result =  realNameReference.modify(realName);
		if(result!=null && result.getResult().equals(ApiResponseResult.SUCCESS.name()) && result.getData()==true){
			return R.ok();
		}else if(result!=null){
			return R.error(result.getMessage());
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("member:realName:remove")
	public R remove( String id){
		ApiResponse<Boolean> result =  realNameReference.remove(id);
		if(result!=null && result.getResult().equals(ApiResponseResult.SUCCESS.name()) && result.getData()==true){
			return R.ok();
		}else if(result!=null){
			return R.error(result.getMessage());
		}
		return R.error();
	}
//	
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/batchRemove")
//	@ResponseBody
//	@RequiresPermissions("member:realName:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		realNameService.batchRemove(ids);
//		return R.ok();
//	}
//	
}
