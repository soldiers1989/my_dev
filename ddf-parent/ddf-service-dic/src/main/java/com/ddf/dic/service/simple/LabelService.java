package com.ddf.dic.service.simple;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.dic.dao.LabelDao;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.constant.RedisKeyConstant;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.dic.dto.Label;
import com.ddf.entity.dic.dto.Xiaoqu;
import com.ddf.entity.dic.eo.LabelType;
import com.ddf.entity.dic.query.LabelQuery;
import com.ddf.entity.dic.vo.LabelVo;
import com.ddf.reference.cache.CacheReference;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * label Service
 * @author robot
 * @version 2018-01-16
 */
@Service
public class LabelService extends CrudServiceImpl<Label,LabelVo,LabelQuery>{
	@Autowired
	private LabelDao labelDao;
	@Autowired
	private IdReference idReference;
	@Autowired
	private DateReference dateReference;
	@Autowired
	private CacheReference cacheReference;
	public boolean create(Label entity) {
		entity.setId(idReference.createId(TableName.label).getData());
		entity.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(entity);
	}
	public boolean modify(Label entity) {
		entity.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(entity);
	}
	public Page<LabelVo> query4page(int pageNum, int pageSize){
		LabelQuery labelQuery = new LabelQuery();
		labelQuery.buildPageSql(pageNum, pageSize);
		List<LabelVo> list = this.findList(labelQuery);
		long totalCount = this.findCount(labelQuery);
		Page<LabelVo> page = new Page<LabelVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	public Page<LabelVo> query4pagehelper(LabelQuery query){
		query.buildPageSql();
		List<LabelVo> list = this.findList(query);
		long totalCount = this.findCount(query);
		Page<LabelVo> page = new Page<LabelVo>(query.getPageNum(), query.getPageSize(), totalCount, list);
		return page;
	}

	public Page<LabelVo> query4pagehelper(int pageNum, int pageSize){
		LabelQuery labelQuery = new LabelQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<LabelVo> list = this.findList(labelQuery);
        Page<LabelVo> page = new Page<LabelVo>(list);
        return page;
	}

	public List<LabelVo> findListByType(LabelType type) {
		Gson gson = new Gson();
		String data = cacheReference.get(RedisKeyConstant.DIC_LABEL_KEY +"findListByType"+ type).getData();
		if (!StringUtil.isEmpty(data)){
			return gson.fromJson(data,new TypeToken<List<LabelVo>>(){}.getType());
		}else {
			LabelQuery query = new LabelQuery();
			query.getLabel().setType(type);
			query.buildSortSql("order by a.sort");
			List<LabelVo> labelVos = super.findList(query);
			cacheReference.put(RedisKeyConstant.DIC_LABEL_KEY +"findListByType"+ type,gson.toJson(labelVos));
			return labelVos;
		}

	}
}