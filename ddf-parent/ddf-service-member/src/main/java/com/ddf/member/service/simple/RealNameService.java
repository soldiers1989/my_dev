package com.ddf.member.service.simple;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.member.dto.RealName;
import com.ddf.entity.member.dto.User;
import com.ddf.entity.member.eo.RealNameStatus;
import com.ddf.entity.member.query.RealNameQuery;
import com.ddf.entity.member.vo.RealNameVo;
import com.ddf.member.dao.RealNameDao;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * real_name Service
 * @author robot
 * @version 2018-01-15
 */
@Service
public class RealNameService extends CrudServiceImpl<RealName,RealNameVo,RealNameQuery>{
	@Autowired
	private RealNameDao realNameDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	@Autowired
	private UserService userService;
	
	public Page<RealNameVo> query4page(int pageNum, int pageSize){
		RealNameQuery realNameQuery = new RealNameQuery();
		realNameQuery.buildPageSql(pageNum, pageSize);
		List<RealNameVo> list = this.findList(realNameQuery);
		long totalCount = this.findCount(realNameQuery);
		Page<RealNameVo> page = new Page<RealNameVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	public Page<RealNameVo> query4page(RealNameQuery realNameQuery){
		if(realNameQuery==null){
			realNameQuery = new RealNameQuery();
		}
		if(StringUtil.isEmpty(realNameQuery.getSortSql())){
			realNameQuery.buildSortSql("order by a.create_date desc");
		}else{
			realNameQuery.buildSortSql(realNameQuery.getSortSql());
		}
		realNameQuery.buildPageSql(realNameQuery.getPageNum(), realNameQuery.getPageSize());
		List<RealNameVo> list = this.findList(realNameQuery);
		long totalCount = this.findCount(realNameQuery);
		Page<RealNameVo> page = new Page<RealNameVo>(realNameQuery.getPageNum(), realNameQuery.getPageSize(), totalCount, list);
		return page;
	}
	
	public Page<RealNameVo> query4pageAndUser(RealNameQuery realNameQuery){
		Page<RealNameVo> page = query4page(realNameQuery);
		if(page!=null && page.getList()!=null && page.getList().size()>0){
			for(int i = 0 ; i < page.getList().size() ; i ++ ){
				RealNameVo vo = page.getList().get(i);
				if(vo != null && vo.getUserId() != null){
					User user = userService.query4id(vo.getUserId());
					page.getList().get(i).setUser(user);
				}
			}
		}
		return page;
	}
	

	public Page<RealNameVo> query4pagehelper(int pageNum, int pageSize){
		RealNameQuery realNameQuery = new RealNameQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<RealNameVo> list = this.findList(realNameQuery);
        Page<RealNameVo> page = new Page<RealNameVo>(list);
        return page;
	}
	
	/**
	 * 我的 实名认证
	 * @param currentUserId
	 * @return
	 */
	public RealNameVo query4user(String currentUserId){
		if(StringUtil.isEmpty(currentUserId)){
			return null;
		}
		RealNameQuery realNameQuery = new RealNameQuery();
		realNameQuery.getRealName().setUserId(currentUserId);
		return realNameDao.query(realNameQuery);
	}
	
	
	public Boolean updateStatus(String id,RealNameStatus status){
		if(StringUtil.isEmpty(id)||status==null){
			return null;
		}
		RealName realName = new RealName();
		realName.setId(id);
		realName.setStatus(status);
		realName.setUpdateDate(dateReference.queryCurrentDate().getData());
		return realNameDao.updateStatus(realName);
	}
	
	/**
	 * 添加
	 */
	public boolean create(RealName realName){
		realName.setId(idReference.createId(TableName.real_name).getData());
		realName.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(realName);
	}
	
	/**
	 * 修改
	 */
	public boolean modify(RealName realName){
		realName.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(realName);
	}
}