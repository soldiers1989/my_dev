package com.ddf.message.service.simple;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.message.dto.LandlordOpinion;
import com.ddf.entity.message.query.LandlordOpinionQuery;
import com.ddf.entity.message.vo.LandlordOpinionVo;
import com.ddf.message.dao.LandlordOpinionDao;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * landlord_opinion Service
 * @author robot
 * @version 2018-01-15
 */
@Service
public class LandlordOpinionService extends CrudServiceImpl<LandlordOpinion,LandlordOpinionVo,LandlordOpinionQuery>{
	@Autowired
	private LandlordOpinionDao landlordOpinionDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	public Page<LandlordOpinionVo> query4page(int pageNum, int pageSize){
		LandlordOpinionQuery landlordOpinionQuery = new LandlordOpinionQuery();
		landlordOpinionQuery.buildPageSql(pageNum, pageSize);
		List<LandlordOpinionVo> list = this.findList(landlordOpinionQuery);
		long totalCount = this.findCount(landlordOpinionQuery);
		Page<LandlordOpinionVo> page = new Page<LandlordOpinionVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	/**
	 * 查询 评论房东 列表
	 * @param landlordOpinionQuery
	 * @return
	 */
	public Page<LandlordOpinionVo> query4page(LandlordOpinionQuery landlordOpinionQuery){
		if(landlordOpinionQuery==null){
			landlordOpinionQuery = new LandlordOpinionQuery();
		}
		if(StringUtil.isEmpty(landlordOpinionQuery.getSortSql())){
			landlordOpinionQuery.buildSortSql("order by a.create_date desc");
		}else{
			landlordOpinionQuery.buildSortSql(landlordOpinionQuery.getSortSql());
		}
		landlordOpinionQuery.buildPageSql();
		List<LandlordOpinionVo> list = this.findList(landlordOpinionQuery);
		long totalCount = this.findCount(landlordOpinionQuery);
		Page<LandlordOpinionVo> page = new Page<LandlordOpinionVo>(landlordOpinionQuery.getPageNum(), landlordOpinionQuery.getPageSize(), totalCount, list);
		return page;
	}
	

	public Page<LandlordOpinionVo> query4pagehelper(int pageNum, int pageSize){
		LandlordOpinionQuery landlordOpinionQuery = new LandlordOpinionQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<LandlordOpinionVo> list = this.findList(landlordOpinionQuery);
        Page<LandlordOpinionVo> page = new Page<LandlordOpinionVo>(list);
        return page;
	}
	
	/**
	 * 添加
	 */
	public boolean create(LandlordOpinion landlordOpinion){
		landlordOpinion.setId(idReference.createId(TableName.landlord_opinion).getData());
		landlordOpinion.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(landlordOpinion);
	}
	
	/**
	 * 修改
	 */
	public boolean modify(LandlordOpinion landlordOpinion){
		landlordOpinion.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(landlordOpinion);
	}
	
	/**
	 * 按房东查询 评论房东 列表
	 * @param landlordId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<LandlordOpinionVo> query4landlordId(String landlordId,int pageNum, int pageSize){
		LandlordOpinionQuery landlordOpinionQuery = new LandlordOpinionQuery();
		landlordOpinionQuery.buildPageSql(pageNum, pageSize);
		landlordOpinionQuery.buildSortSql("order by a.create_date desc");
		landlordOpinionQuery.getLandlordOpinion().setLandlordId(landlordId);
		List<LandlordOpinionVo> list = this.findList(landlordOpinionQuery);
		long totalCount = this.findCount(landlordOpinionQuery);
		Page<LandlordOpinionVo> page = new Page<LandlordOpinionVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	
}