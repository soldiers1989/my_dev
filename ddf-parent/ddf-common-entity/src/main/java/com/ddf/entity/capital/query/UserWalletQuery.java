package com.ddf.entity.capital.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.capital.dto.UserWallet;

/**
 * user_wallet EntityQuery
 * @author robot
 * @version 2018-01-22
 */
public class UserWalletQuery extends Query {

	private static final long serialVersionUID = 1L;

	public UserWalletQuery(){
		this.userWallet = new UserWallet();
	}
	
	private UserWallet userWallet;

	public UserWallet getUserWallet() {
		return userWallet;
	}

	public void setUserWallet(UserWallet userWallet) {
		this.userWallet = userWallet;
	}

}