package com.ddf.admin.module.capital.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddf.admin.common.utils.PageUtils;
import com.ddf.admin.common.utils.R;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.WithdrawApplyBatch;
import com.ddf.entity.capital.eo.WithdrawApplyStatus;
import com.ddf.entity.capital.query.WithdrawApplyBatchQuery;
import com.ddf.entity.capital.query.WithdrawApplyQuery;
import com.ddf.entity.capital.vo.WithdrawApplyBatchVo;
import com.ddf.entity.capital.vo.WithdrawApplyVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.reference.capital.WithdrawApplyBatchReference;
import com.ddf.reference.capital.WithdrawApplyReference;
import com.ddf.util.ListUtil;

/**
 * 提现申请批次
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-03 14:14:23
 */
 
@Controller
@RequestMapping("/capital/applyBatch")
public class ApplyBatchController {
	
	private Logger logger =LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WithdrawApplyBatchReference withdrawApplyBatchReference;
	@Autowired
	private WithdrawApplyReference withdrawApplyReference;
	
	@GetMapping()
	@RequiresPermissions("capital:applyBatch:applyBatch")
	String ApplyBatch(){
	    return "capital/applyBatch/applyBatch";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("capital:applyBatch:applyBatch")
	public PageUtils list(@ModelAttribute WithdrawApplyBatchQuery withdrawApplyBatchQuery){
		//查询列表数据
		ApiResponse<Page<WithdrawApplyBatchVo>> result=withdrawApplyBatchReference.pagequery(withdrawApplyBatchQuery);
		if(result!=null&&result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			Page<WithdrawApplyBatchVo> page=result.getData();
			if(page!=null&&!ListUtil.isEmpty(page.getList())) {
				PageUtils pageUtils = new PageUtils(page.getList(), (int)page.getTotalCount());
				return pageUtils;
			}
		}
		return new PageUtils(new ArrayList<WithdrawApplyBatchVo>(), 0);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("capital:applyBatch:add")
	String add(Model model){
	    return "capital/applyBatch/add";
	}
	
	@GetMapping("/add/data")
	@ResponseBody
	@RequiresPermissions("capital:applyBatch:add")
	PageUtils getAddData(Model model,@RequestParam(required=false)Date startDate,@RequestParam(required=false)Date endDate){
		ApiResponse<List<WithdrawApplyVo>> result=withdrawApplyReference.batchIdIsNull(startDate, endDate);
		if(result!=null&&result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			PageUtils pageUtils = new PageUtils(result.getData(), result.getData().size());
			return pageUtils;
		}
		return new PageUtils(new ArrayList<WithdrawApplyVo>(), 0);
	}
	
	
	
	@ResponseBody
	@GetMapping("/save")
	@RequiresPermissions("capital:applyBatch:save")
	public R save(HttpServletRequest request,@RequestParam("ids[]") String[] applyIds,@RequestParam("batchName")String batchName){
		if(applyIds == null || applyIds.length==0){
			return R.error("请选择提现申请");
		}else if(applyIds.length>100){
			return R.error("最多只能选100条");
		}
		ApiResponse<Boolean> result=withdrawApplyBatchReference.create(batchName, applyIds);
		if(result!=null&&result.getData()) {
			return R.ok("创建成功");
		}
		return R.error("系统异常");
	}
	
	@GetMapping("/query/{id}")
	@RequiresPermissions("capital:applyBatch:applyBatch")
	String getAddData(@PathVariable("id")String id,Model model){
		WithdrawApplyBatch withdrawApplyBatch=withdrawApplyBatchReference.query(id).getData();
		model.addAttribute("withdrawApplyBatch", withdrawApplyBatch);
		return "capital/applyBatch/transfer";
	}
	
	@GetMapping("/query/data")
	@ResponseBody
	@RequiresPermissions("capital:applyBatch:applyBatch")
	PageUtils queryData(Model model,@RequestParam(required=true)String batchId,@RequestParam(required=false)WithdrawApplyStatus status){
		WithdrawApplyQuery withdrawApplyQuery=new WithdrawApplyQuery();
		withdrawApplyQuery.getWithdrawApply().setBatchId(batchId);
		if(status!=null) {
			withdrawApplyQuery.getWithdrawApply().setStatus(status);
		}
		ApiResponse<Page<WithdrawApplyVo>> result=withdrawApplyReference.pagequery(withdrawApplyQuery);
		if(result!=null&&result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			PageUtils pageUtils = new PageUtils(result.getData().getList(), (int)result.getData().getTotalCount());
			return pageUtils;
		}
		return new PageUtils(new ArrayList<WithdrawApplyVo>(), 0);
	}
	
	@RequestMapping(value = "/transferCheck")
	@ResponseBody
	@RequiresPermissions("capital:applyBatch:transfer")
	public R transferCheck(HttpServletRequest request){
		String batchId = request.getParameter("batchId");
		ApiResponse<Boolean> result=withdrawApplyBatchReference.transferCheck(batchId);
		if(result==null||result.getResult().equals(ApiResponseResult.ERROR.name())) {
			return R.error("系统异常");
		}else if(result.getResult().equals(ApiResponseResult.BUSS_ERROR.name())) {
			return R.error(result.getMessage());
		}else {
			return R.ok();
		}
		
	}
	
	@RequestMapping(value = "/transfer")
	@RequiresPermissions("capital:applyBatch:transfer")
	public String transfer(HttpServletRequest request,HttpServletResponse response){
		String alipayBatchNo = request.getParameter("alipayBatchNo");
		String batchId = request.getParameter("batchId");
		try {
			ApiResponse<String> result = withdrawApplyBatchReference.transfer(alipayBatchNo,batchId);
			if(result!=null&&result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
				response.setContentType("text/html;charset=UTF-8");
			    response.setCharacterEncoding("UTF-8");
				response.getWriter().write(result.getData());
			}
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		} 
		return null;
	}
	
	@ResponseBody
	@RequiresPermissions("capital:applyBatch:delete")
	@RequestMapping(value = "/delete")
	public R delete(String id,HttpServletRequest request) {
		ApiResponse<Boolean> result=withdrawApplyBatchReference.remove(id);
		if(result==null||result.getResult().equals(ApiResponseResult.ERROR.name())) {
			return R.error("系统异常");
		}else if(result.getResult().equals(ApiResponseResult.BUSS_ERROR.name())) {
			return R.error(result.getMessage());
		}else {
			return R.ok();
		}
	}
	
	@RequiresPermissions("capital:applyBatch:transfer")
	@RequestMapping(value = "/activate")
	@ResponseBody
	public R activate(@RequestParam(required=true)String id,HttpServletRequest request) {
		ApiResponse<Boolean> result=withdrawApplyBatchReference.active(id);
		if(result==null||result.getResult().equals(ApiResponseResult.ERROR.name())) {
			return R.error("系统异常");
		}else if(result.getResult().equals(ApiResponseResult.BUSS_ERROR.name())) {
			return R.error(result.getMessage());
		}else {
			return R.ok();
		}
	}
	
	@GetMapping("/query/sum/user/{id}")
	@RequiresPermissions("capital:applyBatch:applyBatch")
	String query4sum4user(@PathVariable("id")String id,Model model){
		model.addAttribute("batchId", id);
		return "capital/applyBatch/sum";
	}
	
	@GetMapping("/query/sum/user/data")
	@ResponseBody
	@RequiresPermissions("capital:applyBatch:applyBatch")
	PageUtils query4sum4userData(Model model,@RequestParam(required=true)String batchId,@RequestParam(required=false)String mobile){
		ApiResponse<List<WithdrawApplyVo>> result=withdrawApplyReference.querySumUser(batchId, mobile);
		if(result!=null&&result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			PageUtils pageUtils = new PageUtils(result.getData(), result.getData().size());
			return pageUtils;
		}
		return new PageUtils(new ArrayList<WithdrawApplyVo>(), 0);
	}
	
}
