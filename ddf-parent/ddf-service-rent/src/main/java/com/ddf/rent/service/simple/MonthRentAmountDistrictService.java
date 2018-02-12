package com.ddf.rent.service.simple;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ddf.entity.dic.eo.AreaType;
import com.ddf.entity.dic.vo.AreaVo;
import com.ddf.entity.rent.eo.StatsStatus;
import com.ddf.entity.rent.vo.MonthRentAmountVo;
import com.ddf.entity.rent.vo.SpiderHouseVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.reference.dic.AreaReference;
import com.ddf.rent.service.task.StatsHouseAvgPriceTask;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.github.pagehelper.PageHelper;
import com.ddf.entity.rent.dto.MonthRentAmountDistrict;
import com.ddf.entity.rent.query.MonthRentAmountDistrictQuery;
import com.ddf.entity.rent.vo.MonthRentAmountDistrictVo;
import com.ddf.rent.dao.MonthRentAmountDistrictDao;

/**
 * month_rent_amount_district Service
 * @author robot
 * @version 2018-01-18
 */
@Service
public class MonthRentAmountDistrictService extends CrudServiceImpl<MonthRentAmountDistrict,MonthRentAmountDistrictVo,MonthRentAmountDistrictQuery>{
	@Autowired
	private MonthRentAmountDistrictDao monthRentAmountDistrictDao;
	@Autowired
	private StatsHouseAvgPriceTask statsHouseAvgPriceTask;
	@Autowired
	private SpiderHouseService spiderHouseService;
	@Autowired
	private AreaReference areaReference;
	@Autowired
	private IdReference idReference;
	@Autowired
	private DateReference dateReference;

	public Page<MonthRentAmountDistrictVo> query4page(int pageNum, int pageSize){
		MonthRentAmountDistrictQuery monthRentAmountDistrictQuery = new MonthRentAmountDistrictQuery();
		monthRentAmountDistrictQuery.buildPageSql(pageNum, pageSize);
		List<MonthRentAmountDistrictVo> list = this.findList(monthRentAmountDistrictQuery);
		long totalCount = this.findCount(monthRentAmountDistrictQuery);
		Page<MonthRentAmountDistrictVo> page = new Page<MonthRentAmountDistrictVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	

	public Page<MonthRentAmountDistrictVo> query4pagehelper(int pageNum, int pageSize){
		MonthRentAmountDistrictQuery monthRentAmountDistrictQuery = new MonthRentAmountDistrictQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<MonthRentAmountDistrictVo> list = this.findList(monthRentAmountDistrictQuery);
        Page<MonthRentAmountDistrictVo> page = new Page<MonthRentAmountDistrictVo>(list);
        return page;
	}
	public Page<MonthRentAmountDistrictVo> query4page(MonthRentAmountDistrictQuery query){
		if (query.getPageNum() == 0 || query.getPageSize() ==0){
			query.setPageNum(1);
			query.setPageSize(10);
		}
		query.buildPageSql();
		List<MonthRentAmountDistrictVo> list = this.findList(query);
		long totalCount = this.findCount(query);
		Page<MonthRentAmountDistrictVo> page = new Page<MonthRentAmountDistrictVo>(query.getPageNum(), query.getPageSize(), totalCount, list);
		return page;
	}
	public List<MonthRentAmountDistrictVo> findListByDateAndStatus() {

		return monthRentAmountDistrictDao.findListByDateAndStatus();
	}

	public boolean statsModify(MonthRentAmountVo district) {
		return monthRentAmountDistrictDao.statsModify(district);
	}

	public void generateRentAmountByDistrict(Long date, Date bdate, Date edate) {
		monthRentAmountDistrictDao.removeByDate(date);
		//得到当前系统时间
		Date currentDate = dateReference.queryCurrentDate().getData();
		ApiResponse<List<AreaVo>> response = areaReference.queryListByType(AreaType.DISTRICT);
		List<AreaVo> data = response.getData();
		List<MonthRentAmountDistrict> districtList = new ArrayList<>();
		for (AreaVo datum : data) {
			MonthRentAmountDistrict district = new MonthRentAmountDistrict();
			district.setStatsDate(currentDate);
			district.setDate(date);
			district.setBdate(bdate);
			district.setEdate(edate);
			district.setStatus(StatsStatus.WAIT);
			district.setDistrictId(datum.getId());
			districtList.add(district);
			create(district);
		}
//		batchCreate(districtList);
	}

	private void batchCreate(List<MonthRentAmountDistrict> districtList) {
//		monthRentAmountDistrictDao.batchCreate(districtList);
	}

	/**
	 * 按大区统计
	 */
	public void statsRentAmountDistrictByMonth() {
		//得到当前系统时间
		Date currentDate = dateReference.queryCurrentDate().getData();
		List<MonthRentAmountDistrictVo> monthRentAmountDistrictVos = findListByDateAndStatus();
		for (MonthRentAmountDistrictVo amountDistrictVo : monthRentAmountDistrictVos) {
			String id = amountDistrictVo.getId();
			String districtId = amountDistrictVo.getDistrictId();
			Date bdate = amountDistrictVo.getBdate();
			Date edate = amountDistrictVo.getEdate();
			List<SpiderHouseVo> spiderHouseVos = spiderHouseService.statsListByDistrictAndGroupRoomAndDate(districtId, bdate, edate);
			MonthRentAmountVo district = statsHouseAvgPriceTask.getMonthRentAmountVo(currentDate, id, spiderHouseVos);
			statsModify(district);
		}
	}
	
	public MonthRentAmountDistrictVo find4thisMonth(String districtId) {
		return monthRentAmountDistrictDao.find4thisMonth(districtId);
	}
}