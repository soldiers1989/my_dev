package com.ddf.dic.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.dic.dto.MetroStation;
import com.ddf.entity.dic.query.MetroStationQuery;
import com.ddf.entity.dic.vo.MetroStationVo;

/**
 * metro_station DAO接口
 * @author robot
 * @version 2018-01-08
 */
public interface MetroStationDao extends CrudDao<MetroStation,MetroStationVo,MetroStationQuery> {
	
}