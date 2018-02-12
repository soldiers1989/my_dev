package com.ddf.message.service.simple;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.message.dto.LandlordComplain;
import com.ddf.entity.message.query.LandlordComplainQuery;
import com.ddf.entity.message.vo.LandlordComplainVo;
import com.ddf.message.dao.LandlordComplainDao;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * landlord_complain Service
 * @author robot
 * @version 2018-01-15
 */
@Service
public class LandlordComplainService extends CrudServiceImpl<LandlordComplain,LandlordComplainVo,LandlordComplainQuery>{
	@Autowired
	private LandlordComplainDao landlordComplainDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	public Page<LandlordComplainVo> query4page(int pageNum, int pageSize){
		LandlordComplainQuery landlordComplainQuery = new LandlordComplainQuery();
		landlordComplainQuery.buildPageSql(pageNum, pageSize);
		List<LandlordComplainVo> list = this.findList(landlordComplainQuery);
		long totalCount = this.findCount(landlordComplainQuery);
		Page<LandlordComplainVo> page = new Page<LandlordComplainVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	/**
	 * 查询 房客举报房东 列表
	 * @param landlordComplainQuery
	 * @return
	 */
	public Page<LandlordComplainVo> query4page(LandlordComplainQuery landlordComplainQuery){
		if(landlordComplainQuery==null) {
			landlordComplainQuery = new LandlordComplainQuery();
		}
		if(StringUtil.isEmpty(landlordComplainQuery.getSortSql())){
			landlordComplainQuery.buildSortSql("order by a.create_date desc");
		}else{
			landlordComplainQuery.buildSortSql(landlordComplainQuery.getSortSql());
		}
		landlordComplainQuery.buildPageSql();
		List<LandlordComplainVo> list = this.findList(landlordComplainQuery);
		long totalCount = this.findCount(landlordComplainQuery);
		Page<LandlordComplainVo> page = new Page<LandlordComplainVo>(landlordComplainQuery.getPageNum(), landlordComplainQuery.getPageSize(), totalCount, list);
		return page;
	}
	
	

	public Page<LandlordComplainVo> query4pagehelper(int pageNum, int pageSize){
		LandlordComplainQuery landlordComplainQuery = new LandlordComplainQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<LandlordComplainVo> list = this.findList(landlordComplainQuery);
        Page<LandlordComplainVo> page = new Page<LandlordComplainVo>(list);
        return page;
	}
	
	/**
	 * 添加
	 */
	public boolean create(LandlordComplain landlordComplain){
		landlordComplain.setId(idReference.createId(TableName.landlord_complain).getData());
		landlordComplain.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(landlordComplain);
	}
	
	/**
	 * 修改
	 */
	public boolean modify(LandlordComplain landlordComplain){
		landlordComplain.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(landlordComplain);
	}
	
	/**
	 * 按房东查询 房客举报房东 列表
	 * @param landlordId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<LandlordComplainVo> query4landlordId(String landlordId,int pageNum, int pageSize){
		LandlordComplainQuery landlordComplainQuery = new LandlordComplainQuery();
		landlordComplainQuery.buildPageSql(pageNum, pageSize);
		landlordComplainQuery.buildSortSql("order by a.create_date desc");
		landlordComplainQuery.getLandlordComplain().setLandlordId(landlordId);
		List<LandlordComplainVo> list = this.findList(landlordComplainQuery);
		long totalCount = this.findCount(landlordComplainQuery);
		Page<LandlordComplainVo> page = new Page<LandlordComplainVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	
}