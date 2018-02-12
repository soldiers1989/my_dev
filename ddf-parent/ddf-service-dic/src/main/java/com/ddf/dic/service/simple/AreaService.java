package com.ddf.dic.service.simple;

import com.ddf.entity.constant.RedisKeyConstant;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.cache.CacheReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.JsonUtil;
import com.ddf.util.ListUtil;
import com.ddf.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.dic.dto.Area;
import com.ddf.entity.dic.query.AreaQuery;
import com.ddf.entity.dic.vo.AreaVo;
import com.ddf.dic.dao.AreaDao;

import java.util.List;

/**
 * area Service
 * @author robot
 * @version 2018-01-10
 */
@Service
public class AreaService extends CrudServiceImpl<Area,AreaVo,AreaQuery>{
	@Autowired
	private AreaDao areaDao;
	@Autowired
	private CacheReference cacheReference;
	@Autowired
	private IdReference idReference;
	/**
	 * 查询Area所有的上级对象,范围从大到小排列
	 * @param id 主键
	 * @return
	 * @throws Exception
	 */
	public List<AreaVo> queryParents(String id){
		Gson gson = new Gson();
		String data = cacheReference.get(RedisKeyConstant.DIC_AREA_KEY +"queryParents"+ id).getData();
		if (!StringUtil.isEmpty(data)){
			return gson.fromJson(data,new TypeToken<List<AreaVo>>(){}.getType());
		}else {
			Area area = areaDao.query4id(id);
			if (area ==null) return null;
			String parentIds = area.getParentIds();
			String[] ids = getStrings(parentIds);
			if (ids == null) return null;
			List<AreaVo> listByParents = areaDao.findListByParents(ids);
//			cacheReference.put(RedisKeyConstant.DIC_AREA_KEY + "queryParents" + id,gson.toJson(listByParents));
			return listByParents;
		}

	}
	/**
	 * 查询Area的父对象
	 * @param id 主键
	 * @return
	 * @throws Exception
	 */
	public Area queryParent(String id){
//		String data1 = idReference.createId(TableName.real_name).getData();
		String data = cacheReference.get(RedisKeyConstant.DIC_AREA_KEY + id).getData();
		if (!StringUtil.isEmpty(data)){
			return JsonUtil.toBean(data,Area.class);
		}else {
			Area area = areaDao.query4id(id);
			if (area ==null) return null;
			Area area1 = areaDao.query4id(area.getParentId());
//			cacheReference.put(RedisKeyConstant.DIC_AREA_KEY+id,JsonUtil.toJson(area1));
			return area1;
		}
	}

	/**
	 * 查询Area子列表
	 * @param id 主键
	 * @return
	 */
	public List<AreaVo> queryChilds(String id) {
		Gson gson = new Gson();
		cacheReference.remove4begin(RedisKeyConstant.DIC_AREA_KEY +"queryChilds");
		String data = cacheReference.get(RedisKeyConstant.DIC_AREA_KEY +"queryChilds"+ id).getData();
		if (!StringUtil.isEmpty(data)){
			return gson.fromJson(data,new TypeToken<List<AreaVo>>(){}.getType());
		}else {
			AreaQuery query = new AreaQuery();
			query.getArea().setParentId(id);
//			query.buildPageSql(1,2);
			List<AreaVo> list = areaDao.findList(query);
//			cacheReference.put(RedisKeyConstant.DIC_AREA_KEY + "queryChilds" + id,gson.toJson(list));
			return list;
		}

	}

	/**
	 * 将包含逗号的字符串 转化为 String[]
	 * @param parentIds
	 * @return
	 */
	private String[] getStrings(String parentIds) {
		if (StringUtil.isEmpty(parentIds)) return null;
		int i = parentIds.lastIndexOf(",");
		int length = parentIds.length()-1;
		if (length == i){
			parentIds = parentIds.substring(0,parentIds.length()-2);
		}
		String[] ids = parentIds.split("\\,");
		return ids;
	}

	public List<AreaVo> queryListByType(String type) {
		AreaQuery query = new AreaQuery();
		query.getArea().setType(type);
		return findList(query);
	}
}