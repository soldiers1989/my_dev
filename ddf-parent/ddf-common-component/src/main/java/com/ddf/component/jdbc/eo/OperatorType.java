package com.ddf.component.jdbc.eo;


public enum OperatorType {
	
	eq("=","等于"), 
	ne("!=","不等于"),
	gt(">","大于"),
	gte(">=","大于等于"),
	lt("<","小于"),
	lte("<=","小于等于"),
	like("like","模糊匹配"),
	blike("like","前缀模糊匹配"),
	elike("like","后缀模糊匹配"),
	notLike("not like","不匹配"),
	isNull("is null","空"),
	isNotNull("is not null","非空"),
	in("in","包含"),
	notIn("not in","不包含");
	
	private String symbol;
	private String explain;
	
	
	private OperatorType(String symbol,String explain) {
		this.symbol = symbol;
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	/** 
     * 操作符是否允许为空 
     *  
     * @param operator 
     * @return 
     */  
    public static boolean isAllowBlankValue(final OperatorType operator) {  
        return operator == OperatorType.isNotNull || operator == OperatorType.isNull;  
    }


}
