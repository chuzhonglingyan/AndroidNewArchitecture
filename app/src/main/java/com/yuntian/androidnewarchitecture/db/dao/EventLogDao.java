package com.yuntian.androidnewarchitecture.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.yuntian.androidnewarchitecture.db.entity.EventLog;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface EventLogDao {


    @Query("SELECT * FROM eventlog")
    Flowable<List<EventLog>> getAll();

}

