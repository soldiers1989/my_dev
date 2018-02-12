package com.ddf.rent.dao;

import org.apache.ibatis.annotations.Param;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.rent.query.ShareHouseQuery;
import com.ddf.entity.rent.vo.ShareHouseVo;
import com.ddf.entity.rent.dto.ShareHouse;

/**
 * share_house DAO接口
 * @author robot
 * @version 2018-02-02
 */
public interface ShareHouseDao extends CrudDao<ShareHouse,ShareHouseVo,ShareHouseQuery> {

	int remove4id(@Param(value="id")String id);
	
}