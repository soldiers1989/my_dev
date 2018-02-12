package com.ddf.entity.dic.eo;

public enum LabelType {
	house("房源"),
	house_facility("房源设施"),
	house_by_opinion("评论房源"),
	landlord_by_opinion("评论房东"),
	lodger_by_opinion("评论租客"),
	life_style("生活方式"),
	about_individuals("关于个人"),
	hobby("兴趣爱好"),
	eating_habits("饮食习惯"),
	tenant_requirements("租客要求");

	private String explain;
	
	private LabelType(String explain){
		this.explain=explain;
	}
	
	public String getExplain() {
		return explain;
	}
	
	public void setExplain(String explain) {
		this.explain = explain;
	}
}
