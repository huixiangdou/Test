package com.test.moel;

/**
 * Created by Administrator on 2017/8/29.
 */

public class Channel {
    private String clssId;
    private String name;
    private String parentId;

    public Channel(String clssId, String name, String parentId) {
        this.clssId = clssId;
        this.name = name;
        this.parentId = parentId;
    }

    public String getClssId() {
        return clssId;
    }

    public void setClssId(String clssId) {
        this.clssId = clssId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "CaipuClass{" +
                "clssId='" + clssId + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}
