package com.ddf.admin.common.controller;

import com.ddf.admin.system.domain.UserToken;
import org.springframework.stereotype.Controller;
import com.ddf.admin.common.utils.ShiroUtils;
import com.ddf.admin.system.domain.UserDO;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}