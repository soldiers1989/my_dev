package com.ddf.message.service.simple;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.member.vo.UserVo;
import com.ddf.entity.message.dto.MessageTask;
import com.ddf.entity.message.dto.SysMessage;
import com.ddf.entity.message.dto.SysMessageAdmin;
import com.ddf.entity.message.eo.MessageTaskBizType;
import com.ddf.entity.message.eo.MessageTaskStatus;
import com.ddf.entity.message.eo.SysMessageAdminSendType;
import com.ddf.entity.message.eo.SysMessageReadStatus;
import com.ddf.entity.message.query.MessageTaskQuery;
import com.ddf.entity.message.vo.MessageTaskVo;
import com.ddf.message.dao.MessageTaskDao;
import com.ddf.message.dao.SysMessageAdminDao;
import com.ddf.message.dao.SysMessageDao;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.reference.member.UserReference;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * message_task Service
 * @author robot
 * @version 2018-01-12
 */
@Service
public class MessageTaskService extends CrudServiceImpl<MessageTask,MessageTaskVo,MessageTaskQuery>{
	
	private static Logger logger = Logger.getLogger( MessageTaskService.class );
	
	@Autowired
	private MessageTaskDao messageTaskDao;
	
	@Autowired
	private SysMessageDao sysMessageDao;
	
	@Autowired
	private SysMessageAdminDao sysMessageAdminDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	@Autowired
	private UserReference userReference;
	
