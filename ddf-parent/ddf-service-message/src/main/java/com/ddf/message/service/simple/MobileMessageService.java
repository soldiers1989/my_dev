package com.ddf.message.service.simple;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.github.pagehelper.PageHelper;
import com.ddf.entity.message.dto.MobileMessage;
import com.ddf.entity.message.query.MobileMessageQuery;
import com.ddf.entity.message.vo.MobileMessageVo;
import com.ddf.message.dao.MobileMessageDao;

/**
 * mobile_message Service
 * @author robot
 * @version 2018-01-15
 */
@Service
public class MobileMessageService extends CrudServiceImpl<MobileMessage,MobileMessageVo,MobileMessageQuery>{
	@Autowired
	private MobileMessageDao mobileMessageDao;
	
	public Page<MobileMessageVo> query4page(int pageNum, int pageSize){
		MobileMessageQuery mobileMessageQuery = new MobileMessageQuery();
		mobileMessageQuery.buildPageSql(pageNum, pageSize);
		List<MobileMessageVo> list = this.findList(mobileMessageQuery);
		long totalCount = this.findCount(mobileMessageQuery);
		Page<MobileMessageVo> page = new Page<MobileMessageVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	

	public Page<MobileMessageVo> query4pagehelper(int pageNum, int pageSize){
		MobileMessageQuery mobileMessageQuery = new MobileMessageQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<MobileMessageVo> list = this.findList(mobileMessageQuery);
        Page<MobileMessageVo> page = new Page<MobileMessageVo>(list);
        return page;
	}
}