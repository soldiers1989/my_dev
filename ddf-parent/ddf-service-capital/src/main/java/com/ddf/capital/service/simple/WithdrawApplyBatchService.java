package com.ddf.capital.service.simple;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddf.capital.alipay.api.AlipayBatchTransApi;
import com.ddf.capital.alipay.dto.BatchTransUser;
import com.ddf.capital.dao.UserWalletDao;
import com.ddf.capital.dao.WithdrawApplyBatchDao;
import com.ddf.capital.dao.WithdrawApplyDao;
import com.ddf.capital.dao.WithdrawOrderDao;
import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.Bill;
import com.ddf.entity.capital.dto.WithdrawApply;
import com.ddf.entity.capital.dto.WithdrawApplyBatch;
import com.ddf.entity.capital.dto.WithdrawOrder;
import com.ddf.entity.capital.eo.BillStatus;
import com.ddf.entity.capital.eo.WithdrawApplyBatchStatus;
import com.ddf.entity.capital.eo.WithdrawApplyStatus;
import com.ddf.entity.capital.eo.WithdrawOrderStatus;
import com.ddf.entity.capital.query.BillQuery;
import com.ddf.entity.capital.query.WithdrawApplyBatchQuery;
import com.ddf.entity.capital.query.WithdrawApplyQuery;
import com.ddf.entity.capital.query.WithdrawOrderQuery;
import com.ddf.entity.capital.vo.BillVo;
import com.ddf.entity.capital.vo.WithdrawApplyBatchVo;
import com.ddf.entity.capital.vo.WithdrawApplyVo;
import com.ddf.entity.capital.vo.WithdrawOrderVo;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.exception.BusinessException;
import com.ddf.entity.member.dto.User;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.reference.member.UserReference;
import com.ddf.util.JsonUtil;
import com.ddf.util.ListUtil;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * withdraw_apply_batch Service
 * @author robot
 * @version 2018-02-02
 */
@Service
public class WithdrawApplyBatchService extends CrudServiceImpl<WithdrawApplyBatch,WithdrawApplyBatchVo,WithdrawApplyBatchQuery>{
	
	private static Logger logger=LoggerFactory.getLogger(WithdrawApplyBatchService.class);
	
	@Autowired
	private WithdrawApplyBatchDao withdrawApplyBatchDao;
	
	@Autowired
	private WithdrawApplyDao withdrawApplyDao;
	
	@Autowired
	private WithdrawOrderDao withdrawOrderDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	@Autowired
	private UserReference userReference;
	@Autowired
	private AlipayBatchTransApi alipayBatchTransApi;
	@Autowired
	private UserWalletDao userWalletDao;
	@Autowired
	private BillService billService;
	
	@Value("${transferNotify}")
	private String transferNotify;
	
