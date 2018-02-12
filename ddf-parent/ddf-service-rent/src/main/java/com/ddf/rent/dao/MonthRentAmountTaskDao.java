package com.ddf.rent.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.rent.query.MonthRentAmountTaskQuery;
import com.ddf.entity.rent.vo.MonthRentAmountCircleVo;
import com.ddf.entity.rent.vo.MonthRentAmountTaskVo;
import com.ddf.entity.rent.dto.MonthRentAmountTask;

import java.util.List;

/**
 * month_rent_amount_task DAO接口
 * @author robot
 * @version 2018-01-25
 */
public interface MonthRentAmountTaskDao extends CrudDao<MonthRentAmountTask,MonthRentAmountTaskVo,MonthRentAmountTaskQuery> {
    List<MonthRentAmountTaskVo> findListByDateAndStatus();
}