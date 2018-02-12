package com.ddf.entity.db.eo;

public enum TableName {
	
	student("学生表","s"),
	student_score("成绩表","ss"),
	
	/*ddf-message数据库**/
	call_record("通话记录","cr"),
	chat_record("聊天记录","cr"),
	house_opinion("房源评价表","ho"),
	landlord_complain("房客投诉房东表","lc"),
	landlord_opinion("房客评价房东表","lo"),
	lodger_complain("房东投诉房客表","lc"),
	lodger_opinion("房东评价房客表","lo"),
	message_task("消息库任务表","mt"),
	mobile_message("手机短信","mm"),
	sys_message("消息通知表","sm"),
	sys_message_admin("发送消息管理表","sma"),
	
	/*ddf_sms数据库*/
	short_message("手机短信表","sms"),
	sms_param("短信库系统参数配置","sp"),
	
	
	/*ddf-member数据库**/
	user("用户表","u"),
	alipay_zhima_credit("芝麻信用","azc"),
	bank_card("用户银行卡","bc"),
	real_name("实名认证","rn"),
	
	
	/*ddf-dic数据库**/
	area("区域表","area"),
	label("标签表","label"),
	metro_line("地铁线","ml"),
	metro_station("地铁站","ms"),
	xiaoqu("小区","xiaoqu"),
	
	
	
	/*ddf-rent-match数据库**/
	house_match_record("匹配到的房源","hmr"),
	rent_demand_match_record("匹配到的需求","rdmr"),
	
	/*ddf-rent数据库**/
	apartment("整租房源表","a"),
	share_house("合租房源表","sh"),
	share_apartment("合租房间表","sa"),
	apartment_appointment("房源预约表","aa"),
	apartment_deposit_contract("房源合同表","adc"),
	rent_param("租房参数表","rp"),
	rent_demand("租房需求表","rd"),
	apartment_mark("房源收藏表","am"),
	month_rent_amount_city("租金按城市统计","mrac"),
	month_rent_amount_district("租金按大区统计","mrad"),
	month_rent_amount_circle("租金按商圈统计","mrac"),
	month_rent_amount_xiaoqu("租金按小区统计","mrax"),
	/*ddf-capital数据库**/
	bond_order("保证金订单表","bo"),
	bond_order_refund_apply("退保证金申请表","bora"),
	withdraw_order("提现订单","wo"),
	recharge_order("充值订单","ro"),
	deposit_order("定金订单","do"),
	bill("资金明细","wo"),
	user_wallet("我的钱包","uw"),
	withdraw_apply("提现申请","wa"),
	withdraw_apply_batch("提现申请批次","wab"),
	ali_withdraw_apply_batch("提现申请批次","awab"),
	;
	
	private String explain;
	private String shortName;

	private TableName(String explain,String shortName) {
		this.explain = explain;
		this.shortName = shortName;
	}
	
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}


	



}