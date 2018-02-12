package com.ddf.rent.service.simple;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.rent.dto.ShareApartment;
import com.ddf.entity.rent.dto.ShareHouse;
import com.ddf.entity.rent.eo.ShareApartmentDepositStatus;
import com.ddf.entity.rent.eo.ShareApartmentMatchStatus;
import com.ddf.entity.rent.eo.ShareApartmentStatus;
import com.ddf.entity.rent.eo.ShareHouseStatus;
import com.ddf.entity.rent.query.ShareApartmentQuery;
import com.ddf.entity.rent.query.ShareHouseQuery;
import com.ddf.entity.rent.vo.ShareApartmentVo;
import com.ddf.entity.rent.vo.ShareHouseVo;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.rent.dao.ShareApartmentDao;
import com.ddf.rent.dao.ShareHouseDao;
import com.ddf.util.ApartmentNumUtil;

/**
 * share_house Service
 * 
 * @author robot
 * @version 2018-02-02
 */
@Service
public class ShareHouseService extends CrudServiceImpl<ShareHouse, ShareHouseVo, ShareHouseQuery> {
	@Autowired
	private ShareHouseDao shareHouseDao;
	@Autowired
	private IdReference idReference;
	@Autowired
	private DateReference dateReference;
	@Autowired
	private ShareApartmentDao shareApartmentDao;

	public Page<ShareHouseVo> query4page(ShareHouseQuery shareHouseQuery,int pageNum, int pageSize) {
		shareHouseQuery.buildPageSql(pageNum, pageSize);
		List<ShareHouseVo> list = this.findList(shareHouseQuery);
		long totalCount = this.findCount(shareHouseQuery);
		Page<ShareHouseVo> page = new Page<ShareHouseVo>(pageNum, pageSize, totalCount, list);
		return page;
	}

	/**
	 * 创建合租
	 */
	public boolean createShareHouse(ShareHouse shareHouse) {
		// 设置默认参数
		String houseId = idReference.createId(TableName.share_house).getData();
		shareHouse.setId(houseId);
		shareHouse.setStatus(ShareHouseStatus.wait_review);
		shareHouse.setHideFlag(false);
		Date tempDate = dateReference.queryCurrentDate().getData();
		shareHouse.setCreateDate(tempDate);
		shareHouse.setUpdateDate(tempDate);
		shareHouseDao.create(shareHouse);

		// 合租房间添加---房间名为"A","B","C","D","E".....
		if (shareHouse.getApartmentNum() > 0) {
			for (int i = 1; i <= shareHouse.getApartmentNum(); i++) {
				String name = ApartmentNumUtil.getApartmentName4num(i);
				ShareApartment shareApartmentTemp = new ShareApartment();
				shareApartmentTemp.setId(idReference.createId(TableName.share_apartment).getData());
				shareApartmentTemp.setHouseId(houseId);
				shareApartmentTemp.setUserId(shareHouse.getUserId());
				shareApartmentTemp.setName(name);
				shareApartmentTemp.setDepositStatus(ShareApartmentDepositStatus.close);
				shareApartmentTemp.setStatus(ShareApartmentStatus.create_review);
				shareApartmentTemp.setHideFlag(false);
				shareApartmentTemp.setCreateDate(tempDate);
				shareApartmentTemp.setUpdateDate(tempDate);
				shareApartmentDao.create(shareApartmentTemp);
			}
		}
		return true;
	}

	/**
	 * 修改合租
	 */
	public boolean modifyShareHouse(ShareHouse shareHouse) {
		// 设置默认参数
		shareHouse.setStatus(ShareHouseStatus.wait_review);
		shareHouse.setUpdateDate(dateReference.queryCurrentDate().getData());
		ShareHouse shareHouseTemp = shareHouseDao.query4id(shareHouse.getId());
		shareHouse.setHideFlag(shareHouseTemp.getHideFlag());
		shareHouse.setCreateDate(shareHouseTemp.getCreateDate());
		return shareHouseDao.modify(shareHouse);
	}

	/**
	 * 伪删除合租---级联删除合租房间
	 */
	public boolean remove4id(String id) {
		int i = shareHouseDao.remove4id(id);
		int j = shareApartmentDao.remove4houseId(id);
		return (i > 0 && j > 0) ? true : false;
	}

	/**
	 * 我的合租房源------》（包含多个房间）
	 */
	public Page<ShareHouseVo> pagequery4my(ShareHouseQuery shareHouseQuery) {
		int pageNum = shareHouseQuery.getPageNum();
		int pageSize = shareHouseQuery.getPageSize();
		shareHouseQuery.getShareHouse().setHideFlag(false);
		shareHouseQuery.buildSortSql("order by a.create_date desc");
		Page<ShareHouseVo> page = this.query4page(shareHouseQuery, pageNum, pageSize);
		// 遍历每个房源
		for(ShareHouseVo shareHouseVoObject:page.getList()){
			// 查询正在招租取正在招租房间的最低价
			ShareApartmentVo ShareApartmentVo = shareApartmentDao.query4houseId4minAmout4openStatus(shareHouseVoObject.getId(),ShareApartmentMatchStatus.open);
			if(ShareApartmentVo!=null){
				shareHouseVoObject.setMinAmout(ShareApartmentVo.getAmout());
			}
			// 查询正在招租的房间数
			ShareApartmentQuery shareApartmentQuery = new ShareApartmentQuery();
			shareApartmentQuery.getShareApartment().setHouseId(shareHouseVoObject.getId());
			shareApartmentQuery.getShareApartment().setHideFlag(false);
			shareApartmentQuery.getShareApartment().setMatchStatus(ShareApartmentMatchStatus.open);
			Long totalCount = shareApartmentDao.findCount(shareApartmentQuery);
			shareHouseVoObject.setMatchStatusOpenNum(totalCount);
		}
		return page;
	}

	/**
	 * 合租房间list
	 */
	public Page<ShareHouseVo> list(ShareHouseQuery shareHouseQuery) {
		int pageNum = shareHouseQuery.getPageNum();
		int pageSize = shareHouseQuery.getPageSize();
		shareHouseQuery.buildSortSql("order by a.create_date desc");
		Page<ShareHouseVo> page = this.query4page(shareHouseQuery, pageNum, pageSize);
		return page;
	}

	/**
	 * 合租房源审核
	 */
	public boolean review(String id, ShareHouseStatus status) {
		ShareHouse shareHouse = shareHouseDao.query4id(id);
		shareHouse.setStatus(status);
		shareHouse.setReviewSuccessDate(dateReference.queryCurrentDate().getData());
		return shareHouseDao.modify(shareHouse);
	}
}