package com.ddf.capital.alipay.api;

import java.util.List;

import com.ddf.capital.alipay.dto.BatchTransUser;

public interface AlipayBatchTransApi {
	String batchTransRequest(List<BatchTransUser> batchTransUsers,String batchNo,String notifyUrl);
}
