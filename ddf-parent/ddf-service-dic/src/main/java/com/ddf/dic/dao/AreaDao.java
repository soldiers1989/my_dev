package com.ddf.dic.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.dic.query.AreaQuery;
import com.ddf.entity.dic.vo.AreaVo;
import com.ddf.entity.dic.dto.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * area DAO接口
 * @author robot
 * @version 2018-01-10
 */
public interface AreaDao extends CrudDao<Area,AreaVo,AreaQuery> {

    List<AreaVo> findListByParents(@Param("parentIds") String[] parentIds);
}