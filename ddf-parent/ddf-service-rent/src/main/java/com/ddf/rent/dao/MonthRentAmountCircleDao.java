package com.ddf.rent.dao;

import com.ddf.component.mybatis.api.CrudDao;

import com.ddf.entity.rent.query.MonthRentAmountCircleQuery;
import com.ddf.entity.rent.vo.MonthRentAmountCircleVo;
import com.ddf.entity.rent.dto.MonthRentAmountCircle;
import com.ddf.entity.rent.vo.MonthRentAmountVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * month_rent_amount_circle DAO接口
 * @author robot
 * @version 2018-01-18
 */
public interface MonthRentAmountCircleDao extends CrudDao<MonthRentAmountCircle,MonthRentAmountCircleVo,MonthRentAmountCircleQuery> {

    List<MonthRentAmountCircleVo> findListByDateAndStatus();

    boolean statsModify(MonthRentAmountVo city);

    boolean removeByDate(Long date);
    
    MonthRentAmountCircleVo find4thisMonth(@Param("circleId")String circleId);
}