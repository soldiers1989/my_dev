package com.ddf.dic.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.dic.query.XiaoquQuery;
import com.ddf.entity.dic.vo.XiaoquVo;
import com.ddf.entity.dic.dto.Xiaoqu;

import java.util.List;

/**
 * xiaoqu DAO接口
 * @author robot
 * @version 2018-01-10
 */
public interface XiaoquDao extends CrudDao<Xiaoqu,XiaoquVo,XiaoquQuery> {
    List<XiaoquVo> findList4like(XiaoquQuery query);
    long findCount4like(XiaoquQuery query);
    List<XiaoquVo> findXiaoquByLngLat(XiaoquQuery query);
}