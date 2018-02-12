package com.ddf.entity.message.dto;

import com.ddf.entity.message.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * sys_message Entity
 * @author robot
 * @version 2018-01-18
 */
@ApiModel(description = "SysMessage")
public class SysMessage extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "消息批次ID")
	private String sysMessageAdminId;
	@ApiModelProperty(value = "用户ID")
	private String userId;
	@ApiModelProperty(value = "业务类型")
	private String type;
	@ApiModelProperty(value = "图片")
	private String img;
	@ApiModelProperty(value = "标题")
	private String title;
	@ApiModelProperty(value = "内容")
	private String content;
	@ApiModelProperty(value = "消息链接")
	private String url;
	@ApiModelProperty(value = "枚举类型,UNREAD(未读),READ(已读)")
	private SysMessageReadStatus readStatus;
	
	public SysMessage() {
		super();
	}

	public SysMessage(String id){
		super(id);
	}

	public String getSysMessageAdminId() {
		return sysMessageAdminId;
	}

	public void setSysMessageAdminId(String sysMessageAdminId) {
		this.sysMessageAdminId = sysMessageAdminId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
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
	
	public SysMessageReadStatus getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(SysMessageReadStatus readStatus) {
		this.readStatus = readStatus;
	}
	
}