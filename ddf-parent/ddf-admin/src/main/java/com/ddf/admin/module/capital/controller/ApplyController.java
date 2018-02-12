package com.ddf.admin.module.capital.controller;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddf.admin.common.utils.PageUtils;
import com.ddf.admin.common.utils.R;
import com.ddf.admin.module.capital.service.ApplyService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.eo.WithdrawApplyStatus;
import com.ddf.entity.capital.query.WithdrawApplyQuery;
import com.ddf.entity.capital.vo.WithdrawApplyVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.reference.capital.WithdrawApplyReference;
import com.ddf.reference.common.DateReference;
import com.ddf.util.ListUtil;

/**
 * 提现申请
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-03 14:14:23
 */
 
@Controller
@RequestMapping("/capital/apply")
public class ApplyController {
	@Autowired
	private ApplyService applyService;
	@Autowired
	private WithdrawApplyReference withdrawApplyReference;
	@Autowired
	private DateReference dateReference;
	
	@GetMapping()
	@RequiresPermissions("capital:apply:apply")
	String Apply(){
	    return "capital/apply/apply";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("capital:apply:apply")
	public PageUtils list(@ModelAttribute WithdrawApplyQuery withdrawApplyQuery){
		//查询列表数据
		ApiResponse<Page<WithdrawApplyVo>> result=withdrawApplyReference.pagequery(withdrawApplyQuery);
		if(result!=null&&result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			Page<WithdrawApplyVo> page=result.getData();
			if(page!=null&&!ListUtil.isEmpty(page.getList())) {
				PageUtils pageUtils = new PageUtils(page.getList(), (int)page.getTotalCount());
				return pageUtils;
			}
		}
		return new PageUtils(new ArrayList<WithdrawApplyVo>(), 0);
	}
	
	@GetMapping("/review/{id}")
	@RequiresPermissions("capital:apply:review")
	String edit(@PathVariable("id") String id,Model model){
		WithdrawApplyVo withdrawApplyVo=withdrawApplyReference.query(id).getData();
		model.addAttribute("apply", withdrawApplyVo);
	    return "capital/apply/review";
	}
	
	@GetMapping("/review")
	@ResponseBody
	@RequiresPermissions("capital:apply:review")
	R review(@RequestParam("id")String id,@RequestParam("status")WithdrawApplyStatus status,Model model){
		WithdrawApplyVo withdrawApplyVo=withdrawApplyReference.query(id).getData();
		withdrawApplyVo.setStatus(status);
		withdrawApplyVo.setUpdateDate(dateReference.queryCurrentDate().getData());
		ApiResponse<Boolean> result=withdrawApplyReference.modify(withdrawApplyVo);
		if(result==null||result.getResult().equals(ApiResponseResult.ERROR.name())) {
			return R.error("系统异常");
		}else if(result.getResult().equals(ApiResponseResult.BUSS_ERROR.name())) {
			return R.error(result.getMessage());
		}else {
			return R.ok();
		}
	}
}
