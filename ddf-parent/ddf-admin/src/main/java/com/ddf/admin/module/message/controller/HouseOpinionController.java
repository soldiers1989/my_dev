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
import com.ddf.entity.message.dto.HouseOpinion;
import com.ddf.entity.message.query.HouseOpinionQuery;
import com.ddf.entity.message.vo.HouseOpinionVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.reference.member.UserReference;
import com.ddf.reference.message.HouseOpinionReference;
import com.ddf.util.ListUtil;

/**
 * 房客评价房源
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 15:56:39
 */
 
@Controller
@RequestMapping("/message/houseOpinion")
public class HouseOpinionController {
	@Autowired
	private HouseOpinionReference houseOpinionReference;
	
	@Autowired
	private UserReference userReference;
	
//	@Autowired
//	private ApartmentReference apartmentReference;
	
	@GetMapping()
	@RequiresPermissions("message:houseOpinion:houseOpinion")
	String HouseOpinion(){
	    return "message/houseOpinion/houseOpinion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("message:houseOpinion:houseOpinion")
	public PageUtils list(@ModelAttribute HouseOpinionQuery houseOpinionQuery){
		//查询列表数据
		ApiResponse<Page<HouseOpinionVo>> result=houseOpinionReference.batchquery(houseOpinionQuery);
		if( result != null && result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			Page<HouseOpinionVo> page=result.getData();
			if(page!=null&&!ListUtil.isEmpty(page.getList())) {
				
				for( int i = 0 ; i < page.getList().size() ; i++ ){
					/*//房源信息
					ApiResponse<Apartment> result_apartment= apartmentReference.query(page.getList().get(i).getHouseId());
					if(result_apartment!=null && result_apartment.getResult().equals(ApiResponseResult.SUCCESS.name())){
						Apartment apartment = result_apartment.getData();
						page.getList().get(i).setApartment(apartment);
					}*/
					
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
		return new PageUtils(new ArrayList<HouseOpinionVo>(), 0);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("message:houseOpinion:add")
	String add(){
	    return "message/houseOpinion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("message:houseOpinion:edit")
	String edit(@PathVariable("id") String id,Model model){
		ApiResponse<HouseOpinion> result = houseOpinionReference.query(id);
		if(result!=null && result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			model.addAttribute("houseOpinion", result.getData());
		}
	    return "message/houseOpinion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("message:houseOpinion:add")
	public R save( HouseOpinion houseOpinion){
		ApiResponse<Boolean> result = houseOpinionReference.create(houseOpinion);
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
	@RequiresPermissions("message:houseOpinion:edit")
	public R update( HouseOpinion houseOpinion){
		ApiResponse<Boolean> result = houseOpinionReference.modify(houseOpinion);
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
	@RequiresPermissions("message:houseOpinion:remove")
	public R remove( String id){
		ApiResponse<Boolean> result = houseOpinionReference.remove(id);
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
//	@RequiresPermissions("message:houseOpinion:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		houseOpinionService.batchRemove(ids);
//		return R.ok();
//	}
	
}
