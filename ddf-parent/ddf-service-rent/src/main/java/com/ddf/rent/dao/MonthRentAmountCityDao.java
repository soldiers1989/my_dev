package com.ddf.rent.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.rent.query.MonthRentAmountCityQuery;
import com.ddf.entity.rent.vo.MonthRentAmountCityVo;
import com.ddf.entity.rent.dto.MonthRentAmountCity;
import com.ddf.entity.rent.vo.MonthRentAmountVo;

import java.util.List;

/**
 * month_rent_amount_city DAO接口
 * @author robot
 * @version 2018-01-18
 */
public interface MonthRentAmountCityDao extends CrudDao<MonthRentAmountCity,MonthRentAmountCityVo,MonthRentAmountCityQuery> {

    List<MonthRentAmountCityVo> findListByDateAndStatus();

    boolean statsModify(MonthRentAmountVo city);

    boolean removeByDate(Long date);
}