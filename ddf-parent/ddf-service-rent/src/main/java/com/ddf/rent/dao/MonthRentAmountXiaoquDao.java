package com.ddf.rent.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.dic.query.XiaoquQuery;
import com.ddf.entity.rent.query.MonthRentAmountXiaoquQuery;
import com.ddf.entity.rent.vo.MonthRentAmountVo;
import com.ddf.entity.rent.vo.MonthRentAmountXiaoquVo;
import com.ddf.entity.rent.dto.MonthRentAmountXiaoqu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * month_rent_amount_xiaoqu DAO接口
 * @author robot
 * @version 2018-01-18
 */
public interface MonthRentAmountXiaoquDao extends CrudDao<MonthRentAmountXiaoqu,MonthRentAmountXiaoquVo,MonthRentAmountXiaoquQuery> {

    List<MonthRentAmountXiaoquVo> findListByDateAndStatus(XiaoquQuery query);

    boolean statsModify(MonthRentAmountVo city);

    boolean removeByDate(Long date);

    Boolean batchInsert(@Param("insertList") List<MonthRentAmountXiaoqu> insertList);
    
    MonthRentAmountXiaoquVo find4thisMonth(@Param("xiaoquId")String xiaoquId);
}