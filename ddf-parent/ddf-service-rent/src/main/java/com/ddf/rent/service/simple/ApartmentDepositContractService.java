package com.ddf.rent.service.simple;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.rent.dto.Apartment;
import com.ddf.entity.rent.dto.ApartmentDepositContract;
import com.ddf.entity.rent.dto.ShareApartment;
import com.ddf.entity.rent.eo.ApartmentAppointmentApartmentType;
import com.ddf.entity.rent.eo.ApartmentDepositContractStatus;
import com.ddf.entity.rent.query.ApartmentDepositContractQuery;
import com.ddf.entity.rent.vo.ApartmentAppointmentVo;
import com.ddf.entity.rent.vo.ApartmentDepositContractVo;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.rent.dao.ApartmentDepositContractDao;
import com.github.pagehelper.PageHelper;

/**
 * apartment_deposit_contract Service
 * @author robot
 * @version 2018-02-02
 */
@Service
public class ApartmentDepositContractService extends CrudServiceImpl<ApartmentDepositContract,ApartmentDepositContractVo,ApartmentDepositContractQuery>{
	@Autowired
	private ApartmentDepositContractDao apartmentDepositContractDao;
	@Autowired
	private IdReference idReference;
	@Autowired
	private DateReference dateReference;
	
	public Page<ApartmentDepositContractVo> query4page(ApartmentDepositContractQuery apartmentDepositContractQuery,int pageNum, int pageSize){
		apartmentDepositContractQuery.buildPageSql(pageNum, pageSize);
		List<ApartmentDepositContractVo> list = this.findList(apartmentDepositContractQuery);
		long totalCount = this.findCount(apartmentDepositContractQuery);
		Page<ApartmentDepositContractVo> page = new Page<ApartmentDepositContractVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	

	public Page<ApartmentDepositContractVo> query4pagehelper(int pageNum, int pageSize){
		ApartmentDepositContractQuery apartmentDepositContractQuery = new ApartmentDepositContractQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<ApartmentDepositContractVo> list = this.findList(apartmentDepositContractQuery);
        Page<ApartmentDepositContractVo> page = new Page<ApartmentDepositContractVo>(list);
        return page;
	}


	/**
	 * 创建预定合同
	 */
	public Boolean createApartmentDepositContract(ApartmentDepositContract apartmentDepositContract) {
		// 设置默认值
		apartmentDepositContract.setId(idReference.createId(TableName.apartment_deposit_contract).getData());
		Date tempDate = dateReference.queryCurrentDate().getData();
		apartmentDepositContract.setCreateDate(tempDate);
		apartmentDepositContract.setUpdateDate(tempDate);
		apartmentDepositContract.setStatus(ApartmentDepositContractStatus.open);
		return apartmentDepositContractDao.create(apartmentDepositContract);
	}
	
	/**
	 * 修改预定合同
	 */
	public Boolean modifyApartmentDepositContract(ApartmentDepositContract apartmentDepositContract) {
		// 设置默认值
		apartmentDepositContract.setUpdateDate(dateReference.queryCurrentDate().getData());
		apartmentDepositContract.setStatus(ApartmentDepositContractStatus.open);
		ApartmentDepositContract apartmentDepositContractTemp = apartmentDepositContractDao.query4id(apartmentDepositContract.getId());
		apartmentDepositContract.setCreateDate(apartmentDepositContractTemp.getCreateDate());
		return apartmentDepositContractDao.modify(apartmentDepositContract);
	}


	/**
	 * 开启预定/关闭预定
	 */
	public boolean review(String id, ApartmentDepositContractStatus status) {
		ApartmentDepositContract apartmentDepositContract = apartmentDepositContractDao.query4id(id);
		apartmentDepositContract.setStatus(status);
		return this.apartmentDepositContractDao.modify(apartmentDepositContract);
	}


	public Page<ApartmentDepositContractVo> list(ApartmentDepositContractQuery apartmentDepositContractQuery) {
		int pageNum = apartmentDepositContractQuery.getPageNum();
		int pageSize = apartmentDepositContractQuery.getPageSize();
		apartmentDepositContractQuery.buildSortSql("order by a.create_date desc");
		Page<ApartmentDepositContractVo> page = this.query4page(apartmentDepositContractQuery, pageNum, pageSize);
		// 判断当前用户是整租，合租,预约中---添加房源对象信息
		return page;
	}
}