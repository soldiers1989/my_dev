package com.ddf.member.service.simple;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.member.dto.BankCard;
import com.ddf.entity.member.query.BankCardQuery;
import com.ddf.entity.member.vo.BankCardVo;
import com.ddf.member.dao.BankCardDao;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * bank_card Service
 * @author robot
 * @version 2018-01-15
 */
@Service
public class BankCardService extends CrudServiceImpl<BankCard,BankCardVo,BankCardQuery>{
	@Autowired
	private BankCardDao bankCardDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	public Page<BankCardVo> query4page(int pageNum, int pageSize){
		BankCardQuery bankCardQuery = new BankCardQuery();
		bankCardQuery.buildPageSql(pageNum, pageSize);
		List<BankCardVo> list = this.findList(bankCardQuery);
		long totalCount = this.findCount(bankCardQuery);
		Page<BankCardVo> page = new Page<BankCardVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	public Page<BankCardVo> query4page(BankCardQuery bankCardQuery){
		if(bankCardQuery==null){
			bankCardQuery = new BankCardQuery();
		}
		if(StringUtil.isEmpty(bankCardQuery.getSortSql())){
			bankCardQuery.buildSortSql("order by a.create_date desc");
		}else{
			bankCardQuery.buildSortSql(bankCardQuery.getSortSql());
		}
		bankCardQuery.buildPageSql(bankCardQuery.getPageNum(), bankCardQuery.getPageSize());
		List<BankCardVo> list = this.findList(bankCardQuery);
		long totalCount = this.findCount(bankCardQuery);
		Page<BankCardVo> page = new Page<BankCardVo>(bankCardQuery.getPageNum(), bankCardQuery.getPageSize(), totalCount, list);
		return page;
	}
	

	public Page<BankCardVo> query4pagehelper(int pageNum, int pageSize){
		BankCardQuery bankCardQuery = new BankCardQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<BankCardVo> list = this.findList(bankCardQuery);
        Page<BankCardVo> page = new Page<BankCardVo>(list);
        return page;
	}
	
	/**
	 * 我的 用户银行卡
	 * @param currentUserId
	 * @return
	 */
	public List<BankCardVo> query4user(String currentUserId){
		if(StringUtil.isEmpty(currentUserId)){
			return null;
		}
		BankCardQuery bankCardQuery = new BankCardQuery();
		bankCardQuery.getBankCard().setUserId(currentUserId);
		return bankCardDao.query(bankCardQuery);
	}
	
	/**
	 * 添加
	 */
	public boolean create(BankCard bankCard){
		bankCard.setId(idReference.createId(TableName.bank_card).getData());
		bankCard.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(bankCard);
	}
	
	/**
	 * 修改
	 */
	public boolean modify(BankCard bankCard){
		bankCard.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(bankCard);
	}
}