package com.ddf.rent.service.simple;
import java.util.Date;
import java.util.List;

import com.ddf.entity.rent.vo.MonthRentAmountXiaoquVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.github.pagehelper.PageHelper;
import com.ddf.entity.rent.dto.SpiderHouse;
import com.ddf.entity.rent.query.SpiderHouseQuery;
import com.ddf.entity.rent.vo.SpiderHouseVo;
import com.ddf.rent.dao.SpiderHouseDao;

/**
 * spider_house Service
 * @author robot
 * @version 2018-01-19
 */
@Service
public class SpiderHouseService extends CrudServiceImpl<SpiderHouse,SpiderHouseVo,SpiderHouseQuery>{
	@Autowired
	private SpiderHouseDao spiderHouseDao;
	
	public Page<SpiderHouseVo> query4page(int pageNum, int pageSize){
		SpiderHouseQuery spiderHouseQuery = new SpiderHouseQuery();
		spiderHouseQuery.buildPageSql(pageNum, pageSize);
		List<SpiderHouseVo> list = this.findList(spiderHouseQuery);
		long totalCount = this.findCount(spiderHouseQuery);
		Page<SpiderHouseVo> page = new Page<SpiderHouseVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	

	public Page<SpiderHouseVo> query4pagehelper(int pageNum, int pageSize){
		SpiderHouseQuery spiderHouseQuery = new SpiderHouseQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<SpiderHouseVo> list = this.findList(spiderHouseQuery);
        Page<SpiderHouseVo> page = new Page<SpiderHouseVo>(list);
        return page;
	}

	public List<SpiderHouseVo> statsListByCityAndGroupRoomAndDate(String cityId, Date bDate, Date eDate) {
		return spiderHouseDao.statsListByCityAndGroupRoomAndDate(cityId,bDate,eDate);
	}
	public List<SpiderHouseVo> statsListByDistrictAndGroupRoomAndDate(String districtId, Date bDate, Date eDate) {
		return spiderHouseDao.statsListByDistrictAndGroupRoomAndDate(districtId,bDate,eDate);
	}
	public List<SpiderHouseVo> statsListByCircleAndGroupRoomAndDate(String circleId, Date bDate, Date eDate) {
		return spiderHouseDao.statsListByCircleAndGroupRoomAndDate(circleId,bDate,eDate);
	}
	public List<SpiderHouseVo> statsListByXiaoquAndGroupRoomAndDate(String xiaoquId, Date bDate, Date eDate) {
		return spiderHouseDao.statsListByXiaoquAndGroupRoomAndDate(xiaoquId,bDate,eDate);
	}
}