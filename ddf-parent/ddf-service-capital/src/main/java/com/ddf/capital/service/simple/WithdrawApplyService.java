package com.ddf.capital.service.simple;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddf.capital.dao.WithdrawApplyDao;
import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.UserWallet;
import com.ddf.entity.capital.dto.WithdrawApply;
import com.ddf.entity.capital.eo.BillOrderType;
import com.ddf.entity.capital.eo.BillStatus;
import com.ddf.entity.capital.eo.WithdrawApplyStatus;
import com.ddf.entity.capital.query.UserWalletQuery;
import com.ddf.entity.capital.query.WithdrawApplyQuery;
import com.ddf.entity.capital.vo.UserWalletVo;
import com.ddf.entity.capital.vo.WithdrawApplyVo;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.exception.BusinessException;
import com.ddf.entity.member.dto.User;
import com.ddf.entity.member.vo.UserVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.reference.member.UserReference;
import com.ddf.util.ListUtil;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * withdraw_apply Service
 * @author robot
 * @version 2018-02-02
 */
@Service
public class WithdrawApplyService extends CrudServiceImpl<WithdrawApply,WithdrawApplyVo,WithdrawApplyQuery>{
	@Autowired
	private WithdrawApplyDao withdrawApplyDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	@Autowired
	private UserReference userReference;
	@Autowired
	private UserWalletService userWalletService;
	
	@Autowired
	private BillService billService;
	
	public Page<WithdrawApplyVo> query4page(int pageNum, int pageSize){
		WithdrawApplyQuery withdrawApplyQuery = new WithdrawApplyQuery();
		withdrawApplyQuery.buildPageSql(pageNum, pageSize);
		List<WithdrawApplyVo> list = this.findList(withdrawApplyQuery);
		long totalCount = this.findCount(withdrawApplyQuery);
		Page<WithdrawApplyVo> page = new Page<WithdrawApplyVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	

	public Page<WithdrawApplyVo> query4pagehelper(int pageNum, int pageSize){
		WithdrawApplyQuery withdrawApplyQuery = new WithdrawApplyQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<WithdrawApplyVo> list = this.findList(withdrawApplyQuery);
        Page<WithdrawApplyVo> page = new Page<WithdrawApplyVo>(list);
        return page;
	}
	
	@Transactional
	public void create(String userId,BigDecimal amount) throws BusinessException{
		//检查余额
		check4withdraw4balance(userId, amount);
		//插入提现申请
		WithdrawApply withdrawApply=new WithdrawApply();
		withdrawApply.setId(idReference.createId(TableName.withdraw_apply).getData());
		Date currentDate=dateReference.queryCurrentDate().getData();
		ApiResponse<User> resultUser=userReference.query(userId);
		if(resultUser!=null&&resultUser.getData()!=null) {
			withdrawApply.setAlipayAccount(resultUser.getData().getId());
		}
		withdrawApply.setAmount(amount);
		withdrawApply.setCreateDate(currentDate);
		withdrawApply.setUserId(userId);
		withdrawApply.setStatus(WithdrawApplyStatus.wait_review);
		dao.create(withdrawApply);
		//冻结金额
		userWalletService.freezeAmount(userId, amount);
		
		BigDecimal billAmount=withdrawApply.getAmount().multiply(new BigDecimal(-1));
		billService.create(withdrawApply.getUserId(), BillOrderType.withdraw, withdrawApply.getId(), withdrawApply.getId(), billAmount,BillStatus.PROCESSING);
	}
	
	/**
	 * 检查提现余额
	 * @param userId
	 * @param withdrawRuleId
	 */
	private void check4withdraw4balance(String userId,BigDecimal amount)throws BusinessException{
		UserWallet userWallet=null;
		UserWalletQuery query=new UserWalletQuery();
		query.getUserWallet().setUserId(userId);
		List<UserWalletVo> list=userWalletService.findList(query);
		if(!ListUtil.isEmpty(list)) {
			userWallet=list.get(0);
		}
		if(userWallet==null||userWallet.getAmount().compareTo(amount)==-1){
			throw new BusinessException("余额不足");
		}
	}
	
	public Page<WithdrawApplyVo> query4page(WithdrawApplyQuery withdrawApplyQuery){
		if(withdrawApplyQuery.getPageSize()!=0) {
			withdrawApplyQuery.buildPageSql();
		}
		if(StringUtil.isEmpty(withdrawApplyQuery.getSortSql())) {
			withdrawApplyQuery.buildSortSql("order by a.create_date desc");
		}
		List<WithdrawApplyVo> list = this.findList(withdrawApplyQuery);
		long totalCount = this.findCount(withdrawApplyQuery);
		Page<WithdrawApplyVo> page = new Page<WithdrawApplyVo>(withdrawApplyQuery.getPageNum(), withdrawApplyQuery.getPageSize(), totalCount, list);
		if(page!=null&&!ListUtil.isEmpty(page.getList())) {
			for(WithdrawApplyVo withdrawApplyVo:page.getList()) {
				withdrawApplyVo.setUser(userReference.query(withdrawApplyVo.getUserId()).getData());
			}
		}
		
		return page;
	}
	
	public List<WithdrawApplyVo> queryList4batchIdIsNull(Date startDate,Date endDate){
		List<WithdrawApplyVo> list=withdrawApplyDao.queryList4batchIdIsNull(startDate, endDate);
		if(!ListUtil.isEmpty(list)) {
			for(WithdrawApplyVo withdrawApplyVo:list) {
				withdrawApplyVo.setUser(userReference.query(withdrawApplyVo.getUserId()).getData());
			}
		}
		return list;
	}
	
	public List<WithdrawApplyVo> querySumUser(String batchId,String mobile) {
		String userId="";
		if(StringUtil.isNotEmpty(mobile)) {
			UserVo userVo=userReference.queryByMobile(mobile).getData();
			if(userVo!=null) {
				userId=userVo.getId();
			}
		}
		List<WithdrawApplyVo> withDrawApplyVoList=withdrawApplyDao.querysum4user(batchId, userId);
		if(!ListUtil.isEmpty(withDrawApplyVoList)) {
			for(WithdrawApplyVo withdrawApplyVo:withDrawApplyVoList) {
				User user=userReference.query(withdrawApplyVo.getUserId()).getData();
				withdrawApplyVo.setUser(user);
			}
		}
		return withDrawApplyVoList;
	}
}