package com.ddf.message.service.simple;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.message.dto.LodgerOpinion;
import com.ddf.entity.message.query.LodgerOpinionQuery;
import com.ddf.entity.message.vo.LodgerOpinionVo;
import com.ddf.message.dao.LodgerOpinionDao;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * lodger_opinion Service
 * @author robot
 * @version 2018-01-15
 */
@Service
public class LodgerOpinionService extends CrudServiceImpl<LodgerOpinion,LodgerOpinionVo,LodgerOpinionQuery>{
	@Autowired
	private LodgerOpinionDao lodgerOpinionDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	public Page<LodgerOpinionVo> query4page(int pageNum, int pageSize){
		LodgerOpinionQuery lodgerOpinionQuery = new LodgerOpinionQuery();
		lodgerOpinionQuery.buildPageSql(pageNum, pageSize);
		List<LodgerOpinionVo> list = this.findList(lodgerOpinionQuery);
		long totalCount = this.findCount(lodgerOpinionQuery);
		Page<LodgerOpinionVo> page = new Page<LodgerOpinionVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	public Page<LodgerOpinionVo> query4pagehelper(int pageNum, int pageSize){
		LodgerOpinionQuery lodgerOpinionQuery = new LodgerOpinionQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<LodgerOpinionVo> list = this.findList(lodgerOpinionQuery);
        Page<LodgerOpinionVo> page = new Page<LodgerOpinionVo>(list);
        return page;
	}
	
	/**
	 * 添加
	 */
	public boolean create(LodgerOpinion lodgerOpinion){
		lodgerOpinion.setId(idReference.createId(TableName.lodger_opinion).getData());
		lodgerOpinion.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(lodgerOpinion);
	}
	
	/**
	 * 修改
	 */
	public boolean modify(LodgerOpinion lodgerOpinion){
		lodgerOpinion.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(lodgerOpinion);
	}
	
	
	/**
	 * 按房客查询 评论房客 列表
	 * @param lodgerId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<LodgerOpinionVo> query4lodgerId(String lodgerId,int pageNum, int pageSize){
		LodgerOpinionQuery lodgerOpinionQuery = new LodgerOpinionQuery();
		lodgerOpinionQuery.buildPageSql(pageNum, pageSize);
		lodgerOpinionQuery.getLodgerOpinion().setLodgerId(lodgerId);
		List<LodgerOpinionVo> list = this.findList(lodgerOpinionQuery);
		long totalCount = this.findCount(lodgerOpinionQuery);
		Page<LodgerOpinionVo> page = new Page<LodgerOpinionVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	
	/**
	 * 查询 评论房客 列表
	 * @param lodgerOpinionQuery
	 * @return
	 */
	public Page<LodgerOpinionVo> query4page(LodgerOpinionQuery lodgerOpinionQuery){
		if(lodgerOpinionQuery==null){
			lodgerOpinionQuery = new LodgerOpinionQuery();
		}
		if(StringUtil.isEmpty(lodgerOpinionQuery.getSortSql())){
			lodgerOpinionQuery.buildSortSql("order by a.create_date desc");
		}else{
			lodgerOpinionQuery.buildSortSql(lodgerOpinionQuery.getSortSql());
		}
		lodgerOpinionQuery.buildPageSql();
		List<LodgerOpinionVo> list = this.findList(lodgerOpinionQuery);
		long totalCount = this.findCount(lodgerOpinionQuery);
		Page<LodgerOpinionVo> page = new Page<LodgerOpinionVo>(lodgerOpinionQuery.getPageNum(), lodgerOpinionQuery.getPageSize(), totalCount, list);
		return page;
	}
}