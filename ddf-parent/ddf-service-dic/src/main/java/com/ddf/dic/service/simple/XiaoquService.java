package com.ddf.dic.service.simple;

import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.dic.query.LabelQuery;
import com.ddf.entity.dic.vo.LabelVo;
import com.ddf.reference.cache.CacheReference;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.dic.dto.Xiaoqu;
import com.ddf.entity.dic.query.XiaoquQuery;
import com.ddf.entity.dic.vo.XiaoquVo;
import com.ddf.dic.dao.XiaoquDao;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * xiaoqu Service
 * @author robot
 * @version 2018-01-10
 */
@Service
public class XiaoquService extends CrudServiceImpl<Xiaoqu,XiaoquVo,XiaoquQuery>{
	@Autowired
	private XiaoquDao xiaoquDao;
    @Autowired
    private IdReference idReference;
    @Autowired
    private DateReference dateReference;
    @Autowired
    private CacheReference cacheReference;

    public boolean create(Xiaoqu entity) {
        entity.setId(idReference.createId(TableName.xiaoqu).getData());
        entity.setCreateDate(dateReference.queryCurrentDate().getData());
        return dao.create(entity);
    }
    public boolean modify(Xiaoqu entity) {
        entity.setUpdateDate(dateReference.queryCurrentDate().getData());
        return dao.modify(entity);
    }
    public List<XiaoquVo> findList4where(XiaoquQuery xiaoquQuery) {
        xiaoquQuery.buildPageSql(xiaoquQuery.getPageNum(),xiaoquQuery.getPageSize());
        return super.findList(xiaoquQuery);
    }
    public Page<XiaoquVo> query4page(int pageNum, int pageSize){
        XiaoquQuery xiaoquQuery = new XiaoquQuery();
        xiaoquQuery.buildPageSql(pageNum, pageSize);
        List<XiaoquVo> list = this.findList(xiaoquQuery);
        long totalCount = this.findCount(xiaoquQuery);
        Page<XiaoquVo> page = new Page<XiaoquVo>(pageNum, pageSize, totalCount, list);
        return page;
    }
    public Page<XiaoquVo> query4pagehelper(XiaoquQuery query){
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<XiaoquVo> list = this.findList(query);
        Page<XiaoquVo> page = new Page<XiaoquVo>(list);
        return page;
    }

    /**
     * 模糊查询
     * @return
     */
    public List<XiaoquVo> findList4likeNameOrAddress(XiaoquQuery xiaoquQuery) {
        xiaoquQuery.buildPageSql(xiaoquQuery.getPageNum(),xiaoquQuery.getPageSize());
        return xiaoquDao.findList4like(xiaoquQuery);
    }

    public List<XiaoquVo> findXiaoquByLngLat(Integer pageNum, Integer pageSize, String lng, String lat) {
        XiaoquQuery query = new XiaoquQuery();
        query.buildPageSql(pageNum,pageSize);
        query.getXiaoqu().setLng(lng);
        query.getXiaoqu().setLat(lat);
        return xiaoquDao.findXiaoquByLngLat(query);
    }

    public long findXiaoquNum() {
        XiaoquQuery query = new XiaoquQuery();
        return xiaoquDao.findCount(query);
    }
}