	public Page<MessageTaskVo> query4page(int pageNum, int pageSize){
		MessageTaskQuery messageTaskQuery = new MessageTaskQuery();
		messageTaskQuery.buildPageSql(pageNum, pageSize);
		List<MessageTaskVo> list = this.findList(messageTaskQuery);
		long totalCount = this.findCount(messageTaskQuery);
		Page<MessageTaskVo> page = new Page<MessageTaskVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	public Page<MessageTaskVo> query4page(MessageTaskQuery messageTaskQuery){
		if(messageTaskQuery==null){
			messageTaskQuery = new MessageTaskQuery();
		}
		if(StringUtil.isEmpty(messageTaskQuery.getSortSql())){
			messageTaskQuery.buildSortSql("order by a.create_date desc");
		}else{
			messageTaskQuery.buildSortSql(messageTaskQuery.getSortSql());
		}
		messageTaskQuery.buildPageSql();
		List<MessageTaskVo> list = this.findList(messageTaskQuery);
		long totalCount = this.findCount(messageTaskQuery);
		Page<MessageTaskVo> page = new Page<MessageTaskVo>(messageTaskQuery.getPageNum(), messageTaskQuery.getPageSize(), totalCount, list);
		return page;
	}
	

	public Page<MessageTaskVo> query4pagehelper(int pageNum, int pageSize){
		MessageTaskQuery messageTaskQuery = new MessageTaskQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<MessageTaskVo> list = this.findList(messageTaskQuery);
        Page<MessageTaskVo> page = new Page<MessageTaskVo>(list);
        return page;
	}
	
	/**
	 * 添加
	 */
	public boolean create(MessageTask messageTask){
		messageTask.setId(idReference.createId(TableName.message_task).getData());
		messageTask.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(messageTask);
	}
	
	/**
	 * 修改
	 */
	public boolean modify(MessageTask messageTask){
		messageTask.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(messageTask);
	}
	
	/**
	 * 消息任务job操作
	 */
	public void sysMessageAdminTask(){
		try{
			//获取一条 消息未处理的任务
			MessageTask messageTask = messageTaskDao.findByBizTypeLimit1(MessageTaskBizType.sys_message_admin);
			if(messageTask!=null){
				
				//标记该数据在处理中
				messageTask.setStatus(MessageTaskStatus.PROCESSING);
				messageTask.setUpdateDate(dateReference.queryCurrentDate().getData());
				messageTaskDao.modify(messageTask);
				
				//获取消息批次
				SysMessageAdmin sysMessageAdmin = sysMessageAdminDao.query4id(messageTask.getDataId());
				
				if(sysMessageAdmin!=null){
					try{
						int createNum = 0;//添加数量
						if(sysMessageAdmin.getSendType()==SysMessageAdminSendType.all){
							//-------------------------发送全部用户---------start
							int pageNum = 1;//页数
							int pageSize = 100;//每页数量
							//获取到用户列表分页数据
							Page<UserVo> pageUserVo = userReference.batchqueryByCreateDateAsc(pageNum, pageSize).getData();
							
							//判断是否有用户数据
							if(pageUserVo != null && pageUserVo.getSize()>0){
								long pages = pageUserVo.getPages();//获取到总页数
								for(pageNum =1 ; pageNum <= pages ; pageNum++){
									if(pageNum>1){
										//获取到用户列表分页数据
										pageUserVo = userReference.batchqueryByCreateDateAsc(pageNum, pageSize).getData();
										if(pageUserVo == null || pageUserVo.getSize()==0){
											break;
										}
									}
									List<UserVo> userVoList = pageUserVo.getList();//获取到用户集合
									List<SysMessage> smlist = new ArrayList<SysMessage>();
									Date date = dateReference.queryCurrentDate().getData();
									for(UserVo vo:userVoList){
										if(vo!=null && StringUtil.isNotEmpty(vo.getId())){
											SysMessage sm = getSysMessage(vo.getId(), date, sysMessageAdmin);
											smlist.add(sm);
										}
									}
									//批量发送消息
									createNum += sysMessageDao.createBatch(smlist);
								}
							}
							//----------------------发送全部用户--------------end
							
						}else{
							
							//-----------------------发送指定用户-----------start
							String userIds = sysMessageAdmin.getSendUserIds();
							if(StringUtil.isNotEmpty(userIds)){
								List<SysMessage> list = new ArrayList<SysMessage>();
								String[] userIdArr = userIds.split(",");
								Date date = dateReference.queryCurrentDate().getData();
								for(String userId:userIdArr){
									if(StringUtil.isNotEmpty(userId)){
										SysMessage sm = getSysMessage(userId, date, sysMessageAdmin);
										list.add(sm);
									}
								}
								//批量发送消息
								createNum = sysMessageDao.createBatch(list);
							}
							//-----------------------发送指定用户--------------end
							
						}
						//标记该数据已处理
						messageTask.setStatus(MessageTaskStatus.SUCCESS);
						messageTask.setRemark("消息已经发送成功，发送成功数量为："+createNum);
						messageTask.setUpdateDate(dateReference.queryCurrentDate().getData());
						messageTaskDao.modify(messageTask);
					}catch(Exception e2){
						logger.error("消息发送,报错:"+e2.getMessage(),e2);
						
						//标记该数据错误
						messageTask.setStatus(MessageTaskStatus.FAIL);
						messageTask.setRemark("消息发送报错,"+e2.getMessage());
						messageTask.setUpdateDate(dateReference.queryCurrentDate().getData());
						messageTaskDao.modify(messageTask);
					}
				}else{
					//标记该数据错误
					messageTask.setStatus(MessageTaskStatus.FAIL);
					messageTask.setRemark("找不到对应的数据");
					messageTask.setUpdateDate(dateReference.queryCurrentDate().getData());
					messageTaskDao.modify(messageTask);
				}
				
			}else{
				logger.info("===================暂时没有消息需要发送");
			}
		}catch(Exception e){
			 logger.error("消息发送,报错:"+e.getMessage(),e);
		}
	}
	
	/**
	 * 消息 转 用户消息 
	 * @param userId
	 * @param date
	 * @param sysMessageAdmin
	 * @return
	 */
	private SysMessage getSysMessage(String userId,Date date,SysMessageAdmin sysMessageAdmin){
		SysMessage sm = new SysMessage();
		sm.setId(idReference.createId(TableName.sys_message).getData());
		sm.setUserId(userId);
		sm.setSysMessageAdminId(sysMessageAdmin.getId());
		sm.setType(sysMessageAdmin.getType().name());
		sm.setImg(sysMessageAdmin.getImg());
		sm.setTitle(sysMessageAdmin.getTitle());
		sm.setContent(sysMessageAdmin.getContent());
		sm.setUrl(sysMessageAdmin.getUrl());
		sm.setReadStatus(SysMessageReadStatus.UNREAD);
		sm.setCreateDate(date);
		return sm;
	}
}