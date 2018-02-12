package com.ddf.entity.message.query;

import java.util.Date;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.message.dto.ChatRecord;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * chat_record EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class ChatRecordQuery extends Query {

	private static final long serialVersionUID = 1L;

	public ChatRecordQuery(){
		this.chatRecord = new ChatRecord();
	}
	
	private ChatRecord chatRecord;

	public ChatRecord getChatRecord() {
		return chatRecord;
	}

	public void setChatRecord(ChatRecord chatRecord) {
		this.chatRecord = chatRecord;
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