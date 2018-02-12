package com.ddf.sms.util;

public enum AliMsgTemplate {
	verification_code("SMS_112755007", "验证码code，您正在进行身份验证，打死不要告诉别人哦！"),
	verification_content("SMS_112755006", "code");
	private String cid;
	private String content;

	private AliMsgTemplate(String cid,String content) {
		this.cid = cid;
		this.content = content;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static void main(String[] args) {
	}

}
