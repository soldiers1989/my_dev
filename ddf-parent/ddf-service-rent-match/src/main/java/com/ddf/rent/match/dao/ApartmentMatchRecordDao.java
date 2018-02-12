package com.ddf.rent.match.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.rent.match.query.ApartmentMatchRecordQuery;
import com.ddf.entity.rent.match.vo.ApartmentMatchRecordVo;
import com.ddf.entity.rent.match.dto.ApartmentMatchRecord;

/**
 * apartment_match_record DAO接口
 * @author robot
 * @version 2018-02-05
 */
public interface ApartmentMatchRecordDao extends CrudDao<ApartmentMatchRecord,ApartmentMatchRecordVo,ApartmentMatchRecordQuery> {
	
}