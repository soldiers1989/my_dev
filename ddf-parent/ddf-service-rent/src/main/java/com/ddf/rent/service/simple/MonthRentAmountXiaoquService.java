package com.ddf.rent.service.simple;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.dic.eo.AreaType;
import com.ddf.entity.dic.query.XiaoquQuery;
import com.ddf.entity.dic.vo.AreaVo;
import com.ddf.entity.dic.vo.XiaoquVo;
import com.ddf.entity.rent.dto.MonthRentAmountCircle;
import com.ddf.entity.rent.eo.StatsStatus;
import com.ddf.entity.rent.query.MonthRentAmountDistrictQuery;
import com.ddf.entity.rent.vo.MonthRentAmountCircleVo;
import com.ddf.entity.rent.vo.MonthRentAmountDistrictVo;
import com.ddf.entity.rent.vo.MonthRentAmountVo;
import com.ddf.entity.rent.vo.SpiderHouseVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.reference.dic.AreaReference;
import com.ddf.reference.dic.XiaoquReference;
import com.ddf.rent.service.task.StatsHouseAvgPriceTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.github.pagehelper.PageHelper;
import com.ddf.entity.rent.dto.MonthRentAmountXiaoqu;
import com.ddf.entity.rent.query.MonthRentAmountXiaoquQuery;
import com.ddf.entity.rent.vo.MonthRentAmountXiaoquVo;
import com.ddf.rent.dao.MonthRentAmountXiaoquDao;

/**
 * month_rent_amount_xiaoqu Service
 * @author robot
 * @version 2018-01-18
 */
@Service
public class MonthRentAmountXiaoquService extends CrudServiceImpl<MonthRentAmountXiaoqu,MonthRentAmountXiaoquVo,MonthRentAmountXiaoquQuery>{

	private final static int PageSize = 100;
	@Autowired
	private MonthRentAmountXiaoquDao monthRentAmountXiaoquDao;
	@Autowired
	private SpiderHouseService spiderHouseService;
	@Autowired
	private StatsHouseAvgPriceTask statsHouseAvgPriceTask;
	@Autowired
	private AreaReference areaReference;
	@Autowired
	private XiaoquReference xiaoquReference;
	@Autowired
	private IdReference idReference;
	@Autowired
	private DateReference dateReference;

	public Page<MonthRentAmountXiaoquVo> query4page(int pageNum, int pageSize){
		MonthRentAmountXiaoquQuery monthRentAmountXiaoquQuery = new MonthRentAmountXiaoquQuery();
		monthRentAmountXiaoquQuery.buildPageSql(pageNum, pageSize);
		List<MonthRentAmountXiaoquVo> list = this.findList(monthRentAmountXiaoquQuery);
		long totalCount = this.findCount(monthRentAmountXiaoquQuery);
		Page<MonthRentAmountXiaoquVo> page = new Page<MonthRentAmountXiaoquVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	

	public Page<MonthRentAmountXiaoquVo> query4pagehelper(int pageNum, int pageSize){
		MonthRentAmountXiaoquQuery monthRentAmountXiaoquQuery = new MonthRentAmountXiaoquQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<MonthRentAmountXiaoquVo> list = this.findList(monthRentAmountXiaoquQuery);
        Page<MonthRentAmountXiaoquVo> page = new Page<MonthRentAmountXiaoquVo>(list);
        return page;
	}
	public Page<MonthRentAmountXiaoquVo> query4page(MonthRentAmountXiaoquQuery query){
		if (query.getPageNum() == 0 || query.getPageSize() ==0){
			query.setPageNum(1);
			query.setPageSize(10);
		}
		query.buildPageSql();
		List<MonthRentAmountXiaoquVo> list = this.findList(query);
		long totalCount = this.findCount(query);
		Page<MonthRentAmountXiaoquVo> page = new Page<MonthRentAmountXiaoquVo>(query.getPageNum(), query.getPageSize(), totalCount, list);
		return page;
	}
    public boolean statsModify(MonthRentAmountVo circle) {
		return monthRentAmountXiaoquDao.statsModify(circle);
    }

	public List<MonthRentAmountXiaoquVo> findListByDateAndStatus(XiaoquQuery query) {

		return monthRentAmountXiaoquDao.findListByDateAndStatus(query);
	}

	public void generateRentAmountByXiaoqu(Long date, Date bdate, Date edate) {
		monthRentAmountXiaoquDao.removeByDate(date);
		//现在只生成 上海 cityId="d56c6f6450c94ed6b1b28b21a3982a1f"
		String cityId="d56c6f6450c94ed6b1b28b21a3982a1f";
		//得到当前系统时间
		Date currentDate = dateReference.queryCurrentDate().getData();
		Long xiaoquNum = xiaoquReference.queryXiaoquNum().getData();
		int PageNum = Integer.parseInt(xiaoquNum+"")/PageSize + 1;
		for (int i = 1; i < PageNum+1 ; i++) {
			XiaoquQuery query = new XiaoquQuery();
			query.getXiaoqu().setCityId(cityId);
			query.buildPageSql(i,PageSize);
			ApiResponse<Page<XiaoquVo>> response = xiaoquReference.pageQuery(query);
			List<MonthRentAmountXiaoqu> insertList = new ArrayList<>();
			for (XiaoquVo areaVo : response.getData().getList()) {
				MonthRentAmountXiaoqu xiaoqu = new MonthRentAmountXiaoqu();
				xiaoqu.setStatsDate(currentDate);
				xiaoqu.setDate(date);
				xiaoqu.setBdate(bdate);
				xiaoqu.setEdate(edate);
				xiaoqu.setStatus(StatsStatus.WAIT);
				xiaoqu.setXiaoquId(areaVo.getId());
				insertList.add(xiaoqu);
			}
			batchInsert(insertList);
		}
	}

	private Boolean batchInsert(List<MonthRentAmountXiaoqu> insertList) {
		return monthRentAmountXiaoquDao.batchInsert(insertList);
	}

	/**
	 * 按小区统计
	 */
	public void statsRentAmountXiaoquByMonth() {
		//得到当前系统时间
		Date currentDate = dateReference.queryCurrentDate().getData();
		Long xiaoquNum = xiaoquReference.queryXiaoquNum().getData();
		int PageNum = Integer.parseInt(xiaoquNum+"")/PageSize + 1;
		for (int i = 1; i < PageNum + 1 ; i++) {
			XiaoquQuery query = new XiaoquQuery();
			query.buildPageSql(i, PageSize);
			List<MonthRentAmountXiaoquVo> monthRentAmountXiaoquVos = findListByDateAndStatus(query);
			for (MonthRentAmountXiaoquVo circleVo : monthRentAmountXiaoquVos) {
				String id = circleVo.getId();
				String xiaoquId = circleVo.getXiaoquId();
				Date bdate = circleVo.getBdate();
				Date edate = circleVo.getEdate();
				List<SpiderHouseVo> spiderHouseVos = spiderHouseService.statsListByXiaoquAndGroupRoomAndDate(xiaoquId, bdate, edate);
				MonthRentAmountVo xiaoqu = statsHouseAvgPriceTask.getMonthRentAmountVo(currentDate, id, spiderHouseVos);
				statsModify(xiaoqu);
			}
		}

	}
	
	public MonthRentAmountXiaoquVo find4thisMonth(String xiaoquId) {
		return monthRentAmountXiaoquDao.find4thisMonth(xiaoquId);
	}
}