package com.ddf.rent.service.simple;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.member.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.ddf.entity.rent.dto.Apartment;
import com.ddf.entity.rent.dto.ApartmentMark;
import com.ddf.entity.rent.dto.ShareApartment;
import com.ddf.entity.rent.eo.ApartmentMarkApartmentType;
import com.ddf.entity.rent.query.ApartmentMarkQuery;
import com.ddf.entity.rent.vo.ApartmentMarkVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.reference.member.UserReference;
import com.ddf.rent.dao.ApartmentDao;
import com.ddf.rent.dao.ApartmentMarkDao;
import com.ddf.rent.dao.ShareApartmentDao;

/**
 * apartment_mark Service
 * @author robot
 * @version 2018-02-02
 */
@Service
public class ApartmentMarkService extends CrudServiceImpl<ApartmentMark,ApartmentMarkVo,ApartmentMarkQuery>{
	@Autowired
	private ApartmentMarkDao apartmentMarkDao;
	@Autowired
	private IdReference idReference;
	@Autowired
	private DateReference dateReference;
	@Autowired
	private ApartmentDao apartmentDao;
	@Autowired
	private ShareApartmentDao shareApartmentDao;
	@Autowired
	private UserReference userReference;
	
	public Page<ApartmentMarkVo> query4page(ApartmentMarkQuery apartmentMarkQuery, int pageNum, int pageSize){
		apartmentMarkQuery.buildPageSql(pageNum, pageSize);
		List<ApartmentMarkVo> list = this.findList(apartmentMarkQuery);
		long totalCount = this.findCount(apartmentMarkQuery);
		Page<ApartmentMarkVo> page = new Page<ApartmentMarkVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	

	public Page<ApartmentMarkVo> query4pagehelper(int pageNum, int pageSize){
		ApartmentMarkQuery apartmentMarkQuery = new ApartmentMarkQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<ApartmentMarkVo> list = this.findList(apartmentMarkQuery);
        Page<ApartmentMarkVo> page = new Page<ApartmentMarkVo>(list);
        return page;
	}


	/**
	 * 创建收藏
	 */
	public boolean createApartmentMark(ApartmentMark apartmentMark) {
		// 设置默认参数
		apartmentMark.setId(idReference.createId(TableName.apartment_mark).getData());
		Date tempDate = dateReference.queryCurrentDate().getData();
		apartmentMark.setCreateDate(tempDate);
		apartmentMark.setUpdateDate(tempDate);
		return apartmentMarkDao.create(apartmentMark);
	}


	/**
	 * 我的收藏
	 */
	public Page<ApartmentMarkVo> pagequery4my(ApartmentMarkQuery apartmentMarkQuery) {
		int pageNum = apartmentMarkQuery.getPageNum();
		int pageSize = apartmentMarkQuery.getPageSize();
		Page<ApartmentMarkVo> page = query4page(apartmentMarkQuery, pageNum, pageSize);
		// 判断收藏的是整租还是合租
		for(ApartmentMarkVo apartmentMarkVoObject:page.getList()){
			if(ApartmentMarkApartmentType.whole_rent.equals(apartmentMarkVoObject.getApartmentType())){
				Apartment apartment = apartmentDao.query4id(apartmentMarkVoObject.getApartmentId());
				apartmentMarkVoObject.setApartment(apartment);
			}else{
				ShareApartment shareApartment = shareApartmentDao.query4id(apartmentMarkVoObject.getApartmentId());
				apartmentMarkVoObject.setShareApartment(shareApartment);
			}
		}
		return page;
	}


	/**
	 * 收藏列表
	 */
	public Page<ApartmentMarkVo> list(ApartmentMarkQuery apartmentMarkQuery) {
		// 根据用户手机号获取id
		if(StringUtils.isNotBlank(apartmentMarkQuery.getMobile())){
			ApiResponse<UserVo> apiResponse4user= userReference.queryByMobile(apartmentMarkQuery.getMobile());
			apartmentMarkQuery.getApartmentMark().setUserId(apiResponse4user.getData().getId());
		}
		int pageNum = apartmentMarkQuery.getPageNum();
		int pageSize = apartmentMarkQuery.getPageSize();
		Page<ApartmentMarkVo> page = this.query4page(apartmentMarkQuery,pageNum,pageSize);
		// 判断收藏的是整租还是合租
		for(ApartmentMarkVo apartmentMarkVoObject:page.getList()){
			if(ApartmentMarkApartmentType.whole_rent.equals(apartmentMarkVoObject.getApartmentType())){
				Apartment apartment = apartmentDao.query4id(apartmentMarkVoObject.getApartmentId());
				apartmentMarkVoObject.setApartment(apartment);
			}else{
				ShareApartment shareApartment = shareApartmentDao.query4id(apartmentMarkVoObject.getApartmentId());
				apartmentMarkVoObject.setShareApartment(shareApartment);
			}
//			if(StringUtils.isNotBlank(apartmentMarkVoObject.getUserId())){
//				apartmentMarkVoObject.setUser(userReference.query(apartmentMarkVoObject.getUserId()).getData());
//			}
		}
		return page;
	}

}