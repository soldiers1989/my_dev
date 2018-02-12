package com.ddf.message.quartz;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ddf.message.service.simple.MessageTaskService;

public class SysMessageAdminTaskJob {
	
	private Logger logger = Logger.getLogger(SysMessageAdminTaskJob.class);
	
	@Autowired
	private MessageTaskService messageTaskService;

	public void execute(){
		logger.info("========================消息发送job==================开始："+new Date());
		messageTaskService.sysMessageAdminTask();
		logger.info("========================消息发送job==================结束："+new Date());
	}
}
