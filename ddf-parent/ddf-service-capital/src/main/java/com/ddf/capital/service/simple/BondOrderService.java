package com.ddf.capital.service.simple;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ddf.capital.alipay.api.AlipayTradeAppApi;
import com.ddf.capital.dao.BondOrderDao;
import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.BondOrder;
import com.ddf.entity.capital.eo.BillOrderType;
import com.ddf.entity.capital.eo.BillStatus;
import com.ddf.entity.capital.eo.BondOrderStatus;
import com.ddf.entity.capital.query.BondOrderQuery;
import com.ddf.entity.capital.query.DepositOrderQuery;
import com.ddf.entity.capital.vo.BondOrderVo;
import com.ddf.entity.capital.vo.DepositOrderVo;
import com.ddf.entity.db.eo.TableName;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.reference.member.UserReference;
import com.ddf.util.ListUtil;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * bond_order Service
 * @author robot
 * @version 2018-01-22
 */
@Service
public class BondOrderService extends CrudServiceImpl<BondOrder,BondOrderVo,BondOrderQuery>{
	@Autowired
	private BondOrderDao bondOrderDao;
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private AlipayTradeAppApi alipayTradeAppApi;
	
	@Autowired
	private UserReference userReference;
	
	@Value("${bondNotify}")
	private String bondNotify;
	
	public Page<BondOrderVo> query4page(int pageNum, int pageSize){
		BondOrderQuery bondOrderQuery = new BondOrderQuery();
		bondOrderQuery.buildPageSql(pageNum, pageSize);
		List<BondOrderVo> list = this.findList(bondOrderQuery);
		long totalCount = this.findCount(bondOrderQuery);
		Page<BondOrderVo> page = new Page<BondOrderVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	public Page<BondOrderVo> query4page(BondOrderQuery bondOrderQuery){
		bondOrderQuery.buildPageSql();
		if(StringUtil.isEmpty(bondOrderQuery.getSortSql())) {
			bondOrderQuery.buildSortSql("order by a.create_date desc");
		}
		List<BondOrderVo> list = this.findList(bondOrderQuery);
		if(!ListUtil.isEmpty(list)) {
			for(BondOrderVo bondOrderVo:list) {
				bondOrderVo.setUser(userReference.query(bondOrderVo.getLandlordId()).getData());
			}
		}
		long totalCount = this.findCount(bondOrderQuery);
		Page<BondOrderVo> page = new Page<BondOrderVo>(bondOrderQuery.getPageNum(), bondOrderQuery.getPageSize(), totalCount, list);
		return page;
	}

	public Page<BondOrderVo> query4pagehelper(int pageNum, int pageSize){
		BondOrderQuery bondOrderQuery = new BondOrderQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<BondOrderVo> list = this.findList(bondOrderQuery);
        Page<BondOrderVo> page = new Page<BondOrderVo>(list);
        return page;
	}
	
	public String createOrder(BondOrder bondOrder) {
		bondOrder.setId(idReference.createId(TableName.bond_order).getData());
		Date currentDate=dateReference.queryCurrentDate().getData();
		bondOrder.setCreateDate(currentDate);
		bondOrder.setUpdateDate(currentDate);
		bondOrder.setNo(StringUtil.getOrderNum());
		bondOrder.setStatus(BondOrderStatus.proving);
		dao.create(bondOrder);
		String form=alipayTradeAppApi.alipayTradeAppPay(bondOrder.getId(), "点点房支付保证金", bondOrder.getAmount().toString(), bondNotify, null, null);
		//插入资金明细
		billService.create(bondOrder.getLandlordId(), BillOrderType.recharge_bond, bondOrder.getId(), bondOrder.getNo(), bondOrder.getAmount(),BillStatus.PROCESSING);
		return form;
	}
	
	public BondOrderVo query4userId(String userId){
		BondOrderQuery bondOrderQuery=new BondOrderQuery();
		bondOrderQuery.getBondOrder().setLandlordId(userId);
		bondOrderQuery.getBondOrder().setStatus(BondOrderStatus.proved);
		List<BondOrderVo> bondOrderVoList=dao.findList(bondOrderQuery);
		if(!ListUtil.isEmpty(bondOrderVoList)) {
			return bondOrderVoList.get(0);
		}
		return null;
	}
}