package com.ddf.rent.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.rent.query.ApartmentAppointmentQuery;
import com.ddf.entity.rent.vo.ApartmentAppointmentVo;
import com.ddf.entity.rent.dto.ApartmentAppointment;

/**
 * apartment_appointment DAO接口
 * @author robot
 * @version 2018-02-02
 */
public interface ApartmentAppointmentDao extends CrudDao<ApartmentAppointment,ApartmentAppointmentVo,ApartmentAppointmentQuery> {
	
}