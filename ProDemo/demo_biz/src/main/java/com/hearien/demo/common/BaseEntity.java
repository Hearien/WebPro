package com.hearien.demo.common;

import java.io.Serializable;

/**
 * @ClassName BaseEntity
 * @Author WangHaiyang
 * @Date 2018/10/25 11:34
 **/
public class BaseEntity implements Serializable {

    private String id;

    private String createId;

    private String createTime;

    private String updateId;

    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
