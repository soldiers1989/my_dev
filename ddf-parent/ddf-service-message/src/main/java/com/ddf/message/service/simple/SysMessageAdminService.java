package com.ddf.message.service.simple;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.message.dto.MessageTask;
import com.ddf.entity.message.dto.SysMessageAdmin;
import com.ddf.entity.message.eo.MessageTaskBizType;
import com.ddf.entity.message.eo.MessageTaskStatus;
import com.ddf.entity.message.eo.SysMessageAdminStatus;
import com.ddf.entity.message.query.SysMessageAdminQuery;
import com.ddf.entity.message.vo.SysMessageAdminVo;
import com.ddf.message.dao.MessageTaskDao;
import com.ddf.message.dao.SysMessageAdminDao;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * sys_message_admin Service
 * @author robot
 * @version 2018-01-10
 */
@Service
public class SysMessageAdminService extends CrudServiceImpl<SysMessageAdmin,SysMessageAdminVo,SysMessageAdminQuery>{
	@Autowired
	private SysMessageAdminDao sysMessageAdminDao;
	
	@Autowired
	private MessageTaskDao messageTaskDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	public Page<SysMessageAdminVo> query4page(int pageNum, int pageSize){
		SysMessageAdminQuery sysMessageAdminQuery = new SysMessageAdminQuery();
		sysMessageAdminQuery.buildPageSql(pageNum, pageSize);
		List<SysMessageAdminVo> list = this.findList(sysMessageAdminQuery);
		long totalCount = this.findCount(sysMessageAdminQuery);
		Page<SysMessageAdminVo> page = new Page<SysMessageAdminVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	public Page<SysMessageAdminVo> query4page(SysMessageAdminQuery sysMessageAdminQuery){
		if(sysMessageAdminQuery==null){
			sysMessageAdminQuery = new SysMessageAdminQuery();
		}
		if(StringUtil.isEmpty(sysMessageAdminQuery.getSortSql())){
			sysMessageAdminQuery.buildSortSql("order by a.create_date desc");
		}else{
			sysMessageAdminQuery.buildSortSql(sysMessageAdminQuery.getSortSql());
		}
		sysMessageAdminQuery.buildPageSql();
		List<SysMessageAdminVo> list = this.findList(sysMessageAdminQuery);
		long totalCount = this.findCount(sysMessageAdminQuery);
		Page<SysMessageAdminVo> page = new Page<SysMessageAdminVo>(sysMessageAdminQuery.getPageNum(), sysMessageAdminQuery.getPageSize(), totalCount, list);
		return page;
	}
	

	public Page<SysMessageAdminVo> query4pagehelper(int pageNum, int pageSize){
		SysMessageAdminQuery sysMessageAdminQuery = new SysMessageAdminQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<SysMessageAdminVo> list = this.findList(sysMessageAdminQuery);
        Page<SysMessageAdminVo> page = new Page<SysMessageAdminVo>(list);
        return page;
	}
	
	/**
	 * 添加
	 */
	public boolean create(SysMessageAdmin sysMessageAdmin){
		String id = idReference.createId(TableName.sys_message_admin).getData();
		sysMessageAdmin.setId(id);
		sysMessageAdmin.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(sysMessageAdmin);
	}
	
	/**
	 * 修改
	 */
	public boolean modify(SysMessageAdmin sysMessageAdmin){
		sysMessageAdmin.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(sysMessageAdmin);
	}
	
	/**
	 * 审核
	 * @param id
	 * @param status
	 * @return
	 */
	public boolean reviewReject(String id,SysMessageAdminStatus status){
		if(StringUtil.isEmpty(id) || status == null) {
			return false;
		}
		SysMessageAdmin sysMessageAdmin = sysMessageAdminDao.query4id(id);
		if(sysMessageAdmin!=null){
			sysMessageAdmin.setStatus(status);
			sysMessageAdmin.setUpdateDate(dateReference.queryCurrentDate().getData());
			return sysMessageAdminDao.modify(sysMessageAdmin);
		}
		return false;
	}
	
	/**
	 * 发送
	 * @param id
	 * @return
	 */
	public boolean send(String id){
		SysMessageAdmin sysMessageAdmin = sysMessageAdminDao.query4id(id);
		
		if(sysMessageAdmin!=null){
			//Task添加任务
			MessageTask  messageTask = new MessageTask();
			messageTask.setId(idReference.createId(TableName.message_task).getData());
			messageTask.setBizType(MessageTaskBizType.sys_message_admin);
			messageTask.setCreateDate(dateReference.queryCurrentDate().getData());
			messageTask.setDataId(sysMessageAdmin.getId());
			messageTask.setStatus(MessageTaskStatus.WAIT);
			messageTaskDao.create(messageTask);
			
			//修改状态为 发送
			sysMessageAdmin.setStatus(SysMessageAdminStatus.sent);
			sysMessageAdminDao.modify(sysMessageAdmin);
			
			return true;
		}
		return false;
	}
}