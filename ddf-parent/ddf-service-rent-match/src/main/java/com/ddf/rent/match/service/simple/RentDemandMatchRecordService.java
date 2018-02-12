package com.ddf.rent.match.service.simple;
import java.util.List;

import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.dic.dto.Label;
import com.ddf.reference.cache.CacheReference;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.github.pagehelper.PageHelper;
import com.ddf.entity.rent.match.dto.RentDemandMatchRecord;
import com.ddf.entity.rent.match.query.RentDemandMatchRecordQuery;
import com.ddf.entity.rent.match.vo.RentDemandMatchRecordVo;
import com.ddf.rent.match.dao.RentDemandMatchRecordDao;

/**
 * rent_demand_match_record Service
 * @author robot
 * @version 2018-01-22
 */
@Service
public class RentDemandMatchRecordService extends CrudServiceImpl<RentDemandMatchRecord,RentDemandMatchRecordVo,RentDemandMatchRecordQuery>{

	@Autowired
	private RentDemandMatchRecordDao rentDemandMatchRecordDao;
	@Autowired
	private IdReference idReference;
	@Autowired
	private DateReference dateReference;
	@Autowired
	private CacheReference cacheReference;
	public boolean create(RentDemandMatchRecord entity) {
		entity.setId(idReference.createId(TableName.rent_demand_match_record).getData());
		entity.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(entity);
	}
	public boolean modify(RentDemandMatchRecord entity) {
		entity.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(entity);
	}
	
	public Page<RentDemandMatchRecordVo> query4page(int pageNum, int pageSize){
		RentDemandMatchRecordQuery rentDemandMatchRecordQuery = new RentDemandMatchRecordQuery();
		rentDemandMatchRecordQuery.buildPageSql(pageNum, pageSize);
		List<RentDemandMatchRecordVo> list = this.findList(rentDemandMatchRecordQuery);
		long totalCount = this.findCount(rentDemandMatchRecordQuery);
		Page<RentDemandMatchRecordVo> page = new Page<RentDemandMatchRecordVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	

	public Page<RentDemandMatchRecordVo> query4pagehelper(int pageNum, int pageSize){
		RentDemandMatchRecordQuery rentDemandMatchRecordQuery = new RentDemandMatchRecordQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<RentDemandMatchRecordVo> list = this.findList(rentDemandMatchRecordQuery);
        Page<RentDemandMatchRecordVo> page = new Page<RentDemandMatchRecordVo>(list);
        return page;
	}

	public Page<RentDemandMatchRecordVo> query4page(RentDemandMatchRecordQuery query) {
		query.buildPageSql();
		List<RentDemandMatchRecordVo> list = this.findList(query);
		long totalCount = this.findCount(query);
		Page<RentDemandMatchRecordVo> page = new Page<RentDemandMatchRecordVo>(query.getPageNum(), query.getPageSize(), totalCount, list);
		return page;
	}
	public Boolean hide4id(String id) {
		RentDemandMatchRecord record = new RentDemandMatchRecord();
		record.setId(id);
		record.setHideFlag(false);
		return modify(record);
	}

	public Page<RentDemandMatchRecordVo> query4houseId4groupbylodgerId(int pageNum, int pageSize, String houseId) {
		RentDemandMatchRecordQuery query = new RentDemandMatchRecordQuery();
		query.getRentDemandMatchRecord().setHouseId(houseId);
		query.buildPageSql(pageNum,pageSize);
		query.buildSortSql("order by a.create_date desc");
		List<RentDemandMatchRecordVo> list = rentDemandMatchRecordDao.findList4houseId4groupbylodgerId(query);
		return new Page<>(pageNum, pageSize, list.size(), list);
	}

	/**
	 * 通过 条件对象RentDemandMatchRecord 查询分页
	 * @param pageNum
	 * @param pageSize
	 * @param matchRecord
	 * @return
	 */
	private Page<RentDemandMatchRecordVo> getRentDemandMatchRecordVoPage(int pageNum, int pageSize, RentDemandMatchRecord matchRecord) {
		RentDemandMatchRecordQuery rentDemandMatchRecordQuery = new RentDemandMatchRecordQuery();
		matchRecord.setHideFlag(true);
		rentDemandMatchRecordQuery.setRentDemandMatchRecord(matchRecord);
		rentDemandMatchRecordQuery.buildPageSql(pageNum, pageSize);
		List<RentDemandMatchRecordVo> list = this.findList(rentDemandMatchRecordQuery);
		long totalCount = this.findCount(rentDemandMatchRecordQuery);
		return new Page<>(pageNum, pageSize, totalCount, list);
	}

	public Boolean hide4lodgerId(String lodgerId) {
		RentDemandMatchRecord record = new RentDemandMatchRecord();
		record.setLodgerId(lodgerId);
		record.setHideFlag(false);
		return modify(record);
	}

	public Boolean createRentDemandMatchRecord(RentDemandMatchRecord rentDemandMatchRecord) {
		rentDemandMatchRecord.setId(idReference.createId(TableName.rent_demand_match_record).getData());
		rentDemandMatchRecord.setCreateDate(dateReference.queryCurrentDate().getData());
		return create(rentDemandMatchRecord);
	}
}