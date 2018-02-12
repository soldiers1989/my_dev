package com.ddf.capital.service.simple;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.capital.dao.WithdrawOrderDao;
import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.WithdrawOrder;
import com.ddf.entity.capital.eo.BillOrderType;
import com.ddf.entity.capital.eo.BillStatus;
import com.ddf.entity.capital.eo.WithdrawOrderStatus;
import com.ddf.entity.capital.query.BondOrderRefundApplyQuery;
import com.ddf.entity.capital.query.WithdrawOrderQuery;
import com.ddf.entity.capital.vo.BondOrderRefundApplyVo;
import com.ddf.entity.capital.vo.WithdrawOrderVo;
import com.ddf.entity.db.eo.TableName;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * withdraw_order Service
 * @author robot
 * @version 2018-01-22
 */
@Service
public class WithdrawOrderService extends CrudServiceImpl<WithdrawOrder,WithdrawOrderVo,WithdrawOrderQuery>{
	@Autowired
	private WithdrawOrderDao withdrawOrderDao;
	
	@Autowired
	private DateReference dateReference;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private UserWalletService userWalletService;
	
	public Page<WithdrawOrderVo> query4page(int pageNum, int pageSize){
		WithdrawOrderQuery withdrawOrderQuery = new WithdrawOrderQuery();
		withdrawOrderQuery.buildPageSql(pageNum, pageSize);
		List<WithdrawOrderVo> list = this.findList(withdrawOrderQuery);
		long totalCount = this.findCount(withdrawOrderQuery);
		Page<WithdrawOrderVo> page = new Page<WithdrawOrderVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	public Page<WithdrawOrderVo> query4page(WithdrawOrderQuery withdrawOrderQuery){
		withdrawOrderQuery.buildPageSql(withdrawOrderQuery.getPageNum(), withdrawOrderQuery.getPageSize());
		if(StringUtil.isNotEmpty(withdrawOrderQuery.getOrderBySql())) {
			withdrawOrderQuery.setOrderBySql("order by a.create_date desc");
		}
		List<WithdrawOrderVo> list = this.findList(withdrawOrderQuery);
		long totalCount = this.findCount(withdrawOrderQuery);
		Page<WithdrawOrderVo> page = new Page<WithdrawOrderVo>(withdrawOrderQuery.getPageNum(), withdrawOrderQuery.getPageSize(), totalCount, list);
		return page;
	}

	public Page<WithdrawOrderVo> query4pagehelper(int pageNum, int pageSize){
		WithdrawOrderQuery withdrawOrderQuery = new WithdrawOrderQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<WithdrawOrderVo> list = this.findList(withdrawOrderQuery);
        Page<WithdrawOrderVo> page = new Page<WithdrawOrderVo>(list);
        return page;
	}
	
	public void createOrder(WithdrawOrder withdrawOrder) {
		withdrawOrder.setId(idReference.createId(TableName.withdraw_order).getData());
		Date currentDate=dateReference.queryCurrentDate().getData();
		withdrawOrder.setCreateDate(currentDate);
		withdrawOrder.setUpdateDate(currentDate);
		withdrawOrder.setApplyId(StringUtil.getShortOrderNum());
		withdrawOrder.setStatus(WithdrawOrderStatus.SUCCESS);
		dao.create(withdrawOrder);
		
		//修改钱包金额
		userWalletService.consume(withdrawOrder.getUserId(), withdrawOrder.getAmount());
		//插入资金明细
		BigDecimal billAmount=withdrawOrder.getAmount().multiply(new BigDecimal(-1));
		billService.create(withdrawOrder.getUserId(), BillOrderType.withdraw, withdrawOrder.getId(), withdrawOrder.getId(), billAmount,BillStatus.PROCESSING);
	}
}