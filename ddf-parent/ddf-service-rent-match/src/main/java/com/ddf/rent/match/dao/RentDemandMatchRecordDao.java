package com.ddf.rent.match.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.match.query.RentDemandMatchRecordQuery;
import com.ddf.entity.rent.match.vo.RentDemandMatchRecordVo;
import com.ddf.entity.rent.match.dto.RentDemandMatchRecord;

import java.util.List;

/**
 * rent_demand_match_record DAO接口
 * @author robot
 * @version 2018-01-22
 */
public interface RentDemandMatchRecordDao extends CrudDao<RentDemandMatchRecord,RentDemandMatchRecordVo,RentDemandMatchRecordQuery> {

    List<RentDemandMatchRecordVo> findList4houseId4groupbylodgerId(RentDemandMatchRecordQuery query);
}