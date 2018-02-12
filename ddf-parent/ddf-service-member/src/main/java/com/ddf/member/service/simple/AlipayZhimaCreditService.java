package com.ddf.member.service.simple;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.member.dto.AlipayZhimaCredit;
import com.ddf.entity.member.query.AlipayZhimaCreditQuery;
import com.ddf.entity.member.vo.AlipayZhimaCreditVo;
import com.ddf.member.dao.AlipayZhimaCreditDao;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * alipay_zhima_credit Service
 * @author robot
 * @version 2018-01-15
 */
@Service
public class AlipayZhimaCreditService extends CrudServiceImpl<AlipayZhimaCredit,AlipayZhimaCreditVo,AlipayZhimaCreditQuery>{
	@Autowired
	private AlipayZhimaCreditDao alipayZhimaCreditDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	public Page<AlipayZhimaCreditVo> query4page(int pageNum, int pageSize){
		AlipayZhimaCreditQuery alipayZhimaCreditQuery = new AlipayZhimaCreditQuery();
		alipayZhimaCreditQuery.buildPageSql(pageNum, pageSize);
		List<AlipayZhimaCreditVo> list = this.findList(alipayZhimaCreditQuery);
		long totalCount = this.findCount(alipayZhimaCreditQuery);
		Page<AlipayZhimaCreditVo> page = new Page<AlipayZhimaCreditVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	public Page<AlipayZhimaCreditVo> query4page(AlipayZhimaCreditQuery alipayZhimaCreditQuery){
		if(alipayZhimaCreditQuery==null){
			alipayZhimaCreditQuery = new AlipayZhimaCreditQuery();
		}
		if(StringUtil.isEmpty(alipayZhimaCreditQuery.getSortSql())){
			alipayZhimaCreditQuery.buildSortSql("order by a.create_date desc");
		}else{
			alipayZhimaCreditQuery.buildSortSql(alipayZhimaCreditQuery.getSortSql());
		}
		alipayZhimaCreditQuery.buildPageSql(alipayZhimaCreditQuery.getPageNum(),alipayZhimaCreditQuery.getPageSize());
		List<AlipayZhimaCreditVo> list = this.findList(alipayZhimaCreditQuery);
		long totalCount = this.findCount(alipayZhimaCreditQuery);
		Page<AlipayZhimaCreditVo> page = new Page<AlipayZhimaCreditVo>(alipayZhimaCreditQuery.getPageNum(),alipayZhimaCreditQuery.getPageSize(), totalCount, list);
		return page;
	}
	

	public Page<AlipayZhimaCreditVo> query4pagehelper(int pageNum, int pageSize){
		AlipayZhimaCreditQuery alipayZhimaCreditQuery = new AlipayZhimaCreditQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<AlipayZhimaCreditVo> list = this.findList(alipayZhimaCreditQuery);
        Page<AlipayZhimaCreditVo> page = new Page<AlipayZhimaCreditVo>(list);
        return page;
	}
	
	/**
	 * 添加
	 */
	public boolean create(AlipayZhimaCredit alipayZhimaCredit){
		alipayZhimaCredit.setId(idReference.createId(TableName.alipay_zhima_credit).getData());
		alipayZhimaCredit.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(alipayZhimaCredit);
	}
	
	/**
	 * 修改
	 */
	public boolean modify(AlipayZhimaCredit alipayZhimaCredit){
		alipayZhimaCredit.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(alipayZhimaCredit);
	}
}