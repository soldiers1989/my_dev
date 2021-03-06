package com.ddf.entity.message.query;

import java.util.Date;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.message.dto.MessageTask;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * message_task EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class MessageTaskQuery extends Query {

	private static final long serialVersionUID = 1L;

	public MessageTaskQuery(){
		this.messageTask = new MessageTask();
	}
	
	private MessageTask messageTask;

	public MessageTask getMessageTask() {
		return messageTask;
	}

	public void setMessageTask(MessageTask messageTask) {
		this.messageTask = messageTask;
	}

	private Date startCreateDate;
	private Date endCreateDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartCreateDate() {
		return startCreateDate;
	}

	public void setStartCreateDate(Date startCreateDate) {
		this.startCreateDate = startCreateDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

}