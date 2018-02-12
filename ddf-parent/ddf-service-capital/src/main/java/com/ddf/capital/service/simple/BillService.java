package com.ddf.capital.service.simple;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.capital.dao.BillDao;
import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.Bill;
import com.ddf.entity.capital.eo.BillOrderType;
import com.ddf.entity.capital.eo.BillStatus;
import com.ddf.entity.capital.query.BillQuery;
import com.ddf.entity.capital.query.WithdrawOrderQuery;
import com.ddf.entity.capital.vo.BillVo;
import com.ddf.entity.capital.vo.WithdrawOrderVo;
import com.ddf.entity.db.eo.TableName;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * bill Service
 * @author robot
 * @version 2018-01-22
 */
@Service
public class BillService extends CrudServiceImpl<Bill,BillVo,BillQuery>{
	@Autowired
	private BillDao billDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	public Page<BillVo> query4page(int pageNum, int pageSize){
		BillQuery billQuery = new BillQuery();
		billQuery.buildPageSql(pageNum, pageSize);
		List<BillVo> list = this.findList(billQuery);
		long totalCount = this.findCount(billQuery);
		Page<BillVo> page = new Page<BillVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	public Page<BillVo> query4page(BillQuery billQuery){
		/*billQuery.buildPageSql(billQuery.getPageNum(), billQuery.getPageSize());
		if(StringUtil.isNotEmpty(billQuery.getOrderBySql())) {
			billQuery.setOrderBySql("order by a.create_date desc");
		}*/
		billQuery.buildPageSql();
		if(StringUtil.isEmpty(billQuery.getSortSql())) {
			billQuery.buildSortSql("order by a.create_date desc");
		}else{
			billQuery.buildSortSql(billQuery.getSortSql());
		}
		List<BillVo> list = this.findList(billQuery);
		long totalCount = this.findCount(billQuery);
		Page<BillVo> page = new Page<BillVo>(billQuery.getPageNum(), billQuery.getPageSize(), totalCount, list);
		return page;
	}

	public Page<BillVo> query4pagehelper(int pageNum, int pageSize){
		BillQuery billQuery = new BillQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<BillVo> list = this.findList(billQuery);
        Page<BillVo> page = new Page<BillVo>(list);
        return page;
	}
	
	public void create(String userId,BillOrderType billOrderType,String orderId,String orderNo,BigDecimal amount,BillStatus billStatus) {
		Bill bill=new Bill();
		bill.setId(idReference.createId(TableName.bill).getData());
		bill.setUserId(userId);
		bill.setOrderId(orderId);
		bill.setOrderType(billOrderType);
		bill.setOrderId(orderId);
		bill.setOrderNo(orderNo);
		bill.setAmount(amount);
		bill.setStatus(billStatus);
		Date currentDate=dateReference.queryCurrentDate().getData();
		bill.setCreateDate(currentDate);
		bill.setUpdateDate(currentDate);
		dao.create(bill);
	}
}