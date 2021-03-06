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
import com.ddf.entity.message.dto.LodgerComplain;
import com.ddf.entity.message.query.LodgerComplainQuery;
import com.ddf.entity.message.vo.LodgerComplainVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.reference.member.UserReference;
import com.ddf.reference.message.LodgerComplainReference;
import com.ddf.util.ListUtil;

/**
 * 房东投诉房客
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 15:56:40
 */
 
@Controller
@RequestMapping("/message/lodgerComplain")
public class LodgerComplainController {
	@Autowired
	private LodgerComplainReference lodgerComplainReference;
	
	@Autowired
	private UserReference userReference;
	
	@GetMapping()
	@RequiresPermissions("message:lodgerComplain:lodgerComplain")
	String LodgerComplain(){
	    return "message/lodgerComplain/lodgerComplain";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("message:lodgerComplain:lodgerComplain")
	public PageUtils list(@ModelAttribute LodgerComplainQuery lodgerComplainQuery){
		//查询列表数据
		ApiResponse<Page<LodgerComplainVo>> result=lodgerComplainReference.batchquery(lodgerComplainQuery);
		if( result != null && result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			Page<LodgerComplainVo> page=result.getData();
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
		return new PageUtils(new ArrayList<LodgerComplainVo>(), 0);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("message:lodgerComplain:add")
	String add(){
	    return "message/lodgerComplain/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("message:lodgerComplain:edit")
	String edit(@PathVariable("id") String id,Model model){
		ApiResponse<LodgerComplain> result = lodgerComplainReference.query(id);
		if(result!=null && result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			model.addAttribute("lodgerComplain", result.getData());
		}
	    return "message/lodgerComplain/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("message:lodgerComplain:add")
	public R save( LodgerComplain lodgerComplain){
		ApiResponse<Boolean> result = lodgerComplainReference.create(lodgerComplain);
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
	@RequiresPermissions("message:lodgerComplain:edit")
	public R update( LodgerComplain lodgerComplain){
		ApiResponse<Boolean> result = lodgerComplainReference.modify(lodgerComplain);
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
	@RequiresPermissions("message:lodgerComplain:remove")
	public R remove( String id){
		ApiResponse<Boolean> result = lodgerComplainReference.remove(id);
		if(result!=null && result.getResult().equals(ApiResponseResult.SUCCESS.name()) && result.getData()==true){
			return R.ok();
		}else if(result!=null){
			return R.error(1,result.getMessage());
		}
		return R.error();
	}
	
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/batchRemove")
//	@ResponseBody
//	@RequiresPermissions("message:lodgerComplain:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		lodgerComplainReference.batchRemove(ids);
//		return R.ok();
//	}
//	
}
