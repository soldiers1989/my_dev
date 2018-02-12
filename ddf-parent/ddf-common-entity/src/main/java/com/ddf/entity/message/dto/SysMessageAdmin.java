package com.ddf.entity.message.dto;

import com.ddf.entity.message.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * sys_message_admin Entity
 * @author robot
 * @version 2018-01-18
 */
@ApiModel(description = "SysMessageAdmin")
public class SysMessageAdmin extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "创建人ID")
	private String userId;
	@ApiModelProperty(value = "枚举类型,业务类型")
	private SysMessageAdminType type;
	@ApiModelProperty(value = "图片")
	private String img;
	@ApiModelProperty(value = "标题")
	private String title;
	@ApiModelProperty(value = "内容")
	private String content;
	@ApiModelProperty(value = "消息链接")
	private String url;
	@ApiModelProperty(value = "枚举类型,未审核，审核，已发送")
	private SysMessageAdminStatus status;
	@ApiModelProperty(value = "枚举类型,指定人，全部")
	private SysMessageAdminSendType sendType;
	@ApiModelProperty(value = "当为指定人时，为指定人的ID集合")
	private String sendUserIds;
	
	public SysMessageAdmin() {
		super();
	}

	public SysMessageAdmin(String id){
		super(id);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public SysMessageAdminType getType() {
		return type;
	}

	public void setType(SysMessageAdminType type) {
		this.type = type;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public SysMessageAdminStatus getStatus() {
		return status;
	}

	public void setStatus(SysMessageAdminStatus status) {
		this.status = status;
	}
	
	public SysMessageAdminSendType getSendType() {
		return sendType;
	}

	public void setSendType(SysMessageAdminSendType sendType) {
		this.sendType = sendType;
	}
	
	public String getSendUserIds() {
		return sendUserIds;
	}

	public void setSendUserIds(String sendUserIds) {
		this.sendUserIds = sendUserIds;
	}
	
}