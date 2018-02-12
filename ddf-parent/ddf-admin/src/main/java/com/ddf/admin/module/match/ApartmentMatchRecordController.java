package com.ddf.admin.module.match;

import com.ddf.admin.common.utils.PageUtils;
import com.ddf.admin.common.utils.R;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.dic.dto.Area;
import com.ddf.entity.member.dto.User;
import com.ddf.entity.rent.match.query.ApartmentMatchRecordQuery;
import com.ddf.entity.rent.match.vo.ApartmentMatchRecordVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.dic.AreaReference;
import com.ddf.reference.match.ApartmentMatchRecordReference;
import com.ddf.reference.member.UserReference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 匹配到的房源
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-04 14:43:17
 */
 
@Controller
@RequestMapping("/match/apartmentMatchRecord")
public class ApartmentMatchRecordController {
	@Autowired
	private ApartmentMatchRecordReference apartmentMatchRecordReference;
	@Autowired
	private AreaReference areaReference;
	@Autowired
	private UserReference userReference;
	@GetMapping()
	@RequiresPermissions("match:apartmentMatchRecord:apartmentMatchRecord")
	String ApartmentMatchRecord(){
	    return "match/apartmentMatchRecord/apartmentMatchRecord";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("match:apartmentMatchRecord:apartmentMatchRecord")
	public PageUtils list(@ModelAttribute ApartmentMatchRecordQuery query){
		//查询列表数据
		ApiResponse<Page<ApartmentMatchRecordVo>> response = apartmentMatchRecordReference.pagequery(query);
		List<ApartmentMatchRecordVo> list = new ArrayList<>();
		if (response !=null && response.getData() !=null){
			list = response.getData().getList();
			for (ApartmentMatchRecordVo recordVo : list) {
				String cityId = recordVo.getCityId();
				String districtId = recordVo.getDistrictId();
				String circleId = recordVo.getCircleId();
				String positionStr ="";
				ApiResponse<Area> query1 = areaReference.query(cityId);
				if (query1 !=null && query1.getData() !=null){
					positionStr = query1.getData().getName()+"-";
				}
				ApiResponse<Area> query2 = areaReference.query(districtId);
				if (query2 !=null && query2.getData() !=null){
					positionStr += query2.getData().getName()+"-";
				}
				ApiResponse<Area> query3 = areaReference.query(circleId);
				if (query3 !=null && query3.getData() !=null){
					positionStr += query3.getData().getName();
				}
				recordVo.setPosition(positionStr);
				recordVo.setHouseTitle(recordVo.getRentType() ==null?"":recordVo.getRentType().getExplain() +"-"+ recordVo.getXiaoquName() + "-"+ recordVo.getRoom()+"居室");
				ApiResponse<User> query4 = userReference.query(recordVo.getLandlordId());
				if (query4 !=null && query4.getData() !=null){
					recordVo.setLodger(query4.getData().getNickName() +"-"+ query4.getData().getMobile());
				}
			}
		}
		Page<ApartmentMatchRecordVo> data  = response.getData();
		PageUtils pageUtils = new PageUtils(list, (int) data.getTotalCount());
		return pageUtils;
	}
	

	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("match:apartmentMatchRecord:remove")
	public R remove( String id){
		if(apartmentMatchRecordReference.remove(id).getData()){
			return R.ok();
		}
		return R.error();
	}

	
}
