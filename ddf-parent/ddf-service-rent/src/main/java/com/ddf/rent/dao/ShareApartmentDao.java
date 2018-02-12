package com.ddf.rent.dao;

import org.apache.ibatis.annotations.Param;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.rent.query.ShareApartmentQuery;
import com.ddf.entity.rent.vo.ShareApartmentVo;
import com.ddf.entity.rent.dto.ShareApartment;
import com.ddf.entity.rent.eo.ShareApartmentMatchStatus;

/**
 * share_apartment DAO接口
 * @author robot
 * @version 2018-02-02
 */
public interface ShareApartmentDao extends CrudDao<ShareApartment,ShareApartmentVo,ShareApartmentQuery> {

	int remove4id(@Param(value="id")String id);

	int remove4houseId(@Param(value="houseId")String houseId);

	ShareApartmentVo query4houseId4minAmout4openStatus(@Param(value="houseId")String houseId,@Param(value="matchStatus")ShareApartmentMatchStatus matchStatus);
	
}