package com.ddf.capital.service.simple;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ddf.capital.alipay.api.AlipayTradeAppApi;
import com.ddf.capital.dao.DepositOrderDao;
import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.DepositOrder;
import com.ddf.entity.capital.eo.DepositOrderStatus;
import com.ddf.entity.capital.query.DepositOrderQuery;
import com.ddf.entity.capital.vo.DepositOrderVo;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.member.dto.User;
import com.ddf.entity.rent.dto.ApartmentDepositContract;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.reference.member.UserReference;
import com.ddf.reference.rent.ApartmentDepositContractReference;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * deposit_order Service
 * @author robot
 * @version 2018-01-22
 */
@Service
public class DepositOrderService extends CrudServiceImpl<DepositOrder,DepositOrderVo,DepositOrderQuery>{
	@Autowired
	private DepositOrderDao depositOrderDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	@Autowired
	private UserReference userReference;
	@Autowired
	private ApartmentDepositContractReference apartmentDepositContractReference;
	
	@Autowired
	private AlipayTradeAppApi alipayTradeAppApi;
	
	@Value("${depositNotify}")
	private String depositNotify;
	
	
	public Page<DepositOrderVo> query4page(int pageNum, int pageSize){
		DepositOrderQuery depositOrderQuery = new DepositOrderQuery();
		depositOrderQuery.buildPageSql(pageNum, pageSize);
		List<DepositOrderVo> list = this.findList(depositOrderQuery);
		long totalCount = this.findCount(depositOrderQuery);
		Page<DepositOrderVo> page = new Page<DepositOrderVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	public Page<DepositOrderVo> query4page(DepositOrderQuery depositOrderQuery){
		depositOrderQuery.buildPageSql(depositOrderQuery.getPageNum(), depositOrderQuery.getPageSize());
		if(StringUtil.isNotEmpty(depositOrderQuery.getOrderBySql())) {
			depositOrderQuery.setOrderBySql("order by a.create_date desc");
		}
		List<DepositOrderVo> list = this.findList(depositOrderQuery);
		long totalCount = this.findCount(depositOrderQuery);
		Page<DepositOrderVo> page = new Page<DepositOrderVo>(depositOrderQuery.getPageNum(), depositOrderQuery.getPageSize(), totalCount, list);
		return page;
	}

	public Page<DepositOrderVo> query4pagehelper(int pageNum, int pageSize){
		DepositOrderQuery depositOrderQuery = new DepositOrderQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<DepositOrderVo> list = this.findList(depositOrderQuery);
        Page<DepositOrderVo> page = new Page<DepositOrderVo>(list);
        return page;
	}
	
	public String createOrder(DepositOrder depositOrder) {
		depositOrder.setId(idReference.createId(TableName.deposit_order).getData());
		Date currentDate=dateReference.queryCurrentDate().getData();
		depositOrder.setCreateDate(currentDate);
		depositOrder.setUpdateDate(currentDate);
		depositOrder.setNo(StringUtil.getOrderNum());
		depositOrder.setStatus(DepositOrderStatus.PROCESSING);
		
		ApiResponse<ApartmentDepositContract> apartmentDepositContractRes=apartmentDepositContractReference.query(depositOrder.getHouseDepositContractId());
		if(apartmentDepositContractRes!=null&&apartmentDepositContractRes.getData()!=null) {
			ApartmentDepositContract apartmentDepositContract=apartmentDepositContractRes.getData();
			depositOrder.setAmount(apartmentDepositContract.getDepositAmout());
			depositOrder.setHouseId(apartmentDepositContract.getApartmentId());
			depositOrder.setCheckInDate(apartmentDepositContract.getCheckInDate());
			depositOrder.setCheckOutDate(apartmentDepositContract.getCheckOutDate());
			depositOrder.setPayType(apartmentDepositContract.getPayType());
			depositOrder.setRentAmount(apartmentDepositContract.getRentAmount());
			depositOrder.setRentType(apartmentDepositContract.getRentType());
			if(StringUtil.isNotEmpty(depositOrder.getLandlordId())) {
				ApiResponse<User> userRes=userReference.query(depositOrder.getLandlordId());
				if(userRes!=null&&userRes.getData()!=null) {
					depositOrder.setLandlordMobile(userRes.getData().getMobile());
					depositOrder.setLandlordName(userRes.getData().getNickName());
				}
			}
			if(StringUtil.isNotEmpty(depositOrder.getLodgerId())) {
				ApiResponse<User> userRes=userReference.query(depositOrder.getLodgerId());
				if(userRes!=null&&userRes.getData()!=null) {
					depositOrder.setLodgerMobile(userRes.getData().getMobile());
					depositOrder.setLodgerName(userRes.getData().getNickName());
				}
			}
		}
		dao.create(depositOrder);
		
		String form=alipayTradeAppApi.alipayTradeAppPay(depositOrder.getId(), "点点房支付定金", depositOrder.getAmount().toString(), depositNotify, null, null);
		return form;
	}
	
	public List<DepositOrderVo> batchquery4lodgerId(String lodgerId){
		DepositOrderQuery depositOrderQuery=new DepositOrderQuery();
		depositOrderQuery.getDepositOrder().setLodgerId(lodgerId);
		List<DepositOrderVo> depositOrderVoList=dao.findList(depositOrderQuery);
		return depositOrderVoList;
	}
	
	public List<DepositOrderVo> batchquery4landlordId(String landlordId){
		DepositOrderQuery depositOrderQuery=new DepositOrderQuery();
		depositOrderQuery.getDepositOrder().setLandlordId(landlordId);
		List<DepositOrderVo> depositOrderVoList=dao.findList(depositOrderQuery);
		return depositOrderVoList;
	}
}