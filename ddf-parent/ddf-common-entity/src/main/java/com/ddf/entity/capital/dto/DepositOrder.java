package com.ddf.entity.capital.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ddf.entity.base.dto.DataEntity;
import com.ddf.entity.capital.eo.DepositOrderStatus;
import com.ddf.entity.rent.eo.ApartmentDepositContractPayType;
import com.ddf.entity.rent.eo.ApartmentDepositContractRentType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * deposit_order Entity
 * @author robot
 * @version 2018-01-22
 */
@ApiModel(description = "DepositOrder")
public class DepositOrder extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "房东id")
	private String landlordId;
	@ApiModelProperty(value = "房东姓名")
	private String landlordName;
	@ApiModelProperty(value = "房东手机号")
	private String landlordMobile;
	@ApiModelProperty(value = "房客id")
	private String lodgerId;
	@ApiModelProperty(value = "租客姓名")
	private String lodgerName;
	@ApiModelProperty(value = "租客手机号")
	private String lodgerMobile;
	@ApiModelProperty(value = "house_deposit_contract_id")
	private String houseDepositContractId;
	@ApiModelProperty(value = "house_id")
	private String houseId;
	@ApiModelProperty(value = "no")
	private String no;
	@ApiModelProperty(value = "订单金额")
	private BigDecimal amount;
	@ApiModelProperty(value = "支付宝订单号")
	private String alipayTradeNo;
	@ApiModelProperty(value = "枚举类型")
	private DepositOrderStatus status;
	@ApiModelProperty(value = "入住时间")
	private Date checkInDate;
	@ApiModelProperty(value = "退租时间")
	private Date checkOutDate;
	@ApiModelProperty(value = "租房类型")
	private ApartmentDepositContractRentType rentType;
	@ApiModelProperty(value = "租金")
	private BigDecimal rentAmount;
	@ApiModelProperty(value = "支付方式")
	private ApartmentDepositContractPayType payType;
	
	public DepositOrder() {
		super();
	}

	public DepositOrder(String id){
		super(id);
	}

	public String getLandlordId() {
		return landlordId;
	}

	public void setLandlordId(String landlordId) {
		this.landlordId = landlordId;
	}
	
	public String getLandlordName() {
		return landlordName;
	}

	public void setLandlordName(String landlordName) {
		this.landlordName = landlordName;
	}
	
	public String getLandlordMobile() {
		return landlordMobile;
	}

	public void setLandlordMobile(String landlordMobile) {
		this.landlordMobile = landlordMobile;
	}
	
	public String getLodgerId() {
		return lodgerId;
	}

	public void setLodgerId(String lodgerId) {
		this.lodgerId = lodgerId;
	}
	
	public String getLodgerName() {
		return lodgerName;
	}

	public void setLodgerName(String lodgerName) {
		this.lodgerName = lodgerName;
	}
	
	public String getLodgerMobile() {
		return lodgerMobile;
	}

	public void setLodgerMobile(String lodgerMobile) {
		this.lodgerMobile = lodgerMobile;
	}
	
	public String getHouseDepositContractId() {
		return houseDepositContractId;
	}

	public void setHouseDepositContractId(String houseDepositContractId) {
		this.houseDepositContractId = houseDepositContractId;
	}
	
	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getAlipayTradeNo() {
		return alipayTradeNo;
	}

	public void setAlipayTradeNo(String alipayTradeNo) {
		this.alipayTradeNo = alipayTradeNo;
	}

	public DepositOrderStatus getStatus() {
		return status;
	}

	public void setStatus(DepositOrderStatus status) {
		this.status = status;
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
	
	public BigDecimal getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(BigDecimal rentAmount) {
		this.rentAmount = rentAmount;
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
	
}