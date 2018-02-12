package com.ddf.entity.message.query;

import java.util.Date;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.message.dto.LodgerComplain;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * lodger_complain EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class LodgerComplainQuery extends Query {

	private static final long serialVersionUID = 1L;

	public LodgerComplainQuery(){
		this.lodgerComplain = new LodgerComplain();
	}
	
	private LodgerComplain lodgerComplain;

	public LodgerComplain getLodgerComplain() {
		return lodgerComplain;
	}

	public void setLodgerComplain(LodgerComplain lodgerComplain) {
		this.lodgerComplain = lodgerComplain;
	}

	private Date startCreateDate;
	private Date endCreateDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartCreateDate() {
		return startCreateDate;
	}

	public void setStartCreateDate(Date startCreateDate) {
		this.startCreateDate = startCreateDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

}