package com.ddf.admin.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ddf.admin.common.domain.LogDO;
import com.ddf.admin.common.domain.PageDO;
import com.ddf.admin.common.utils.Query;
@Service
public interface LogService {
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
