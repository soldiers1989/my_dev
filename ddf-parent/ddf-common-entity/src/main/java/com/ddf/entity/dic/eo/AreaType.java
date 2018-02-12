package com.ddf.entity.dic.eo;

public enum AreaType {
    COUNTRY("国家","1"),
    PROVINCE("省","2"),
    CITY("城市","3"),
    DISTRICT("大区","4"),
    CIRCLE("板块","5");

    private String explain;
    private String value;

    AreaType(String explain,String value){
        this.explain=explain;
        this.value=value;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        System.out.println(AreaType.COUNTRY.getValue());
    }
}
