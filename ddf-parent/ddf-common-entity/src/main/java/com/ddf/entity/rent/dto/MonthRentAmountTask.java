package com.ddf.entity.rent.dto;

import java.util.Date;
import com.ddf.entity.rent.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * month_rent_amount_task Entity
 * @author robot
 * @version 2018-01-25
 */
@ApiModel(description = "MonthRentAmountTask")
public class MonthRentAmountTask extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "月数")
	private Long date;
	@ApiModelProperty(value = "开始日期")
	private Date bdate;
	@ApiModelProperty(value = "截止日期")
	private Date edate;
	@ApiModelProperty(value = "状态 对应StatsStatus枚举类 WAIT(待处理),PROCESSING(处理中),SUCCESS(成功),FAIL(失败);")
	private StatsStatus status;
	
	public MonthRentAmountTask() {
		super();
	}

	public MonthRentAmountTask(String id){
		super(id);
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}
	
	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	
	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public StatsStatus getStatus() {
		return status;
	}

	public void setStatus(StatsStatus status) {
		this.status = status;
	}
}