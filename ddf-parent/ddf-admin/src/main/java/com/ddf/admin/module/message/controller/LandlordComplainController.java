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
import com.ddf.entity.member.dto.User;
import com.ddf.entity.message.dto.LandlordComplain;
import com.ddf.entity.message.query.LandlordComplainQuery;
import com.ddf.entity.message.vo.LandlordComplainVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.reference.member.UserReference;
import com.ddf.reference.message.LandlordComplainReference;
import com.ddf.util.ListUtil;

/**
 * 房客投诉房东
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 15:56:40
 */
 
@Controller
@RequestMapping("/message/landlordComplain")
public class LandlordComplainController {
	@Autowired
	private LandlordComplainReference landlordComplainReference;
	
	@Autowired
	private UserReference userReference;
	
	@GetMapping()
	@RequiresPermissions("message:landlordComplain:landlordComplain")
	String LandlordComplain(){
	    return "message/landlordComplain/landlordComplain";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("message:landlordComplain:landlordComplain")
	public PageUtils list(@ModelAttribute LandlordComplainQuery landlordComplainQuery){
		//查询列表数据
		ApiResponse<Page<LandlordComplainVo>> result=landlordComplainReference.batchquery(landlordComplainQuery);
		if( result != null && result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			Page<LandlordComplainVo> page=result.getData();
			if(page!=null&&!ListUtil.isEmpty(page.getList())) {
				for( int i = 0 ; i < page.getList().size() ; i++ ){
					//房东信息
					ApiResponse<User> result_user= userReference.query(page.getList().get(i).getLandlordId());
					if(result_user!=null && result_user.getResult().equals(ApiResponseResult.SUCCESS.name())){
						User landlordUser = result_user.getData();
						page.getList().get(i).setLandlordUser(landlordUser);
					}
					
					//房客信息
					ApiResponse<User> result_user2= userReference.query(page.getList().get(i).getLodgerId());
					if(result_user2!=null && result_user2.getResult().equals(ApiResponseResult.SUCCESS.name())){
						User lodgerUser = result_user2.getData();
						page.getList().get(i).setLodgerUser(lodgerUser);
					}
				}
				PageUtils pageUtils = new PageUtils(page.getList(), (int)page.getTotalCount());
				return pageUtils;
			}
		}
		return new PageUtils(new ArrayList<LandlordComplainVo>(), 0);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("message:landlordComplain:add")
	String add(){
	    return "message/landlordComplain/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("message:landlordComplain:edit")
	String edit(@PathVariable("id") String id,Model model){
		ApiResponse<LandlordComplain> result = landlordComplainReference.query(id);
		if(result!=null && result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			model.addAttribute("landlordComplain", result.getData());
		}
	    return "message/landlordComplain/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("message:landlordComplain:add")
	public R save( LandlordComplain landlordComplain){
		ApiResponse<Boolean> result = landlordComplainReference.create(landlordComplain);
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
	@RequiresPermissions("message:landlordComplain:edit")
	public R update( LandlordComplain landlordComplain){
		ApiResponse<Boolean> result = landlordComplainReference.modify(landlordComplain);
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
	@RequiresPermissions("message:landlordComplain:remove")
	public R remove( String id){
		ApiResponse<Boolean> result = landlordComplainReference.remove(id);
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
//	@RequiresPermissions("message:landlordComplain:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		landlordComplainReference.batchRemove(ids);
//		return R.ok();
//	}
	
}
