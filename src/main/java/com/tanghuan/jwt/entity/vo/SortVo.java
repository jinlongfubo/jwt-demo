package com.tanghuan.jwt.entity.vo;

/**
 * Created by tanghuan on 2017/3/8.
 */

public class SortVo {

    /**
     * 排序的属性
     */
    private String property;

    /**
     * 排序的方向
     */
    private String direction;

    public SortVo() {
    }

    public SortVo(String property, String direction) {
        this.property = property;
        this.direction = direction;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

}
