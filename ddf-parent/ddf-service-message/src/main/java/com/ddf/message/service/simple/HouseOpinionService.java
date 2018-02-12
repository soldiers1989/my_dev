package com.ddf.message.service.simple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.message.dto.HouseOpinion;
import com.ddf.entity.message.query.HouseOpinionQuery;
import com.ddf.entity.message.vo.HouseOpinionVo;
import com.ddf.message.dao.HouseOpinionDao;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * house_opinion Service
 * 
 * @author robot
 * @version 2018-01-15
 */
@Service
public class HouseOpinionService extends
		CrudServiceImpl<HouseOpinion, HouseOpinionVo, HouseOpinionQuery> {
	@Autowired
	private HouseOpinionDao houseOpinionDao;

	@Autowired
	private IdReference idReference;

	@Autowired
	private DateReference dateReference;

	public Page<HouseOpinionVo> query4page(int pageNum, int pageSize) {
		HouseOpinionQuery houseOpinionQuery = new HouseOpinionQuery();
		houseOpinionQuery.buildPageSql(pageNum, pageSize);
		List<HouseOpinionVo> list = this.findList(houseOpinionQuery);
		long totalCount = this.findCount(houseOpinionQuery);
		Page<HouseOpinionVo> page = new Page<HouseOpinionVo>(pageNum, pageSize,
				totalCount, list);
		return page;
	}
	
	/**
	 * 查询 房客评价房源 列表
	 * @param houseOpinionQuery
	 * @return
	 */
	public Page<HouseOpinionVo> query4page(HouseOpinionQuery houseOpinionQuery) {
		if(houseOpinionQuery==null){
			houseOpinionQuery = new HouseOpinionQuery();
		}
		if(StringUtil.isEmpty(houseOpinionQuery.getSortSql())){
			houseOpinionQuery.buildSortSql("order by a.create_date desc");
		}else{
			houseOpinionQuery.buildSortSql(houseOpinionQuery.getSortSql());
		}
		houseOpinionQuery.buildPageSql();
		List<HouseOpinionVo> list = this.findList(houseOpinionQuery);
		long totalCount = this.findCount(houseOpinionQuery);
		Page<HouseOpinionVo> page = new Page<HouseOpinionVo>(houseOpinionQuery.getPageNum(), houseOpinionQuery.getPageSize(),
				totalCount, list);
		return page;
	}

	public Page<HouseOpinionVo> query4pagehelper(int pageNum, int pageSize) {
		HouseOpinionQuery houseOpinionQuery = new HouseOpinionQuery();
		PageHelper.startPage(pageNum, pageSize);
		List<HouseOpinionVo> list = this.findList(houseOpinionQuery);
		Page<HouseOpinionVo> page = new Page<HouseOpinionVo>(list);
		return page;
	}

	/**
	 * 添加
	 */
	public boolean create(HouseOpinion houseOpinion) {
		houseOpinion.setId(idReference.createId(TableName.house_opinion)
				.getData());
		houseOpinion.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(houseOpinion);
	}

	/**
	 * 修改
	 */
	public boolean modify(HouseOpinion houseOpinion) {
		houseOpinion.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(houseOpinion);
	}

	/**
	 * 按房源查询 房客评价房源 列表
	 * 
	 * @param houseId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<HouseOpinionVo> query4houseId(String houseId, int pageNum,
			int pageSize) {
		HouseOpinionQuery houseOpinionQuery = new HouseOpinionQuery();
		houseOpinionQuery.buildPageSql(pageNum, pageSize);
		houseOpinionQuery.getHouseOpinion().setHouseId(houseId);
		List<HouseOpinionVo> list = this.findList(houseOpinionQuery);
		long totalCount = this.findCount(houseOpinionQuery);
		Page<HouseOpinionVo> page = new Page<HouseOpinionVo>(pageNum, pageSize,
				totalCount, list);
		return page;
	}
	
	
}