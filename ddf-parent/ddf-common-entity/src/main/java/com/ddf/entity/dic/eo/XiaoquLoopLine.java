package com.ddf.entity.dic.eo;

public enum XiaoquLoopLine {
    // 环线（inner_ring.内环内、inner_middle_ring.内环至中环、middle_outer_ring.中环至外环、outer_ring.外环外
    inner_ring("内环内"),
    inner_middle_ring("内环至中环"),
    middle_outer_ring("中环至外环"),
    outer_ring("外环外");

    private String explain;

    private XiaoquLoopLine(String explain){
        this.explain=explain;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

}
