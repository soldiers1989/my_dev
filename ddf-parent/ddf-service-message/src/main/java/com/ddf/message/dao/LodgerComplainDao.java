package com.ddf.message.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.message.query.LodgerComplainQuery;
import com.ddf.entity.message.vo.LodgerComplainVo;
import com.ddf.entity.message.dto.LodgerComplain;

/**
 * lodger_complain DAO接口
 * @author robot
 * @version 2018-01-10
 */
public interface LodgerComplainDao extends CrudDao<LodgerComplain,LodgerComplainVo,LodgerComplainQuery> {
	
}