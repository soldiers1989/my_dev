package com.ddf.entity.sms.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.sms.dto.ShortMessage;

/**
 * short_message EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class ShortMessageQuery extends Query {

	private static final long serialVersionUID = 1L;

	public ShortMessageQuery(){
		this.shortMessage = new ShortMessage();
	}
	
	private ShortMessage shortMessage;

	public ShortMessage getShortMessage() {
		return shortMessage;
	}

	public void setShortMessage(ShortMessage shortMessage) {
		this.shortMessage = shortMessage;
	}

}