package com.ddf.rent.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.rent.query.ApartmentMarkQuery;
import com.ddf.entity.rent.vo.ApartmentMarkVo;
import com.ddf.entity.rent.dto.ApartmentMark;

/**
 * apartment_mark DAO接口
 * @author robot
 * @version 2018-02-02
 */
public interface ApartmentMarkDao extends CrudDao<ApartmentMark,ApartmentMarkVo,ApartmentMarkQuery> {
	
}