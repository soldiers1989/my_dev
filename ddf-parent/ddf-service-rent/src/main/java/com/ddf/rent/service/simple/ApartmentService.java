package com.ddf.rent.service.simple;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.rent.dto.Apartment;
import com.ddf.entity.rent.eo.ApartmentDepositStatus;
import com.ddf.entity.rent.eo.ApartmentMatchStatus;
import com.ddf.entity.rent.eo.ApartmentStatus;
import com.ddf.entity.rent.query.ApartmentQuery;
import com.ddf.entity.rent.vo.ApartmentVo;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.rent.dao.ApartmentDao;

/**
 * apartment Service
 * 
 * @author robot
 * @version 2018-02-02
 */
@Service
public class ApartmentService extends CrudServiceImpl<Apartment, ApartmentVo, ApartmentQuery> {
	@Autowired
	private ApartmentDao apartmentDao;
	@Autowired
	private IdReference idReference;
	@Autowired
	private DateReference dateReference;

	public Page<ApartmentVo> query4page(ApartmentQuery apartmentQuery, int pageNum, int pageSize) {
		apartmentQuery.buildPageSql(pageNum, pageSize);
		List<ApartmentVo> list = this.findList(apartmentQuery);
		long totalCount = this.findCount(apartmentQuery);
		Page<ApartmentVo> page = new Page<ApartmentVo>(pageNum, pageSize, totalCount, list);
		return page;
	}

	/**
	 * 创建整租房源
	 */
	public boolean createApartment(Apartment apartment) {
		// 设置默认参数
		apartment.setId(idReference.createId(TableName.apartment).getData());
		apartment.setMatchStatus(ApartmentMatchStatus.open);
		apartment.setDepositStatus(ApartmentDepositStatus.close);
		apartment.setStatus(ApartmentStatus.wait_review);
		apartment.setHideFlag(false);
		Date tempDate = dateReference.queryCurrentDate().getData();
		apartment.setCreateDate(tempDate);
		apartment.setUpdateDate(tempDate);
		return apartmentDao.create(apartment);
	}

	/**
	 * 修改房源
	 */
	public boolean modifyApartment(Apartment apartment) {
		// 设置默认参数
		apartment.setStatus(ApartmentStatus.wait_review);
		apartment.setUpdateDate(dateReference.queryCurrentDate().getData());
		Apartment apartmentTemp = apartmentDao.query4id(apartment.getId());
		apartment.setUserId(apartmentTemp.getUserId());
		apartment.setAssistantId(apartmentTemp.getAssistantId());
		apartment.setMatchStatus(apartmentTemp.getMatchStatus());
		apartment.setDepositStatus(apartmentTemp.getDepositStatus());
		apartment.setHideFlag(apartmentTemp.getHideFlag());
		apartment.setCreateDate(apartmentTemp.getCreateDate());
		return apartmentDao.modify(apartment);
	}

	/**
	 * 伪删除
	 */
	public boolean remove4id(String id) {
		return apartmentDao.remove4id(id) > 0 ? true : false;
	}

	/**
	 * 审核
	 * @return
	 */
	public Boolean review(String id, ApartmentStatus status) {
		Apartment apartment = apartmentDao.query4id(id);
		apartment.setStatus(status);
		apartment.setReviewSuccessDate(dateReference.queryCurrentDate().getData());
		return this.apartmentDao.modify(apartment);
	}

	/**
	 * 停租、招租
	 * @return
	 */
	public Boolean matchStatus(String id, ApartmentMatchStatus apartmentMatchStatus) {
		Apartment apartment = apartmentDao.query4id(id);
		apartment.setMatchStatus(apartmentMatchStatus);
		return this.apartmentDao.modify(apartment);
	}

	/**
	 * 房源列表
	 * @return
	 */
	public Page<ApartmentVo> list(ApartmentQuery apartmentQuery) {
		int pageNum = apartmentQuery.getPageNum();
		int pageSize = apartmentQuery.getPageSize();
		apartmentQuery.buildSortSql("order by a.create_date desc");
		return this.query4page(apartmentQuery, pageNum, pageSize);
	}

	/**
	 * 我的房源
	 * @return
	 */
	public Page<ApartmentVo> pagequery4my(ApartmentQuery apartmentQuery) {
		int pageNum = apartmentQuery.getPageNum();
		int pageSize = apartmentQuery.getPageSize();
		apartmentQuery.getApartment().setHideFlag(false);
		apartmentQuery.buildSortSql("order by a.create_date desc");
		return this.query4page(apartmentQuery, pageNum, pageSize);
	}
}