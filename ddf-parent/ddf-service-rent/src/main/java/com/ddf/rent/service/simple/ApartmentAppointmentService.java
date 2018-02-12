package com.ddf.rent.service.simple;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.rent.dto.Apartment;
import com.ddf.entity.rent.dto.ApartmentAppointment;
import com.ddf.entity.rent.dto.ShareApartment;
import com.ddf.entity.rent.eo.ApartmentAppointmentApartmentType;
import com.ddf.entity.rent.eo.ApartmentAppointmentStatus;
import com.ddf.entity.rent.query.ApartmentAppointmentQuery;
import com.ddf.entity.rent.vo.ApartmentAppointmentVo;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.rent.dao.ApartmentAppointmentDao;
import com.ddf.rent.dao.ApartmentDao;
import com.ddf.rent.dao.ShareApartmentDao;

/**
 * apartment_appointment Service
 * 
 * @author robot
 * @version 2018-02-02
 */
@Service
public class ApartmentAppointmentService
		extends CrudServiceImpl<ApartmentAppointment, ApartmentAppointmentVo, ApartmentAppointmentQuery> {
	@Autowired
	private ApartmentAppointmentDao apartmentAppointmentDao;
	@Autowired
	private IdReference idReference;
	@Autowired
	private DateReference dateReference;
	@Autowired
	private ApartmentDao apartmentDao;
	@Autowired
	private ShareApartmentDao shareApartmentDao;

	public Page<ApartmentAppointmentVo> query4page(ApartmentAppointmentQuery apartmentAppointmentQuery, int pageNum,
			int pageSize) {
		apartmentAppointmentQuery.buildPageSql(pageNum, pageSize);
		List<ApartmentAppointmentVo> list = this.findList(apartmentAppointmentQuery);
		long totalCount = this.findCount(apartmentAppointmentQuery);
		Page<ApartmentAppointmentVo> page = new Page<ApartmentAppointmentVo>(pageNum, pageSize, totalCount, list);
		return page;
	}

	/**
	 * 创建预约
	 */
	public boolean createApartmentAppointment(ApartmentAppointment apartmentAppointment) {
		// 设置默认值
		apartmentAppointment.setId(idReference.createId(TableName.apartment_appointment).getData());
		apartmentAppointment.setStatus(ApartmentAppointmentStatus.wait);
		Date tempDate = dateReference.queryCurrentDate().getData();
		apartmentAppointment.setCreateDate(tempDate);
		apartmentAppointment.setUpdateDate(tempDate);
		return apartmentAppointmentDao.create(apartmentAppointment);
	}

	/**
	 * 修改预约
	 */
	public boolean modifyApartmentAppointment(ApartmentAppointment apartmentAppointment) {
		// 设置默认值
		apartmentAppointment.setUpdateDate(dateReference.queryCurrentDate().getData());
		apartmentAppointment.setStatus(ApartmentAppointmentStatus.wait);
		ApartmentAppointment apartmentAppointmentTemp = apartmentAppointmentDao.query4id(apartmentAppointment.getId());
		apartmentAppointment.setCreateDate(apartmentAppointmentTemp.getCreateDate());
		return apartmentAppointmentDao.modify(apartmentAppointment);
	}

	/**
	 * 取消预约/拒绝预约
	 */
	public boolean review(String id, ApartmentAppointmentStatus status) {
		ApartmentAppointment apartmentAppointment = apartmentAppointmentDao.query4id(id);
		apartmentAppointment.setStatus(status);
		return this.apartmentAppointmentDao.modify(apartmentAppointment);
	}

	/**
	 * 根据用户id，分页查询
	 */
	public Page<ApartmentAppointmentVo> pagequery4userId(ApartmentAppointmentQuery apartmentAppointmentQuery) {
		int pageNum = apartmentAppointmentQuery.getPageNum();
		int pageSize = apartmentAppointmentQuery.getPageSize();
		apartmentAppointmentQuery.buildSortSql("order by a.create_date desc");
		Page<ApartmentAppointmentVo> page = this.query4page(apartmentAppointmentQuery, pageNum, pageSize);
		// 判断当前用户是整租，合租,预约中---添加房源对象信息
		for (ApartmentAppointmentVo apartmentAppointmentVoObject : page.getList()) {
			if(ApartmentAppointmentApartmentType.whole_rent.equals(apartmentAppointmentVoObject.getApartmentType())){
				Apartment apartment = apartmentDao.query4id(apartmentAppointmentVoObject.getApartmentId());
				apartmentAppointmentVoObject.setApartment(apartment);
			}else{
				ShareApartment shareApartment = shareApartmentDao.query4id(apartmentAppointmentVoObject.getApartmentId());
				apartmentAppointmentVoObject.setShareApartment(shareApartment);
			}
		}
		return page;
	}

	/**
	 * 预约list
	 */
	public Page<ApartmentAppointmentVo> list(ApartmentAppointmentQuery apartmentAppointmentQuery) {
		int pageNum = apartmentAppointmentQuery.getPageNum();
		int pageSize = apartmentAppointmentQuery.getPageSize();
		apartmentAppointmentQuery.buildSortSql("order by a.create_date desc");
		Page<ApartmentAppointmentVo> page = this.query4page(apartmentAppointmentQuery, pageNum, pageSize);
		// 判断当前用户是整租，合租,预约中---添加房源对象信息
		for (ApartmentAppointmentVo apartmentAppointmentVoObject : page.getList()) {
			if(ApartmentAppointmentApartmentType.whole_rent.equals(apartmentAppointmentVoObject.getApartmentType())){
				Apartment apartment = apartmentDao.query4id(apartmentAppointmentVoObject.getApartmentId());
				apartmentAppointmentVoObject.setApartment(apartment);
			}else{
				ShareApartment shareApartment = shareApartmentDao.query4id(apartmentAppointmentVoObject.getApartmentId());
				apartmentAppointmentVoObject.setShareApartment(shareApartment);
			}
		}
		return page;
	}
}