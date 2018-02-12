package com.ddf.entity.message.dto;

import com.ddf.entity.message.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * call_record Entity
 * @author robot
 * @version 2018-01-18
 */
@ApiModel(description = "CallRecord")
public class CallRecord extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "呼叫人id")
	private String srcUserId;
	@ApiModelProperty(value = "主叫号码")
	private String srcMobile;
	@ApiModelProperty(value = "被叫人ID")
	private String dstUserId;
	@ApiModelProperty(value = "被叫号码")
	private String dstMobile;
	@ApiModelProperty(value = " 业务类型")
	private String type;
	@ApiModelProperty(value = " 业务ID")
	private String dataId;
	
	public CallRecord() {
		super();
	}

	public CallRecord(String id){
		super(id);
	}

	public String getSrcUserId() {
		return srcUserId;
	}

	public void setSrcUserId(String srcUserId) {
		this.srcUserId = srcUserId;
	}
	
	public String getSrcMobile() {
		return srcMobile;
	}

	public void setSrcMobile(String srcMobile) {
		this.srcMobile = srcMobile;
	}
	
	public String getDstUserId() {
		return dstUserId;
	}

	public void setDstUserId(String dstUserId) {
		this.dstUserId = dstUserId;
	}
	
	public String getDstMobile() {
		return dstMobile;
	}

	public void setDstMobile(String dstMobile) {
		this.dstMobile = dstMobile;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	
}