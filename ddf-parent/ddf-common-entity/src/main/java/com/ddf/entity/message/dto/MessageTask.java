package com.ddf.entity.message.dto;

import com.ddf.entity.message.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * message_task Entity
 * @author robot
 * @version 2018-01-18
 */
@ApiModel(description = "MessageTask")
public class MessageTask extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "枚举类型,业务类型")
	private MessageTaskBizType bizType;
	@ApiModelProperty(value = "数据id")
	private String dataId;
	@ApiModelProperty(value = "枚举类型,状态(WAIT,PROCESSING,SUCCESS,FAIL)")
	private MessageTaskStatus status;
	
	public MessageTask() {
		super();
	}

	public MessageTask(String id){
		super(id);
	}

	public MessageTaskBizType getBizType() {
		return bizType;
	}

	public void setBizType(MessageTaskBizType bizType) {
		this.bizType = bizType;
	}
	
	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	
	public MessageTaskStatus getStatus() {
		return status;
	}

	public void setStatus(MessageTaskStatus status) {
		this.status = status;
	}
	
}