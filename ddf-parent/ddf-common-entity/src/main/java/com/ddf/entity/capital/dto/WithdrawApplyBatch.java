package com.ddf.entity.capital.dto;

import java.math.BigDecimal;
import com.ddf.entity.capital.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * withdraw_apply_batch Entity
 * @author robot
 * @version 2018-02-02
 */
@ApiModel(description = "WithdrawApplyBatch")
public class WithdrawApplyBatch extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "支付宝批次号")
	private String alipayBatchNo;
	@ApiModelProperty(value = "批次总金额")
	private BigDecimal amount;
	@ApiModelProperty(value = "批次名称")
	private String batchName;
	@ApiModelProperty(value = "批次状态枚举类型")
	private WithdrawApplyBatchStatus status;
	
	public WithdrawApplyBatch() {
		super();
	}

	public WithdrawApplyBatch(String id){
		super(id);
	}

	public String getAlipayBatchNo() {
		return alipayBatchNo;
	}

	public void setAlipayBatchNo(String alipayBatchNo) {
		this.alipayBatchNo = alipayBatchNo;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	
	public WithdrawApplyBatchStatus getStatus() {
		return status;
	}

	public void setStatus(WithdrawApplyBatchStatus status) {
		this.status = status;
	}
	
}