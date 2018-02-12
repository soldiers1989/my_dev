package com.ddf.admin.module.match;

import com.ddf.admin.common.utils.PageUtils;
import com.ddf.admin.common.utils.R;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.member.dto.User;
import com.ddf.entity.rent.match.query.RentDemandMatchRecordQuery;
import com.ddf.entity.rent.match.vo.RentDemandMatchRecordVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.match.RentDemandMatchRecordReference;
import com.ddf.reference.member.UserReference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
*
 * 需求匹配到的租客
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-03 17:14:30
*/


@Controller
@RequestMapping("/match/rentLodgerMatchRecord")
public class RentLodgerMatchRecordController {

	@Autowired
	private RentDemandMatchRecordReference rentDemandMatchRecordReference;
	@Autowired
	private UserReference userReference;
	@GetMapping()
	@RequiresPermissions("match:rentLodgerMatchRecord:rentLodgerMatchRecord")
	String RentDemandMatchRecord(){
	    return "match/rentLodgerMatchRecord/rentLodgerMatchRecord";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("match:rentLodgerMatchRecord:rentLodgerMatchRecord")
	public PageUtils list(@ModelAttribute RentDemandMatchRecordQuery query){
		query.buildSortSql(" group by a.lodger_id ");
		//查询列表数据
		ApiResponse<Page<RentDemandMatchRecordVo>> response = rentDemandMatchRecordReference.pagequery(query);
		List<RentDemandMatchRecordVo> list = response.getData().getList();
		for (RentDemandMatchRecordVo recordVo : list) {
			String lodgerId = recordVo.getLodgerId();
			String demandId = recordVo.getRentDemandId();
			ApiResponse<User> query1 = userReference.query(lodgerId);
			if (query1 !=null && query1.getData() !=null)
			recordVo.setLodger(query1.getData().getNickName()+"-"+query1.getData().getMobile());

		}
		PageUtils pageUtils = new PageUtils(list, (int) response.getData().getTotalCount());
		return pageUtils;
	}
	
	/**
	 * 删除
	 * */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("match:rentLodgerMatchRecord:remove")
	public R remove( String id){
		if(rentDemandMatchRecordReference.remove(id).getData()){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除*/
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("match:rentLodgerMatchRecord:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		for (int i = 0; i < ids.length; i++) {
			if(rentDemandMatchRecordReference.remove(ids[i]).getData()){
				return R.ok();
			}
		}
		return R.error();
	}
	
}
