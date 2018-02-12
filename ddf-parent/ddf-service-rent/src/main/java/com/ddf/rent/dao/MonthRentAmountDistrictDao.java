package com.ddf.rent.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.rent.query.MonthRentAmountDistrictQuery;
import com.ddf.entity.rent.vo.MonthRentAmountDistrictVo;
import com.ddf.entity.rent.dto.MonthRentAmountDistrict;
import com.ddf.entity.rent.vo.MonthRentAmountVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * month_rent_amount_district DAO接口
 * @author robot
 * @version 2018-01-18
 */
public interface MonthRentAmountDistrictDao extends CrudDao<MonthRentAmountDistrict,MonthRentAmountDistrictVo,MonthRentAmountDistrictQuery> {

    List<MonthRentAmountDistrictVo> findListByDateAndStatus();

    boolean statsModify(MonthRentAmountVo district);

    boolean removeByDate(Long date);
    
    MonthRentAmountDistrictVo find4thisMonth(@Param("districtId")String districtId);
}