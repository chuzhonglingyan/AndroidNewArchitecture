package com.yuntian.androidnewarchitecture.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

public class BaseEntity {

    @PrimaryKey(autoGenerate = true)//主键是否自动增长，默认为false
    private int id;

    @ColumnInfo(name = "create_time")
    private Date createTime;

    @ColumnInfo(name = "update_time")
    private Date updateTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

