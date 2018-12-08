package com.yuntian.androidnewarchitecture.db.dao;


import com.yuntian.androidnewarchitecture.db.entity.EventLog;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import io.reactivex.Flowable;

@Dao
public interface EventLogDao {


    @Query("SELECT * FROM eventlog")
    Flowable<List<EventLog>> getAll();

}

