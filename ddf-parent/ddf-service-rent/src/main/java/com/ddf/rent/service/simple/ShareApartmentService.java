package com.ddf.rent.service.simple;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.rent.dto.ShareApartment;
import com.ddf.entity.rent.eo.ShareApartmentDepositStatus;
import com.ddf.entity.rent.eo.ShareApartmentMatchStatus;
import com.ddf.entity.rent.eo.ShareApartmentStatus;
import com.ddf.entity.rent.query.ShareApartmentQuery;
import com.ddf.entity.rent.vo.ShareApartmentVo;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.rent.dao.ShareApartmentDao;
import com.ddf.util.ApartmentNumUtil;

/**
 * share_apartment Service
 * 
 * @author robot
 * @version 2018-02-02
 */
@Service
public class ShareApartmentService extends CrudServiceImpl<ShareApartment, ShareApartmentVo, ShareApartmentQuery> {
	@Autowired
	private ShareApartmentDao shareApartmentDao;
	@Autowired
	private IdReference idReference;
	@Autowired
	private DateReference dateReference;

	/**
	 * 通用分页
	 */
	public Page<ShareApartmentVo> query4page(ShareApartmentQuery shareApartmentQuery,int pageNum, int pageSize) {
		shareApartmentQuery.buildPageSql(pageNum, pageSize);
		List<ShareApartmentVo> list = this.findList(shareApartmentQuery);
		long totalCount = this.findCount(shareApartmentQuery);
		Page<ShareApartmentVo> page = new Page<ShareApartmentVo>(pageNum, pageSize, totalCount, list);
		return page;
	}

	/**
	 * 修改合租房间
	 */
	public boolean modifyShareApartment(ShareApartment shareApartment) {
		shareApartment.setUpdateDate(dateReference.queryCurrentDate().getData());
		shareApartment.setDepositStatus(ShareApartmentDepositStatus.close);
		shareApartment.setStatus(ShareApartmentStatus.wait_review);
		ShareApartment shareApartmentTemp = shareApartmentDao.query4id(shareApartment.getId());
		shareApartment.setHouseId(shareApartmentTemp.getHouseId());
		shareApartment.setUserId(shareApartmentTemp.getUserId());
		shareApartment.setName(shareApartmentTemp.getName());
		shareApartment.setHideFlag(shareApartmentTemp.getHideFlag());
		shareApartment.setCreateDate(shareApartmentTemp.getCreateDate());
		return shareApartmentDao.modify(shareApartment);
	}

	/**
	 * 创建合租房间
	 */
	public boolean createShareApartment(ShareApartment shareApartment) {
		// 解析合租房间名为"A","B","C","D","E".....
		ShareApartmentQuery shareApartmentQuery = new ShareApartmentQuery();
		shareApartmentQuery.getShareApartment().setHouseId(shareApartment.getHouseId());
		long totalCount = this.findCount(shareApartmentQuery);
		String name = ApartmentNumUtil.getApartmentName4num((int)totalCount+1);
		
		// 添加一间房
		shareApartment.setId(idReference.createId(TableName.share_apartment).getData());
		shareApartment.setName(name);
		shareApartment.setDepositStatus(ShareApartmentDepositStatus.close);
		shareApartment.setStatus(ShareApartmentStatus.create_review);
		shareApartment.setHideFlag(false);
		Date tempDate = dateReference.queryCurrentDate().getData();
		shareApartment.setCreateDate(tempDate);
		shareApartment.setUpdateDate(tempDate);
		return shareApartmentDao.create(shareApartment);
	}

	/**
	 * 伪删除合租房间
	 */
	public boolean remove4id(String id) {
		return shareApartmentDao.remove4id(id) > 0 ? true : false;
	}

	/**
	 * 合租房间招租/合租房间停租
	 */
	public boolean matchStatus(String id, ShareApartmentMatchStatus matchStatus) {
		ShareApartment shareApartment = shareApartmentDao.query4id(id);
		shareApartment.setMatchStatus(matchStatus);
		return shareApartmentDao.modify(shareApartment);
	}

	/**
	 * 合租房间list(条件查询)
	 */
	public Page<ShareApartmentVo> list(ShareApartmentQuery shareApartmentQuery) {
		int pageNum = shareApartmentQuery.getPageNum();
		int pageSize = shareApartmentQuery.getPageSize();
		shareApartmentQuery.buildSortSql("order by a.create_date desc");
		Page<ShareApartmentVo> page = this.query4page(shareApartmentQuery, pageNum, pageSize);
		return page;
	}

	/**
	 * 合租房间审核通过/合租房间审核驳回
	 */
	public boolean review(String id, ShareApartmentStatus status) {
		ShareApartment shareApartment = shareApartmentDao.query4id(id);
		shareApartment.setStatus(status);
		shareApartment.setReviewSuccessDate(dateReference.queryCurrentDate().getData());
		return shareApartmentDao.modify(shareApartment);
	}
}