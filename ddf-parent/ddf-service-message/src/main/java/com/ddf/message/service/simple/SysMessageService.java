package com.ddf.message.service.simple;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.message.dto.SysMessage;
import com.ddf.entity.message.eo.SysMessageReadStatus;
import com.ddf.entity.message.query.SysMessageQuery;
import com.ddf.entity.message.vo.SysMessageVo;
import com.ddf.message.dao.SysMessageDao;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * sys_message Service
 * @author robot
 * @version 2018-01-15
 */
@Service
public class SysMessageService extends CrudServiceImpl<SysMessage,SysMessageVo,SysMessageQuery>{
	@Autowired
	private SysMessageDao sysMessageDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	public Page<SysMessageVo> query4page(int pageNum, int pageSize){
		SysMessageQuery sysMessageQuery = new SysMessageQuery();
		sysMessageQuery.buildPageSql(pageNum, pageSize);
		List<SysMessageVo> list = this.findList(sysMessageQuery);
		long totalCount = this.findCount(sysMessageQuery);
		Page<SysMessageVo> page = new Page<SysMessageVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	public Page<SysMessageVo> query4page(SysMessageQuery sysMessageQuery){
		if(sysMessageQuery==null){
			sysMessageQuery = new SysMessageQuery();
		}
		if(StringUtil.isEmpty(sysMessageQuery.getSortSql())){
			sysMessageQuery.buildSortSql("order by a.create_date desc");
		}else{
			sysMessageQuery.buildSortSql(sysMessageQuery.getSortSql());
		}
		sysMessageQuery.buildPageSql();
		List<SysMessageVo> list = this.findList(sysMessageQuery);
		long totalCount = this.findCount(sysMessageQuery);
		Page<SysMessageVo> page = new Page<SysMessageVo>(sysMessageQuery.getPageNum(), sysMessageQuery.getPageSize(), totalCount, list);
		return page;
	}
	

	public Page<SysMessageVo> query4pagehelper(int pageNum, int pageSize){
		SysMessageQuery sysMessageQuery = new SysMessageQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<SysMessageVo> list = this.findList(sysMessageQuery);
        Page<SysMessageVo> page = new Page<SysMessageVo>(list);
        return page;
	}
	
	/**
	 * 添加
	 */
	public boolean create(SysMessage sysMessage){
		sysMessage.setId(idReference.createId(TableName.sys_message).getData());
		sysMessage.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(sysMessage);
	}
	
	/**
	 * 修改
	 */
	public boolean modify(SysMessage sysMessage){
		sysMessage.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(sysMessage);
	}
	
	/**
	 * 我的消息列表
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<SysMessageVo> query4userPage(String userId,int pageNum, int pageSize){
		SysMessageQuery sysMessageQuery = new SysMessageQuery();
		sysMessageQuery.getSysMessage().setUserId(userId);
		sysMessageQuery.buildPageSql(pageNum, pageSize);
		List<SysMessageVo> list = this.findList(sysMessageQuery);
		long totalCount = this.findCount(sysMessageQuery);
		Page<SysMessageVo> page = new Page<SysMessageVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	/**
	 * 我的消息总条数
	 * @param userId
	 * @return
	 */
	public long queryAllCount4user(String userId){
		SysMessageQuery sysMessageQuery = new SysMessageQuery();
		sysMessageQuery.getSysMessage().setUserId(userId);
		long totalCount = this.findCount(sysMessageQuery);
		return totalCount;
	}
	
	/**
	 * 我的消息读取状态总条数
	 * @param userId
	 * @return
	 */
	public long queryAllCount4user(String userId,SysMessageReadStatus readStatus){
		SysMessageQuery sysMessageQuery = new SysMessageQuery();
		sysMessageQuery.getSysMessage().setUserId(userId);
		sysMessageQuery.getSysMessage().setReadStatus(readStatus);
		long totalCount = this.findCount(sysMessageQuery);
		return totalCount;
	}
	
	/**
	 * 设置消息已读
	 * @param id
	 * @return
	 */
	public boolean setRead(String id){
		SysMessage sysMessage = new SysMessage();
		sysMessage.setId(id);
		sysMessage.setReadStatus(SysMessageReadStatus.READ);
		sysMessage.setUpdateDate(dateReference.queryCurrentDate().getData());
		return sysMessageDao.updateRead(sysMessage);
	}
	
}