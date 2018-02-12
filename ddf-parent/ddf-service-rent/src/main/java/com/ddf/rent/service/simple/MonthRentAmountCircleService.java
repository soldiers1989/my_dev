package com.ddf.rent.service.simple;
import java.util.Date;
import java.util.List;

import com.ddf.entity.dic.eo.AreaType;
import com.ddf.entity.dic.vo.AreaVo;
import com.ddf.entity.rent.eo.StatsStatus;
import com.ddf.entity.rent.query.MonthRentAmountDistrictQuery;
import com.ddf.entity.rent.vo.MonthRentAmountDistrictVo;
import com.ddf.entity.rent.vo.MonthRentAmountVo;
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
import com.ddf.entity.rent.dto.MonthRentAmountCircle;
import com.ddf.entity.rent.query.MonthRentAmountCircleQuery;
import com.ddf.entity.rent.vo.MonthRentAmountCircleVo;
import com.ddf.rent.dao.MonthRentAmountCircleDao;

/**
 * month_rent_amount_circle Service
 * @author robot
 * @version 2018-01-18
 */
@Service
public class MonthRentAmountCircleService extends CrudServiceImpl<MonthRentAmountCircle,MonthRentAmountCircleVo,MonthRentAmountCircleQuery>{
	@Autowired
	private MonthRentAmountCircleDao monthRentAmountCircleDao;
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

	public Page<MonthRentAmountCircleVo> query4page(int pageNum, int pageSize){
		MonthRentAmountCircleQuery monthRentAmountCircleQuery = new MonthRentAmountCircleQuery();
		monthRentAmountCircleQuery.buildPageSql(pageNum, pageSize);
		List<MonthRentAmountCircleVo> list = this.findList(monthRentAmountCircleQuery);
		long totalCount = this.findCount(monthRentAmountCircleQuery);
		Page<MonthRentAmountCircleVo> page = new Page<MonthRentAmountCircleVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	

	public Page<MonthRentAmountCircleVo> query4pagehelper(int pageNum, int pageSize){
		MonthRentAmountCircleQuery monthRentAmountCircleQuery = new MonthRentAmountCircleQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<MonthRentAmountCircleVo> list = this.findList(monthRentAmountCircleQuery);
        Page<MonthRentAmountCircleVo> page = new Page<MonthRentAmountCircleVo>(list);
        return page;
	}
	public Page<MonthRentAmountCircleVo> query4page(MonthRentAmountCircleQuery query){
		if (query.getPageNum() == 0 || query.getPageSize() ==0){
			query.setPageNum(1);
			query.setPageSize(10);
		}
		query.buildPageSql();
		List<MonthRentAmountCircleVo> list = this.findList(query);
		long totalCount = this.findCount(query);
		Page<MonthRentAmountCircleVo> page = new Page<>(query.getPageNum(), query.getPageSize(), totalCount, list);
		return page;
	}
	public List<MonthRentAmountCircleVo> findListByDateAndStatus() {

		return monthRentAmountCircleDao.findListByDateAndStatus();
	}

	public boolean statsModify(MonthRentAmountVo circle) {
		return monthRentAmountCircleDao.statsModify(circle);
	}

	public void generateRentAmountByCircle(Long date, Date bdate, Date edate) {
		monthRentAmountCircleDao.removeByDate(date);
		//得到当前系统时间
		Date currentDate = dateReference.queryCurrentDate().getData();
		ApiResponse<List<AreaVo>> response = areaReference.queryListByType(AreaType.CIRCLE);
		List<AreaVo> data = response.getData();
		for (AreaVo areaVo : data) {
			MonthRentAmountCircle circle = new MonthRentAmountCircle();
			circle.setStatsDate(currentDate);
			circle.setDate(date);
			circle.setBdate(bdate);
			circle.setEdate(edate);
			circle.setStatus(StatsStatus.WAIT);
			circle.setCircleId(areaVo.getId());
			create(circle);
		}

	}

	/**
	 * 按商圈统计
	 */
	public void statsRentAmountCircleByMonth() {
		//得到当前系统时间
		Date currentDate = dateReference.queryCurrentDate().getData();
		List<MonthRentAmountCircleVo> monthRentAmountCircleVos = findListByDateAndStatus();
		for (MonthRentAmountCircleVo circleVo : monthRentAmountCircleVos) {
			String id = circleVo.getId();
			String circleId = circleVo.getCircleId();
			Date bdate = circleVo.getBdate();
			Date edate = circleVo.getEdate();
			List<SpiderHouseVo> spiderHouseVos = spiderHouseService.statsListByCircleAndGroupRoomAndDate(circleId, bdate, edate);
			MonthRentAmountVo circle = statsHouseAvgPriceTask.getMonthRentAmountVo(currentDate, id, spiderHouseVos);
			statsModify(circle);
		}
	}
	
	public MonthRentAmountCircleVo find4thisMonth(String circleId) {
		return monthRentAmountCircleDao.find4thisMonth(circleId);
	}
}