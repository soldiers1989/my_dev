package com.ddf.dic.service.simple;

import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.dic.dto.MetroLine;
import com.ddf.reference.cache.CacheReference;
import com.ddf.entity.constant.RedisKeyConstant;
import com.ddf.entity.dic.vo.MetroLineVo;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.dic.dao.MetroStationDao;
import com.ddf.entity.dic.dto.MetroStation;
import com.ddf.entity.dic.query.MetroStationQuery;
import com.ddf.entity.dic.vo.MetroStationVo;

import java.util.List;

/**
 * metro_station Service
 * @author robot
 * @version 2018-01-08
 */
@Service
public class MetroStationService extends CrudServiceImpl<MetroStation,MetroStationVo,MetroStationQuery>{
	@Autowired
	private MetroStationDao metroStationDao;
	@Autowired
	private IdReference idReference;
	@Autowired
	private DateReference dateReference;
	@Autowired
	private CacheReference cacheReference;

	public boolean create(MetroStation entity) {
		entity.setId(idReference.createId(TableName.metro_line).getData());
		entity.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(entity);
	}
	public boolean modify(MetroStation entity) {
		entity.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(entity);
	}
	public List<MetroStationVo> findList4metroLineId(String metroLineId) {
		Gson gson = new Gson();
		String data = cacheReference.get(RedisKeyConstant.DIC_METRO_STATION_KEY +"findList4metroLineId"+ metroLineId).getData();
		if (!StringUtil.isEmpty(data)){
			return gson.fromJson(data,new TypeToken<List<MetroStationVo>>(){}.getType());
		}else {
			MetroStationQuery query = new MetroStationQuery();
			MetroStation metroStation = new MetroStation();
			metroStation.setMetroLineId(metroLineId);
			query.setMetroStation(metroStation);
			List<MetroStationVo> list = super.findList(query);
			cacheReference.put(RedisKeyConstant.DIC_METRO_STATION_KEY +"findList4metroLineId"+metroLineId,gson.toJson(list));
			return list;
		}

	}
}