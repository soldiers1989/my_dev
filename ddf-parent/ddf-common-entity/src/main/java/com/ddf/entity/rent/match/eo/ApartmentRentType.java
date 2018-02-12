package com.ddf.entity.rent.match.eo;

public enum ApartmentRentType {
    /**
     * 枚举类型，租房类型（整租whole_rent，合租share_rent）
     */
    whole_rent("整租"),
    share_rent("合租");

    private String explain;

    private ApartmentRentType(String explain) {
        this.explain = explain;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
