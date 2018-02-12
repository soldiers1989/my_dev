package com.ddf.message.service.simple;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.message.dto.LodgerComplain;
import com.ddf.entity.message.query.LodgerComplainQuery;
import com.ddf.entity.message.vo.LodgerComplainVo;
import com.ddf.message.dao.LodgerComplainDao;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * lodger_complain Service
 * @author robot
 * @version 2018-01-15
 */
@Service
public class LodgerComplainService extends CrudServiceImpl<LodgerComplain,LodgerComplainVo,LodgerComplainQuery>{
	@Autowired
	private LodgerComplainDao lodgerComplainDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	public Page<LodgerComplainVo> query4page(int pageNum, int pageSize){
		LodgerComplainQuery lodgerComplainQuery = new LodgerComplainQuery();
		lodgerComplainQuery.buildPageSql(pageNum, pageSize);
		List<LodgerComplainVo> list = this.findList(lodgerComplainQuery);
		long totalCount = this.findCount(lodgerComplainQuery);
		Page<LodgerComplainVo> page = new Page<LodgerComplainVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	

	public Page<LodgerComplainVo> query4pagehelper(int pageNum, int pageSize){
		LodgerComplainQuery lodgerComplainQuery = new LodgerComplainQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<LodgerComplainVo> list = this.findList(lodgerComplainQuery);
        Page<LodgerComplainVo> page = new Page<LodgerComplainVo>(list);
        return page;
	}
	
	/**
	 * 添加
	 */
	public boolean create(LodgerComplain lodgerComplain){
		lodgerComplain.setId(idReference.createId(TableName.lodger_complain).getData());
		lodgerComplain.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(lodgerComplain);
	}
	
	/**
	 * 修改
	 */
	public boolean modify(LodgerComplain lodgerComplain){
		lodgerComplain.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(lodgerComplain);
	}
	
	/**
	 * 按房客查询 举报房客 列表
	 * @param lodgerId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<LodgerComplainVo> query4lodgerId(String lodgerId,int pageNum, int pageSize){
		LodgerComplainQuery lodgerComplainQuery = new LodgerComplainQuery();
		lodgerComplainQuery.getLodgerComplain().setLodgerId(lodgerId);
		lodgerComplainQuery.buildPageSql(pageNum, pageSize);
		List<LodgerComplainVo> list = this.findList(lodgerComplainQuery);
		long totalCount = this.findCount(lodgerComplainQuery);
		Page<LodgerComplainVo> page = new Page<LodgerComplainVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	/**
	 * 查询 举报房客 列表
	 * @param lodgerComplainQuery
	 * @return
	 */
	public Page<LodgerComplainVo> query4page(LodgerComplainQuery lodgerComplainQuery){
		if(lodgerComplainQuery==null){
			lodgerComplainQuery = new LodgerComplainQuery();
		}
		if(StringUtil.isEmpty(lodgerComplainQuery.getSortSql())){
			lodgerComplainQuery.buildSortSql("order by a.create_date desc");
		}else{
			lodgerComplainQuery.buildSortSql(lodgerComplainQuery.getSortSql());
		}
		lodgerComplainQuery.buildPageSql();
		List<LodgerComplainVo> list = this.findList(lodgerComplainQuery);
		long totalCount = this.findCount(lodgerComplainQuery);
		Page<LodgerComplainVo> page = new Page<LodgerComplainVo>(lodgerComplainQuery.getPageNum(), lodgerComplainQuery.getPageSize(), totalCount, list);
		return page;
	}
	
}