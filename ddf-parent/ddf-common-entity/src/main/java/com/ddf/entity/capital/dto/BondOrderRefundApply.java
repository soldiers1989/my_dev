package com.ddf.entity.capital.dto;

import com.ddf.entity.capital.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * bond_order_refund_apply Entity
 * @author robot
 * @version 2018-01-22
 */
@ApiModel(description = "BondOrderRefundApply")
public class BondOrderRefundApply extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户id")
	private String userId;
	@ApiModelProperty(value = "保证金ID")
	private String bondOrderId;
	@ApiModelProperty(value = "no")
	private String no;
	@ApiModelProperty(value = "状态")
	private BondOrderRefundApplyStatus status;
	
	public BondOrderRefundApply() {
		super();
	}

	public BondOrderRefundApply(String id){
		super(id);
	}

	public String getBondOrderId() {
		return bondOrderId;
	}

	public void setBondOrderId(String bondOrderId) {
		this.bondOrderId = bondOrderId;
	}
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public BondOrderRefundApplyStatus getStatus() {
		return status;
	}

	public void setStatus(BondOrderRefundApplyStatus status) {
		this.status = status;
	}
	
	
}