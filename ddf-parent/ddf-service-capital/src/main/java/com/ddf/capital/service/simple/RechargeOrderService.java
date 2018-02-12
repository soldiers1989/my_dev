package com.ddf.capital.service.simple;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ddf.capital.alipay.api.AlipayTradeAppApi;
import com.ddf.capital.dao.RechargeOrderDao;
import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.RechargeOrder;
import com.ddf.entity.capital.eo.BillOrderType;
import com.ddf.entity.capital.eo.BillStatus;
import com.ddf.entity.capital.eo.RechargeOrderStatus;
import com.ddf.entity.capital.query.RechargeOrderQuery;
import com.ddf.entity.capital.vo.RechargeOrderVo;
import com.ddf.entity.db.eo.TableName;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.github.pagehelper.PageHelper;

/**
 * recharge_order Service
 * @author robot
 * @version 2018-01-22
 */
@Service
public class RechargeOrderService extends CrudServiceImpl<RechargeOrder,RechargeOrderVo,RechargeOrderQuery>{
	@Autowired
	private RechargeOrderDao rechargeOrderDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private AlipayTradeAppApi alipayTradeAppApi;
	
	@Value("${rechargeNotify}")
	private String rechargeNotify;
	
	public Page<RechargeOrderVo> query4page(int pageNum, int pageSize){
		RechargeOrderQuery rechargeOrderQuery = new RechargeOrderQuery();
		rechargeOrderQuery.buildPageSql(pageNum, pageSize);
		List<RechargeOrderVo> list = this.findList(rechargeOrderQuery);
		long totalCount = this.findCount(rechargeOrderQuery);
		Page<RechargeOrderVo> page = new Page<RechargeOrderVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	

	public Page<RechargeOrderVo> query4pagehelper(int pageNum, int pageSize){
		RechargeOrderQuery rechargeOrderQuery = new RechargeOrderQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<RechargeOrderVo> list = this.findList(rechargeOrderQuery);
        Page<RechargeOrderVo> page = new Page<RechargeOrderVo>(list);
        return page;
	}
	
	public String createOrder(RechargeOrder rechargeOrder) {
		rechargeOrder.setId(idReference.createId(TableName.recharge_order).getData());
		Date currentDate=dateReference.queryCurrentDate().getData();
		rechargeOrder.setStatus(RechargeOrderStatus.PROCESSING);
		rechargeOrder.setCreateDate(currentDate);
		rechargeOrder.setUpdateDate(currentDate);
		dao.create(rechargeOrder);
		String form=alipayTradeAppApi.alipayTradeAppPay(rechargeOrder.getId(), "点点房充值", rechargeOrder.getAmount().toString(), rechargeNotify, null, null);
		//插入资金明细
		billService.create(rechargeOrder.getUserId(), BillOrderType.recharge, rechargeOrder.getId(), rechargeOrder.getId(), rechargeOrder.getAmount(),BillStatus.PROCESSING);
		return form;
	}
	
}