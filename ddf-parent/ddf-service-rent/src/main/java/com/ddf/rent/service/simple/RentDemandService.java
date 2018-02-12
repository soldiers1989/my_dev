package com.ddf.rent.service.simple;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.rent.dto.RentDemand;
import com.ddf.entity.rent.eo.RentDemandMatchStatus;
import com.ddf.entity.rent.query.RentDemandQuery;
import com.ddf.entity.rent.vo.RentDemandVo;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.rent.dao.RentDemandDao;

/**
 * rent_demand Service
 * 
 * @author robot
 * @version 2018-01-17
 */
@Service
public class RentDemandService extends CrudServiceImpl<RentDemand, RentDemandVo, RentDemandQuery> {
	@Autowired
	private RentDemandDao rentDemandDao;
	@Autowired
	private IdReference idReference;
	@Autowired
	private DateReference dateReference;

	/**
	 * 分页查询通用方法
	 * @return
	 */
	public Page<RentDemandVo> query4page(RentDemandQuery rentDemandQuery, int pageNum, int pageSize) {
		rentDemandQuery.buildPageSql(pageNum, pageSize);
		List<RentDemandVo> list = this.findList(rentDemandQuery);
		long totalCount = this.findCount(rentDemandQuery);
		Page<RentDemandVo> page = new Page<RentDemandVo>(pageNum, pageSize, totalCount, list);
		return page;
	}

	/**
	 * 伪删除
	 */
	public int remove4id(String id) {
		return rentDemandDao.remove4id(id);
	}


	/**
	 * 我的需求
	 * 
	 * @return
	 */
	public Page<RentDemandVo> pagequery4userId(RentDemandQuery rentDemandQuery) {
		int pageNum = rentDemandQuery.getPageNum();
		int pageSize = rentDemandQuery.getPageSize();
		rentDemandQuery.getRentDemand().setHideFlag(false);
		rentDemandQuery.buildSortSql("order by a.match_status desc , a.create_date desc");
		return this.query4page(rentDemandQuery, pageNum, pageSize);
	}

	/**
	 * 订阅/停阅
	 * 
	 * @return
	 */
	public Boolean matchStatus(String id, RentDemandMatchStatus matchStatus) {
		RentDemand rentDemand = rentDemandDao.query4id(id);
		rentDemand.setMatchStatus(matchStatus);
		return this.rentDemandDao.modify(rentDemand);
	}

	/**
	 * 需求列表
	 * 
	 * @return
	 */
	public Page<RentDemandVo> list(RentDemandQuery rentDemandQuery) {
		int pageNum = rentDemandQuery.getPageNum();
		int pageSize = rentDemandQuery.getPageSize();
		rentDemandQuery.buildSortSql("order by a.create_date desc");
		return this.query4page(rentDemandQuery, pageNum, pageSize);
	}


	/**
	 * 创建需求
	 */
	public boolean createRentDemand(RentDemand rentDemand) {
		// 设置默认参数
		rentDemand.setId(idReference.createId(TableName.rent_demand).getData());
		rentDemand.setMatchStatus(RentDemandMatchStatus.close);
		rentDemand.setHideFlag(false);
		Date tempDate = dateReference.queryCurrentDate().getData();
		rentDemand.setCreateDate(tempDate);
		rentDemand.setUpdateDate(tempDate);
		return rentDemandDao.create(rentDemand);
	}

	/**
	 * 修改需求
	 */
	public boolean modifyRentDemand(RentDemand rentDemand) {
		// 设置默认参数
		rentDemand.setUpdateDate(dateReference.queryCurrentDate().getData());
		RentDemand rentDemandTemp = rentDemandDao.query4id(rentDemand.getId());
		rentDemand.setLodgerId(rentDemandTemp.getLodgerId());
		rentDemand.setMatchStatus(rentDemandTemp.getMatchStatus());
		rentDemand.setHideFlag(rentDemandTemp.getHideFlag());
		rentDemand.setCreateDate(rentDemandTemp.getCreateDate());
		return rentDemandDao.create(rentDemand);
	}
}