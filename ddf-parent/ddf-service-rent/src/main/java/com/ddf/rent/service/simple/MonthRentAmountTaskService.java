package com.ddf.rent.service.simple;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.github.pagehelper.PageHelper;
import com.ddf.entity.rent.dto.MonthRentAmountTask;
import com.ddf.entity.rent.query.MonthRentAmountTaskQuery;
import com.ddf.entity.rent.vo.MonthRentAmountTaskVo;
import com.ddf.rent.dao.MonthRentAmountTaskDao;

/**
 * month_rent_amount_task Service
 * @author robot
 * @version 2018-01-25
 */
@Service
public class MonthRentAmountTaskService extends CrudServiceImpl<MonthRentAmountTask,MonthRentAmountTaskVo,MonthRentAmountTaskQuery>{
	@Autowired
	private MonthRentAmountTaskDao monthRentAmountTaskDao;

	public Page<MonthRentAmountTaskVo> query4page(int pageNum, int pageSize){
		MonthRentAmountTaskQuery monthRentAmountTaskQuery = new MonthRentAmountTaskQuery();
		monthRentAmountTaskQuery.buildPageSql(pageNum, pageSize);
		List<MonthRentAmountTaskVo> list = this.findList(monthRentAmountTaskQuery);
		long totalCount = this.findCount(monthRentAmountTaskQuery);
		Page<MonthRentAmountTaskVo> page = new Page<MonthRentAmountTaskVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	

	public Page<MonthRentAmountTaskVo> query4pagehelper(int pageNum, int pageSize){
		MonthRentAmountTaskQuery monthRentAmountTaskQuery = new MonthRentAmountTaskQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<MonthRentAmountTaskVo> list = this.findList(monthRentAmountTaskQuery);
        Page<MonthRentAmountTaskVo> page = new Page<MonthRentAmountTaskVo>(list);
        return page;
	}

	/**
	 * 查出日期在当期之前的 status=wait 的所有任务
	 * @return
	 */
	public List<MonthRentAmountTaskVo> findListByDateAndStatus() {
		return monthRentAmountTaskDao.findListByDateAndStatus();
	}
}