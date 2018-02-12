package com.ddf.reference.member;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ddf.entity.member.dto.BankCard;
import com.ddf.entity.member.vo.BankCardVo;
import com.ddf.entity.response.ApiResponse;

@Component
public class BankCardReferenceFallback implements BankCardReference {

	@Override
	public ApiResponse<BankCard> query(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> create(BankCard bankCard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> modify(BankCard bankCard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<List<BankCardVo>> query4user(String currentUserId) {
		// TODO Auto-generated method stub
		return null;
	}

}
