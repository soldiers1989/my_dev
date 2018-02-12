package com.ddf.rent.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.rent.query.SpiderHouseQuery;
import com.ddf.entity.rent.vo.MonthRentAmountXiaoquVo;
import com.ddf.entity.rent.vo.SpiderHouseVo;
import com.ddf.entity.rent.dto.SpiderHouse;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * spider_house DAO接口
 * @author robot
 * @version 2018-01-19
 */
public interface SpiderHouseDao extends CrudDao<SpiderHouse,SpiderHouseVo,SpiderHouseQuery> {
    List<SpiderHouseVo> statsListByCityAndGroupRoomAndDate(@Param("cityId")  String cityId, @Param("bDate")  Date bDate, @Param("eDate")Date eDate);
    List<SpiderHouseVo> statsListByDistrictAndGroupRoomAndDate(@Param("districtId")  String districtId, @Param("bDate")  Date bDate, @Param("eDate")Date eDate);
    List<SpiderHouseVo> statsListByCircleAndGroupRoomAndDate(@Param("circleId")  String circleId, @Param("bDate")  Date bDate, @Param("eDate")Date eDate);
    List<SpiderHouseVo> statsListByXiaoquAndGroupRoomAndDate(@Param("xiaoquId")  String xiaoquId, @Param("bDate")  Date bDate, @Param("eDate")Date eDate);
}