package com.ddf.rent.match.service.simple;
import java.util.List;

import com.ddf.entity.db.eo.TableName;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.github.pagehelper.PageHelper;
import com.ddf.entity.rent.match.dto.ApartmentMatchRecord;
import com.ddf.entity.rent.match.query.ApartmentMatchRecordQuery;
import com.ddf.entity.rent.match.vo.ApartmentMatchRecordVo;
import com.ddf.rent.match.dao.ApartmentMatchRecordDao;

/**
 * apartment_match_record Service
 * @author robot
 * @version 2018-02-05
 */
@Service
public class ApartmentMatchRecordService extends CrudServiceImpl<ApartmentMatchRecord,ApartmentMatchRecordVo,ApartmentMatchRecordQuery>{
	@Autowired
	private ApartmentMatchRecordDao apartmentMatchRecordDao;
	@Autowired
	private IdReference idReference;
	@Autowired
	private DateReference dateReference;
	public boolean create(ApartmentMatchRecord entity) {
		entity.setId(idReference.createId(TableName.house_match_record).getData());
		entity.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(entity);
	}
	public boolean modify(ApartmentMatchRecord entity) {
		entity.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(entity);
	}
	public Page<ApartmentMatchRecordVo> query4page(int pageNum, int pageSize){
		ApartmentMatchRecordQuery apartmentMatchRecordQuery = new ApartmentMatchRecordQuery();
		apartmentMatchRecordQuery.buildPageSql(pageNum, pageSize);
		List<ApartmentMatchRecordVo> list = this.findList(apartmentMatchRecordQuery);
		long totalCount = this.findCount(apartmentMatchRecordQuery);
		Page<ApartmentMatchRecordVo> page = new Page<ApartmentMatchRecordVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	public Page<ApartmentMatchRecordVo> query4page(ApartmentMatchRecordQuery query){
		query.buildPageSql();
		List<ApartmentMatchRecordVo> list = this.findList(query);
		long totalCount = this.findCount(query);
		Page<ApartmentMatchRecordVo> page = new Page<ApartmentMatchRecordVo>(query.getPageNum(), query.getPageSize(), totalCount, list);
		return page;
	}

	public Page<ApartmentMatchRecordVo> query4pagehelper(int pageNum, int pageSize){
		ApartmentMatchRecordQuery apartmentMatchRecordQuery = new ApartmentMatchRecordQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<ApartmentMatchRecordVo> list = this.findList(apartmentMatchRecordQuery);
        Page<ApartmentMatchRecordVo> page = new Page<ApartmentMatchRecordVo>(list);
        return page;
	}

	public Boolean hide4id(String id) {
		ApartmentMatchRecord entity = new ApartmentMatchRecord();
		entity.setId(id);
		entity.setHideFlag(false);
		return dao.modify(entity);
	}
}