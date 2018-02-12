package com.ddf.dic.service.simple;

import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.dic.dto.Xiaoqu;
import com.ddf.reference.cache.CacheReference;
import com.ddf.entity.constant.RedisKeyConstant;
import com.ddf.entity.dic.vo.LabelVo;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.dic.dao.MetroLineDao;
import com.ddf.entity.dic.dto.MetroLine;
import com.ddf.entity.dic.query.MetroLineQuery;
import com.ddf.entity.dic.vo.MetroLineVo;

import java.util.List;

/**
 * metro_line Service
 * @author robot
 * @version 2018-01-08
 */
@Service
public class MetroLineService extends CrudServiceImpl<MetroLine,MetroLineVo,MetroLineQuery>{
	@Autowired
	private MetroLineDao metroLineDao;
    @Autowired
    private IdReference idReference;
    @Autowired
    private DateReference dateReference;
    @Autowired
    private CacheReference cacheReference;

    public boolean create(MetroLine entity) {
        entity.setId(idReference.createId(TableName.metro_line).getData());
        entity.setCreateDate(dateReference.queryCurrentDate().getData());
        return dao.create(entity);
    }
    public boolean modify(MetroLine entity) {
        entity.setUpdateDate(dateReference.queryCurrentDate().getData());
        return dao.modify(entity);
    }
    public List<MetroLineVo> findList4cityId(String cityId) {
        Gson gson = new Gson();
        String data = cacheReference.get(RedisKeyConstant.DIC_METRO_LINE_KEY +"findList4cityId"+ cityId).getData();
        if (!StringUtil.isEmpty(data)){
            return gson.fromJson(data,new TypeToken<List<MetroLineVo>>(){}.getType());
        }else {
            MetroLineQuery query = new MetroLineQuery();
            MetroLine metroLine = new MetroLine();
            metroLine.setCityId(cityId);
            query.setMetroLine(metroLine);
            List<MetroLineVo> metroLineVos = super.findList(query);
            cacheReference.put(RedisKeyConstant.DIC_METRO_LINE_KEY +"findList4cityId"+ cityId,gson.toJson(metroLineVos));
            return metroLineVos;
        }

    }
}