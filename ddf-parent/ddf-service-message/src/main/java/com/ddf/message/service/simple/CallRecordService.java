package com.ddf.message.service.simple;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.message.dto.CallRecord;
import com.ddf.entity.message.query.CallRecordQuery;
import com.ddf.entity.message.vo.CallRecordVo;
import com.ddf.message.dao.CallRecordDao;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * call_record Service
 * @author robot
 * @version 2018-01-15
 */
@Service
public class CallRecordService extends CrudServiceImpl<CallRecord,CallRecordVo,CallRecordQuery>{
	@Autowired
	private CallRecordDao callRecordDao;
	
	@Autowired
	private IdReference idReference;

	@Autowired
	private DateReference dateReference;
	
	public Page<CallRecordVo> query4page(int pageNum, int pageSize){
		CallRecordQuery callRecordQuery = new CallRecordQuery();
		callRecordQuery.buildPageSql(pageNum, pageSize);
		List<CallRecordVo> list = this.findList(callRecordQuery);
		long totalCount = this.findCount(callRecordQuery);
		Page<CallRecordVo> page = new Page<CallRecordVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	/**
	 * 根据条件查询  通话记录 列表
	 * @param callRecordQuery
	 * @return
	 */
	public Page<CallRecordVo> query4page(CallRecordQuery callRecordQuery){
		if(callRecordQuery==null){
			callRecordQuery = new CallRecordQuery();
		}
		if(StringUtil.isEmpty(callRecordQuery.getSortSql())){
			callRecordQuery.buildSortSql("order by a.create_date desc");
		}else{
			callRecordQuery.buildSortSql(callRecordQuery.getSortSql());
		}
		callRecordQuery.buildPageSql();
		List<CallRecordVo> list = this.findList(callRecordQuery);
		long totalCount = this.findCount(callRecordQuery);
		Page<CallRecordVo> page = new Page<CallRecordVo>(callRecordQuery.getPageNum(), callRecordQuery.getPageSize(), totalCount, list);
		return page;
	}
	

	public Page<CallRecordVo> query4pagehelper(int pageNum, int pageSize){
		CallRecordQuery callRecordQuery = new CallRecordQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<CallRecordVo> list = this.findList(callRecordQuery);
        Page<CallRecordVo> page = new Page<CallRecordVo>(list);
        return page;
	}
	
	
	/**
	 * 添加
	 */
	public boolean create(CallRecord callRecord){
		callRecord.setId(idReference.createId(TableName.call_record).getData());
		callRecord.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(callRecord);
	}
	
	/**
	 * 修改
	 */
	public boolean modify(CallRecord callRecord){
		callRecord.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(callRecord);
	}
	
}