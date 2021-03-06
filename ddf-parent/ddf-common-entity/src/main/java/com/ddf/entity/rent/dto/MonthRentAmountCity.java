package com.ddf.entity.rent.dto;

import java.util.Date;
import java.math.BigDecimal;
import com.ddf.entity.rent.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * month_rent_amount_city Entity
 * @author robot
 * @version 2018-01-26
 */
@ApiModel(description = "MonthRentAmountCity")
public class MonthRentAmountCity extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "city_id")
	private String cityId;
	@ApiModelProperty(value = "月数")
	private Long date;
	@ApiModelProperty(value = "统计时间")
	private Date statsDate;
	@ApiModelProperty(value = "房源新增数-personal_house表 count(*) create_date")
	private BigDecimal oneRoomAmount;
	@ApiModelProperty(value = "two_room_amount")
	private BigDecimal twoRoomAmount;
	@ApiModelProperty(value = "three_room_amount")
	private BigDecimal threeRoomAmount;
	@ApiModelProperty(value = "cal_one_room_amount")
	private BigDecimal calOneRoomAmount;
	@ApiModelProperty(value = "开始日期")
	private Date bdate;
	@ApiModelProperty(value = "截止日期")
	private Date edate;
	@ApiModelProperty(value = "状态 对应StatsStatus枚举类 WAIT(待处理),PROCESSING(处理中),SUCCESS(成功),FAIL(失败);")
	private StatsStatus status;
	
	public MonthRentAmountCity() {
		super();
	}

	public MonthRentAmountCity(String id){
		super(id);
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}
	
	public Date getStatsDate() {
		return statsDate;
	}

	public void setStatsDate(Date statsDate) {
		this.statsDate = statsDate;
	}
	
	public BigDecimal getOneRoomAmount() {
		return oneRoomAmount;
	}

	public void setOneRoomAmount(BigDecimal oneRoomAmount) {
		this.oneRoomAmount = oneRoomAmount;
	}
	
	public BigDecimal getTwoRoomAmount() {
		return twoRoomAmount;
	}

	public void setTwoRoomAmount(BigDecimal twoRoomAmount) {
		this.twoRoomAmount = twoRoomAmount;
	}
	
	public BigDecimal getThreeRoomAmount() {
		return threeRoomAmount;
	}

	public void setThreeRoomAmount(BigDecimal threeRoomAmount) {
		this.threeRoomAmount = threeRoomAmount;
	}
	
	public BigDecimal getCalOneRoomAmount() {
		return calOneRoomAmount;
	}

	public void setCalOneRoomAmount(BigDecimal calOneRoomAmount) {
		this.calOneRoomAmount = calOneRoomAmount;
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