package com.ddf.sms.service.simple;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.constant.RedisKeyConstant;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.sms.dto.SmsParam;
import com.ddf.entity.sms.query.SmsParamQuery;
import com.ddf.entity.sms.vo.SmsParamVo;
import com.ddf.reference.cache.CacheReference;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.sms.dao.SmsParamDao;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * sms_param Service
 * @author robot
 * @version 2018-01-30
 */
@Service
public class SmsParamService extends CrudServiceImpl<SmsParam,SmsParamVo,SmsParamQuery>{
	@Autowired
	private SmsParamDao smsParamDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	@Autowired
	private CacheReference cacheReference;
	
	public Page<SmsParamVo> query4page(int pageNum, int pageSize){
		SmsParamQuery smsParamQuery = new SmsParamQuery();
		smsParamQuery.buildPageSql(pageNum, pageSize);
		List<SmsParamVo> list = this.findList(smsParamQuery);
		long totalCount = this.findCount(smsParamQuery);
		Page<SmsParamVo> page = new Page<SmsParamVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	public Page<SmsParamVo> query4page(SmsParamQuery smsParamQuery){
		if(smsParamQuery==null){
			smsParamQuery = new SmsParamQuery();
		}
		if(StringUtil.isEmpty(smsParamQuery.getSortSql())){
			smsParamQuery.buildSortSql("order by a.create_date desc");
		}else{
			smsParamQuery.buildSortSql(smsParamQuery.getSortSql());
		}
		smsParamQuery.buildPageSql();
		List<SmsParamVo> list = this.findList(smsParamQuery);
		long totalCount = this.findCount(smsParamQuery);
		Page<SmsParamVo> page = new Page<SmsParamVo>(smsParamQuery.getPageNum(), smsParamQuery.getPageSize(), totalCount, list);
		return page;
	}
	

	public Page<SmsParamVo> query4pagehelper(int pageNum, int pageSize){
		SmsParamQuery smsParamQuery = new SmsParamQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<SmsParamVo> list = this.findList(smsParamQuery);
        Page<SmsParamVo> page = new Page<SmsParamVo>(list);
        return page;
	}
	
	/**
	 * 添加
	 */
	public boolean create(SmsParam smsParam){
		String id = idReference.createId(TableName.sms_param).getData();
		smsParam.setId(id);
		smsParam.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(smsParam);
	}
	
	/**
	 * 修改
	 */
	public boolean modify(SmsParam smsParam){
		smsParam.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(smsParam);
	}
	
	/**
	 * 根据参数名查询
	 * @param paramName
	 * @return
	 */
	public SmsParamVo queryByName(String paramName){
		SmsParamQuery smsParamQuery = new SmsParamQuery();
		smsParamQuery.getSmsParam().setParamName(paramName);
		return smsParamDao.query(smsParamQuery);
	}
	
	/**
	 * 根据 参数名称 返回 参数值
	 * @param paramName
	 * @return
	 */
	public String queryValueByName(String paramName){
		SmsParamVo smsParamVo = queryByName(paramName);
		if(smsParamVo!=null){
			return smsParamVo.getParamValue();
		}
		return null;
	}
	
}