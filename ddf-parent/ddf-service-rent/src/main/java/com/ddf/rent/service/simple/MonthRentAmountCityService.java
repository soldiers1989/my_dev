package com.ddf.rent.service.simple;
import java.util.Date;
import java.util.List;

import com.ddf.entity.dic.eo.AreaType;
import com.ddf.entity.dic.vo.AreaVo;
import com.ddf.entity.rent.eo.StatsStatus;
import com.ddf.entity.rent.query.MonthRentAmountXiaoquQuery;
import com.ddf.entity.rent.vo.MonthRentAmountVo;
import com.ddf.entity.rent.vo.MonthRentAmountXiaoquVo;
import com.ddf.entity.rent.vo.SpiderHouseVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.reference.dic.AreaReference;
import com.ddf.rent.service.task.StatsHouseAvgPriceTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.github.pagehelper.PageHelper;
import com.ddf.entity.rent.dto.MonthRentAmountCity;
import com.ddf.entity.rent.query.MonthRentAmountCityQuery;
import com.ddf.entity.rent.vo.MonthRentAmountCityVo;
import com.ddf.rent.dao.MonthRentAmountCityDao;

/**
 * month_rent_amount_city Service
 * @author robot
 * @version 2018-01-18
 */
@Service
public class MonthRentAmountCityService extends CrudServiceImpl<MonthRentAmountCity,MonthRentAmountCityVo,MonthRentAmountCityQuery>{
	@Autowired
	private MonthRentAmountCityDao monthRentAmountCityDao;
	@Autowired
	private SpiderHouseService spiderHouseService;
	@Autowired
	private StatsHouseAvgPriceTask statsHouseAvgPriceTask;
	@Autowired
	private AreaReference areaReference;
	@Autowired
	private IdReference idReference;
	@Autowired
	private DateReference dateReference;

	public Page<MonthRentAmountCityVo> query4page(int pageNum, int pageSize){
		MonthRentAmountCityQuery monthRentAmountCityQuery = new MonthRentAmountCityQuery();
		monthRentAmountCityQuery.buildPageSql(pageNum, pageSize);
		List<MonthRentAmountCityVo> list = this.findList(monthRentAmountCityQuery);
		long totalCount = this.findCount(monthRentAmountCityQuery);
		Page<MonthRentAmountCityVo> page = new Page<MonthRentAmountCityVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	

	public Page<MonthRentAmountCityVo> query4pagehelper(int pageNum, int pageSize){
		MonthRentAmountCityQuery monthRentAmountCityQuery = new MonthRentAmountCityQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<MonthRentAmountCityVo> list = this.findList(monthRentAmountCityQuery);
        Page<MonthRentAmountCityVo> page = new Page<MonthRentAmountCityVo>(list);
        return page;
	}
	public Page<MonthRentAmountCityVo> query4page(MonthRentAmountCityQuery query){
		if (query.getPageNum() == 0 || query.getPageSize() ==0){
			query.setPageNum(1);
			query.setPageSize(10);
		}
		query.buildPageSql();
		List<MonthRentAmountCityVo> list = this.findList(query);
		long totalCount = this.findCount(query);
		Page<MonthRentAmountCityVo> page = new Page<>(query.getPageNum(), query.getPageSize(), totalCount, list);
		return page;
	}
    public List<MonthRentAmountCityVo> findListByDateAndStatus() {
		return monthRentAmountCityDao.findListByDateAndStatus();
    }

	public boolean statsModify(MonthRentAmountVo city) {
		return  monthRentAmountCityDao.statsModify(city);
	}

	public void generateRentAmountByCity(Long date, Date bdate, Date edate) {
		monthRentAmountCityDao.removeByDate(date);
		//现在只生成 上海 cityId="d56c6f6450c94ed6b1b28b21a3982a1f"
		String cityId="d56c6f6450c94ed6b1b28b21a3982a1f";
		//得到当前系统时间
		Date currentDate = dateReference.queryCurrentDate().getData();
//		ApiResponse<List<AreaVo>> response = areaReference.queryChilds("02198862f68f46a09aaa126c3f1a4c79");
//		List<AreaVo> data = response.getData();
		MonthRentAmountCity city = new MonthRentAmountCity();
		city.setStatsDate(currentDate);
		city.setDate(date);
		city.setBdate(bdate);
		city.setEdate(edate);
		city.setStatus(StatsStatus.WAIT);
		city.setCityId(cityId);
		create(city);
	}

	/**
	 * 按城市统计
	 */
	public void statsRentAmountCityByMonth() {
		//得到当前系统时间
		Date currentDate = dateReference.queryCurrentDate().getData();
		List<MonthRentAmountCityVo> monthRentAmountCityVos = findListByDateAndStatus();
		for (MonthRentAmountCityVo rentAmountCityVo : monthRentAmountCityVos) {
			String id = rentAmountCityVo.getId();
			String cityId = rentAmountCityVo.getCityId();
			Date bDate = rentAmountCityVo.getBdate();//开始
			Date eDate = rentAmountCityVo.getEdate();//结束
			List<SpiderHouseVo> spiderHouseVos = spiderHouseService.statsListByCityAndGroupRoomAndDate(cityId, bDate,eDate);
			MonthRentAmountVo rentAmountVo = statsHouseAvgPriceTask.getMonthRentAmountVo(currentDate, id,spiderHouseVos);
			statsModify(rentAmountVo);
		}
	}

}