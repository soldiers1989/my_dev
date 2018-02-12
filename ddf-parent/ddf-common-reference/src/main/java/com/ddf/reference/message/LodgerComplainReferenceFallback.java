package com.ddf.reference.message;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.LodgerComplain;
import com.ddf.entity.message.query.LodgerComplainQuery;
import com.ddf.entity.message.vo.LodgerComplainVo;
import com.ddf.entity.response.ApiResponse;

@Component
public class LodgerComplainReferenceFallback implements LodgerComplainReference {

	@Override
	public ApiResponse<LodgerComplain> query(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> create(LodgerComplain lodgerComplain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> modify(LodgerComplain lodgerComplain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<LodgerComplainVo>> query4lodgerId(String lodgerId,
			int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<LodgerComplainVo>> batchquery(
			LodgerComplainQuery lodgerComplainQuery) {
		// TODO Auto-generated method stub
		return null;
	}

}
