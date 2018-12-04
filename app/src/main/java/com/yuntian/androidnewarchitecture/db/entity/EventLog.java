package com.yuntian.androidnewarchitecture.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity
public class EventLog extends BaseEntity {

    @ColumnInfo(name = "event_name")
    private String eventName;

    @ColumnInfo(name = "event_type")
    private int eventType;

    @ColumnInfo(name = "event_ower")
    private String eventOwer;

    @ColumnInfo(name = "event_code")
    private int eventCode;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getEventOwer() {
        return eventOwer;
    }

    public void setEventOwer(String eventOwer) {
        this.eventOwer = eventOwer;
    }

    public int getEventCode() {
        return eventCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }
}
