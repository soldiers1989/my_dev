package com.ddf.capital.service.simple;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddf.capital.dao.BondOrderRefundApplyDao;
import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.BondOrder;
import com.ddf.entity.capital.dto.BondOrderRefundApply;
import com.ddf.entity.capital.eo.BillOrderType;
import com.ddf.entity.capital.eo.BillStatus;
import com.ddf.entity.capital.eo.BondOrderRefundApplyStatus;
import com.ddf.entity.capital.query.BondOrderQuery;
import com.ddf.entity.capital.query.BondOrderRefundApplyQuery;
import com.ddf.entity.capital.vo.BondOrderRefundApplyVo;
import com.ddf.entity.capital.vo.BondOrderVo;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.exception.BusinessException;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.reference.member.UserReference;
import com.ddf.util.ListUtil;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * bond_order_refund_apply Service
 * @author robot
 * @version 2018-01-22
 */
@Service
public class BondOrderRefundApplyService extends CrudServiceImpl<BondOrderRefundApply,BondOrderRefundApplyVo,BondOrderRefundApplyQuery>{
	@Autowired
	private BondOrderRefundApplyDao bondOrderRefundApplyDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	@Autowired
	private UserReference userReference;
	@Autowired
	private BondOrderService bondOrderService;
	
	@Autowired
	private BillService billService;
	@Autowired
	private UserWalletService userWalletService;
	
	public Page<BondOrderRefundApplyVo> query4page(int pageNum, int pageSize){
		BondOrderRefundApplyQuery bondOrderRefundApplyQuery = new BondOrderRefundApplyQuery();
		bondOrderRefundApplyQuery.buildPageSql(pageNum, pageSize);
		List<BondOrderRefundApplyVo> list = this.findList(bondOrderRefundApplyQuery);
		long totalCount = this.findCount(bondOrderRefundApplyQuery);
		Page<BondOrderRefundApplyVo> page = new Page<BondOrderRefundApplyVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	public Page<BondOrderRefundApplyVo> query4page(BondOrderRefundApplyQuery bondOrderRefundApplyQuery){
		bondOrderRefundApplyQuery.buildPageSql(bondOrderRefundApplyQuery.getPageNum(), bondOrderRefundApplyQuery.getPageSize());
		if(StringUtil.isNotEmpty(bondOrderRefundApplyQuery.getOrderBySql())) {
			bondOrderRefundApplyQuery.setOrderBySql("order by a.create_date desc");
		}
		List<BondOrderRefundApplyVo> list = this.findList(bondOrderRefundApplyQuery);
		long totalCount = this.findCount(bondOrderRefundApplyQuery);
		Page<BondOrderRefundApplyVo> page = new Page<BondOrderRefundApplyVo>(bondOrderRefundApplyQuery.getPageNum(), bondOrderRefundApplyQuery.getPageSize(), totalCount, list);
		return page;
	}

	public Page<BondOrderRefundApplyVo> query4pagehelper(int pageNum, int pageSize){
		BondOrderRefundApplyQuery bondOrderRefundApplyQuery = new BondOrderRefundApplyQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<BondOrderRefundApplyVo> list = this.findList(bondOrderRefundApplyQuery);
        Page<BondOrderRefundApplyVo> page = new Page<BondOrderRefundApplyVo>(list);
        return page;
	}
	
	@Transactional
	public void createOrder(BondOrderRefundApply bondOrderRefundApply) throws BusinessException{
		bondOrderRefundApply.setId(idReference.createId(TableName.bond_order_refund_apply).getData());
		Date currentDate=dateReference.queryCurrentDate().getData();
		bondOrderRefundApply.setCreateDate(currentDate);
		bondOrderRefundApply.setUpdateDate(currentDate);
		bondOrderRefundApply.setNo(StringUtil.getOrderNum());
		bondOrderRefundApply.setStatus(BondOrderRefundApplyStatus.SUCCESS);
		dao.create(bondOrderRefundApply);
		//设置保证金状态
		BondOrder bondOrder=bondOrderService.query4id(bondOrderRefundApply.getBondOrderId());
		if(bondOrder!=null) {
			userReference.setBondFlag(bondOrder.getLandlordId(), false);
		}
		//修改钱包金额
		userWalletService.consume(bondOrder.getLandlordId(), bondOrder.getAmount());
		//插入资金明细
		//取负数
		BigDecimal billAmount=bondOrder.getAmount().multiply(new BigDecimal(-1));
		billService.create(bondOrder.getLandlordId(), BillOrderType.withdraw_bond, bondOrderRefundApply.getId(), bondOrderRefundApply.getNo(), 
				billAmount,BillStatus.SUCCESS);
	}
	
	public BondOrderRefundApplyVo query4bondOrderId(String bondOrderId){
		BondOrderRefundApplyQuery bondOrderRefundApplyQuery=new BondOrderRefundApplyQuery();
		bondOrderRefundApplyQuery.getBondOrderRefundApply().setBondOrderId(bondOrderId);
		List<BondOrderRefundApplyVo> bondOrderRefundApplyVoList=dao.findList(bondOrderRefundApplyQuery);
		if(!ListUtil.isEmpty(bondOrderRefundApplyVoList)) {
			return bondOrderRefundApplyVoList.get(0);
		}
		return null;
	}
}