	public Page<WithdrawApplyBatchVo> query4page(int pageNum, int pageSize){
		WithdrawApplyBatchQuery withdrawApplyBatchQuery = new WithdrawApplyBatchQuery();
		withdrawApplyBatchQuery.buildPageSql(pageNum, pageSize);
		List<WithdrawApplyBatchVo> list = this.findList(withdrawApplyBatchQuery);
		long totalCount = this.findCount(withdrawApplyBatchQuery);
		Page<WithdrawApplyBatchVo> page = new Page<WithdrawApplyBatchVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	

	public Page<WithdrawApplyBatchVo> query4pagehelper(int pageNum, int pageSize){
		WithdrawApplyBatchQuery withdrawApplyBatchQuery = new WithdrawApplyBatchQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<WithdrawApplyBatchVo> list = this.findList(withdrawApplyBatchQuery);
        Page<WithdrawApplyBatchVo> page = new Page<WithdrawApplyBatchVo>(list);
        return page;
	}
	
	public Page<WithdrawApplyBatchVo> query4page(WithdrawApplyBatchQuery withdrawApplyBatchQuery){
		withdrawApplyBatchQuery.buildPageSql(withdrawApplyBatchQuery.getPageNum(), withdrawApplyBatchQuery.getPageSize());
		if(StringUtil.isNotEmpty(withdrawApplyBatchQuery.getSortSql())) {
			withdrawApplyBatchQuery.buildSortSql("order by a.create_date desc");
		}
		List<WithdrawApplyBatchVo> list = this.findList(withdrawApplyBatchQuery);
		long totalCount = this.findCount(withdrawApplyBatchQuery);
		Page<WithdrawApplyBatchVo> page = new Page<WithdrawApplyBatchVo>(withdrawApplyBatchQuery.getPageNum(), withdrawApplyBatchQuery.getPageSize(), totalCount, list);
		return page;
	}
	
	public void create(String batchName, String[] applyIds) {
		BigDecimal amount=withdrawApplyDao.sumAmountInapplyIds(applyIds);
		Date currentDate=dateReference.queryCurrentDate().getData();
		WithdrawApplyBatch withdrawApplyBatch = new WithdrawApplyBatch();
		withdrawApplyBatch.setId(idReference.createId(TableName.withdraw_apply_batch).getData());
		withdrawApplyBatch.setAlipayBatchNo(idReference.createId(TableName.ali_withdraw_apply_batch).getData());
		withdrawApplyBatch.setBatchName(batchName);
		withdrawApplyBatch.setAmount(amount);
		withdrawApplyBatch.setCreateDate(currentDate);
		withdrawApplyBatch.setStatus(WithdrawApplyBatchStatus.WAIT);
		
		Boolean result = withdrawApplyBatchDao.create(withdrawApplyBatch);
		if(result){
			withdrawApplyDao.updateBatchIdInapplyIds(withdrawApplyBatch.getId(), applyIds);
		}
	}
	
	public void transferCheck(String batchId) throws BusinessException{
		//验证重复转账
		WithdrawApplyBatch withdrawApplyBatch = withdrawApplyBatchDao.query4id(batchId);
		if(withdrawApplyBatch.getStatus() != WithdrawApplyBatchStatus.WAIT){
			throw new BusinessException("只有未转账的批次才能转账");
		}
		//验证重复提交
		List<WithdrawOrderVo> orderList = withdrawOrderDao.queryList4batchId4status(batchId, WithdrawOrderStatus.PROCESSING);
		if(!ListUtil.isEmpty(orderList)){
			throw new BusinessException("该批次转账处理中,请勿重复提交");
		}
	}
	
	@Transactional
	public String transfer(String alipayBatchNo,String batchId){
		//更新批次状态为转账中
		WithdrawApplyBatch withdrawApplyBatch=dao.query4id(batchId);
		withdrawApplyBatch.setStatus(WithdrawApplyBatchStatus.PROCESSING);
		dao.modify(withdrawApplyBatch);
		
		//查询批次下的申请列表
		WithdrawApplyQuery query=new WithdrawApplyQuery();
		query.getWithdrawApply().setBatchId(batchId);
		List<WithdrawApplyVo> applyList=withdrawApplyDao.findList(query);
		
		//生成提现订单
		batchCreateWithdrawOrder(applyList);
		
		//调用批量转账接口
		List<BatchTransUser> batchTransUserList = convert2BatchTransUserList(applyList);
		logger.info("开始调用批量转账接口,传入参数为:"+JsonUtil.toJson(batchTransUserList));
		String result = alipayBatchTransApi.batchTransRequest(batchTransUserList,alipayBatchNo,transferNotify);
		logger.info("调用批量转账接口返回结果:"+result);
		return result;
	}
	
	@Transactional
	public void batchCreateWithdrawOrder(List<WithdrawApplyVo> applyList){
		for (WithdrawApply withdrawApply : applyList) {
			WithdrawOrder withdrawOrder = convert2withdrawOrder(withdrawApply);
			withdrawOrderDao.create(withdrawOrder);
		}
	}
	
	private WithdrawOrder convert2withdrawOrder(WithdrawApply withdrawApply){
		Date currentDate=dateReference.queryCurrentDate().getData();
		WithdrawOrder withdrawOrder = new WithdrawOrder();
		withdrawOrder.setId(idReference.createId(TableName.withdraw_order).getData());
		withdrawOrder.setAlipayAccount(withdrawApply.getAlipayAccount());
		withdrawOrder.setAmount(withdrawApply.getAmount());
		withdrawOrder.setApplyId(withdrawApply.getId());
		withdrawOrder.setCreateDate(currentDate);
		withdrawOrder.setStatus(WithdrawOrderStatus.PROCESSING);
		withdrawOrder.setUserId(withdrawApply.getUserId());
		return withdrawOrder;
	}
	
	private List<BatchTransUser> convert2BatchTransUserList(List<WithdrawApplyVo> applyList){
		List<BatchTransUser> batchTransUserList = new ArrayList<BatchTransUser>();
		for (WithdrawApply withdrawApply : applyList) {
			ApiResponse<User> userResult=userReference.query(withdrawApply.getUserId());
			BatchTransUser batchTransUser = new BatchTransUser();
			batchTransUser.setAccount("18726909657");
			batchTransUser.setAmount(new BigDecimal("0.01"));
			batchTransUser.setRealName("年帅帅");
			batchTransUserList.add(batchTransUser);
		}
		return batchTransUserList;
	}
	
	@Transactional
	public void remove4id(String id) throws BusinessException{
		WithdrawApplyBatch withdrawApplyBatch = dao.query4id(id);
		if(withdrawApplyBatch.getStatus() != WithdrawApplyBatchStatus.WAIT){
			throw new BusinessException("只有未转账的批次才能删除");
		}
		//更新提现申请批次id为空
		withdrawApplyDao.updateBatchId2Null4batchId(id);
		//删除批次
		dao.remove(id);
	}
	
	@Transactional
	public void active(String id) throws BusinessException{
		WithdrawApplyBatch withdrawApplyBatch = dao.query4id(id);
		if(withdrawApplyBatch.getStatus() != WithdrawApplyBatchStatus.PROCESSING){
			throw new BusinessException("只有转账中的批次才能激活");
		}
		//更新提现订单为取消
		List<WithdrawOrderVo> withdrawOrders = withdrawOrderDao.queryList4batchId4status(id, WithdrawOrderStatus.PROCESSING);
		if(!ListUtil.isEmpty(withdrawOrders)){
			for(WithdrawOrderVo withdrawOrder:withdrawOrders) {
				withdrawOrder.setStatus(WithdrawOrderStatus.CANCLE);
				withdrawOrderDao.modify(withdrawOrder);
			}
		}
		//重新生成支付宝订单号
		String alipayBatchNo = idReference.createId(TableName.ali_withdraw_apply_batch).getData();
		withdrawApplyBatch.setAlipayBatchNo(alipayBatchNo);
		withdrawApplyBatch.setStatus(WithdrawApplyBatchStatus.WAIT);
		dao.modify(withdrawApplyBatch);
	}
	
	public void batchTransferSuccess(String batchId, List<String> successAlipayAccounts) {
		if(ListUtil.isEmpty(successAlipayAccounts)){
			return;
		}
		List<WithdrawApplyVo> withdrawApplyList = withdrawApplyDao.queryList4batchIdInalipayAccount(batchId, successAlipayAccounts);
		for (WithdrawApply withdrawApply : withdrawApplyList) {
			transferSuccess(withdrawApply.getId(),withdrawApply.getUserId(),withdrawApply.getAmount());
		}
	}
	
	@Transactional
	private void transferSuccess(String applyId,String userId,BigDecimal amount){
		//更新提现订单
		Date currentDate=dateReference.queryCurrentDate().getData();
		withdrawOrderDao.updateStatus4applyId4PROCESSING(WithdrawOrderStatus.SUCCESS,currentDate,applyId);
		//扣除冻结金额
		userWalletDao.reduceFreezeAmount(userId, amount);
		//更新提现申请
		WithdrawApply withdrawApply=withdrawApplyDao.query4id(applyId);
		withdrawApply.setStatus(WithdrawApplyStatus.trade_success);
		withdrawApplyDao.modify(withdrawApply);
		
		Bill bill=null;
		BillQuery billQuery=new BillQuery();
		billQuery.getBill().setOrderId(applyId);
		List<BillVo> billVoList=billService.findList(billQuery);
		if(!ListUtil.isEmpty(billVoList)) {
			bill=billVoList.get(0);
			bill.setStatus(BillStatus.SUCCESS);
			billService.modify(bill);
		}
	}

	public void batchTransferFail(String batchId, List<String> failAlipayAccounts) {
		if(ListUtil.isEmpty(failAlipayAccounts)){
			return;
		}
		List<WithdrawApplyVo> withdrawApplyList = withdrawApplyDao.queryList4batchIdInalipayAccount(batchId, failAlipayAccounts);
		for (WithdrawApply withdrawApply : withdrawApplyList) {
			transferFail(withdrawApply.getId());
		}
	}
	
	@Transactional
	private void transferFail(String applyId){
		//更新提现订单
		Date currentDate=dateReference.queryCurrentDate().getData();
		withdrawOrderDao.updateStatus4applyId4PROCESSING(WithdrawOrderStatus.FAILED,currentDate,applyId);
		//更新提现申请
		WithdrawApply withdrawApply=withdrawApplyDao.query4id(applyId);
		withdrawApply.setStatus(WithdrawApplyStatus.trade_failed);
		withdrawApplyDao.modify(withdrawApply);
		
		Bill bill=null;
		BillQuery billQuery=new BillQuery();
		billQuery.getBill().setOrderId(applyId);
		List<BillVo> billVoList=billService.findList(billQuery);
		if(!ListUtil.isEmpty(billVoList)) {
			bill=billVoList.get(0);
			bill.setStatus(BillStatus.FAILED);
			billService.modify(bill);
		}
	}
}