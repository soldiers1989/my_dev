package com.ddf.entity.rent.dto;

import java.math.BigDecimal;
import java.util.Date;
import com.ddf.entity.rent.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * apartment_deposit_contract Entity
 * @author robot
 * @version 2018-02-02
 */
@ApiModel(description = "ApartmentDepositContract")
public class ApartmentDepositContract extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "租金")
	private BigDecimal rentAmount;
	@ApiModelProperty(value = "定金")
	private BigDecimal depositAmout;
	@ApiModelProperty(value = "枚举类型，开启open，关闭close")
	private ApartmentDepositContractStatus status;
	@ApiModelProperty(value = "房间Id")
	private String apartmentId;
	@ApiModelProperty(value = "枚举类型，整租whole_rent，合租share_rent")
	private ApartmentDepositContractApartmentType apartmentType;
	@ApiModelProperty(value = "枚举类型，租房类型（日租date_rent，月租month_rent）")
	private ApartmentDepositContractRentType rentType;
	@ApiModelProperty(value = "枚举类型，付款方式")
	private ApartmentDepositContractPayType payType;
	@ApiModelProperty(value = "入住时间")
	private Date checkInDate;
	@ApiModelProperty(value = "退租时间")
	private Date checkOutDate;
	
	public ApartmentDepositContract() {
		super();
	}

	public ApartmentDepositContract(String id){
		super(id);
	}

	public BigDecimal getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(BigDecimal rentAmount) {
		this.rentAmount = rentAmount;
	}
	
	public BigDecimal getDepositAmout() {
		return depositAmout;
	}

	public void setDepositAmout(BigDecimal depositAmout) {
		this.depositAmout = depositAmout;
	}
	
	public ApartmentDepositContractStatus getStatus() {
		return status;
	}

	public void setStatus(ApartmentDepositContractStatus status) {
		this.status = status;
	}
	
	public String getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(String apartmentId) {
		this.apartmentId = apartmentId;
	}
	
	public ApartmentDepositContractApartmentType getApartmentType() {
		return apartmentType;
	}

	public void setApartmentType(ApartmentDepositContractApartmentType apartmentType) {
		this.apartmentType = apartmentType;
	}
	
	public ApartmentDepositContractRentType getRentType() {
		return rentType;
	}

	public void setRentType(ApartmentDepositContractRentType rentType) {
		this.rentType = rentType;
	}
	
	public ApartmentDepositContractPayType getPayType() {
		return payType;
	}

	public void setPayType(ApartmentDepositContractPayType payType) {
		this.payType = payType;
	}
	
	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	
	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	
}