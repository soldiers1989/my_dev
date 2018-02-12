package com.ddf.entity.member.dto;

import com.ddf.entity.member.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * alipay_zhima_credit Entity
 * @author robot
 * @version 2018-01-31
 */
@ApiModel(description = "AlipayZhimaCredit")
public class AlipayZhimaCredit extends DataEntity {
	
	private static final long serialVersionUID = 1L;
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
	@ApiModelProperty(value = "芝麻信用分")
	private Long score;
	@ApiModelProperty(value = "枚举类型,芝麻信用状态 认证中、已驳回、已认证 对应芝麻信用表状态")
	private AlipayZhimaCreditStatus status;
	
	public AlipayZhimaCredit() {
		super();
	}

	public AlipayZhimaCredit(String id){
		super(id);
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
	
	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}
	
	public AlipayZhimaCreditStatus getStatus() {
		return status;
	}

	public void setStatus(AlipayZhimaCreditStatus status) {
		this.status = status;
	}
	
}