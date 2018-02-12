package com.ddf.message.service.simple;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.message.dto.ChatRecord;
import com.ddf.entity.message.query.ChatRecordQuery;
import com.ddf.entity.message.vo.ChatRecordVo;
import com.ddf.message.dao.ChatRecordDao;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * chat_record Service
 * @author robot
 * @version 2018-01-15
 */
@Service
public class ChatRecordService extends CrudServiceImpl<ChatRecord,ChatRecordVo,ChatRecordQuery>{
	@Autowired
	private ChatRecordDao chatRecordDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	public Page<ChatRecordVo> query4page(int pageNum, int pageSize){
		ChatRecordQuery chatRecordQuery = new ChatRecordQuery();
		chatRecordQuery.buildPageSql(pageNum, pageSize);
		List<ChatRecordVo> list = this.findList(chatRecordQuery);
		long totalCount = this.findCount(chatRecordQuery);
		Page<ChatRecordVo> page = new Page<ChatRecordVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	/**
	 * 分页查询
	 * @param chatRecordQuery
	 * @return
	 */
	public Page<ChatRecordVo> query4page(ChatRecordQuery chatRecordQuery){
		if(chatRecordQuery==null){
			chatRecordQuery = new ChatRecordQuery();
		}
		if(StringUtil.isEmpty(chatRecordQuery.getSortSql())){
			chatRecordQuery.buildSortSql("order by a.create_date desc");
		}else{
			chatRecordQuery.buildSortSql(chatRecordQuery.getSortSql());
		}
		chatRecordQuery.buildPageSql();
		List<ChatRecordVo> list = this.findList(chatRecordQuery);
		long totalCount = this.findCount(chatRecordQuery);
		Page<ChatRecordVo> page = new Page<ChatRecordVo>(chatRecordQuery.getPageNum(), chatRecordQuery.getPageSize(), totalCount, list);
		return page;
	}
	

	public Page<ChatRecordVo> query4pagehelper(int pageNum, int pageSize){
		ChatRecordQuery chatRecordQuery = new ChatRecordQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<ChatRecordVo> list = this.findList(chatRecordQuery);
        Page<ChatRecordVo> page = new Page<ChatRecordVo>(list);
        return page;
	}
	
	/**
	 * 添加
	 */
	public boolean create(ChatRecord chatRecord){
		chatRecord.setId(idReference.createId(TableName.chat_record).getData());
		chatRecord.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(chatRecord);
	}
	
	/**
	 * 修改
	 */
	public boolean modify(ChatRecord chatRecord){
		chatRecord.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(chatRecord);
	}
	
}