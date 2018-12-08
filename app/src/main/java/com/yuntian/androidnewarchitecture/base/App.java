package com.yuntian.androidnewarchitecture.base;


import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.yuntian.androidnewarchitecture.db.AppDatabase;
import com.yuntian.androidnewarchitecture.work.PhotoCheckWorker;
import com.yuntian.baselibs.base.BaseApp;
import com.yuntian.baselibs.work.WorkManagerUtil;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import androidx.multidex.MultiDex;
import androidx.work.Data;
import androidx.work.WorkRequest;

/**
 * @author chulingyan
 * @time 2018/12/5 21:59
 * @describe 程序入口
 */
public class App extends BaseApp {

    private static AppDatabase appDatabase;

    @Override
    protected void initMain() {
        appDatabase = AppDatabase.getInstance(this);
        LogUtils.d("App启动了");
        startBackgoundTask();
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }


    /**
     * 开启后台任务
     */
    public WorkRequest startBackgoundTask() {
        Data myData = new Data.Builder()
                .putLong(PhotoCheckWorker.KEY_X_ARG, 42)
                .putLong(PhotoCheckWorker.KEY_Y_ARG, 421)
                .putLong(PhotoCheckWorker.KEY_Z_ARG, 5666)
                .build();
        WorkRequest workRequest = WorkManagerUtil.startPeriodicWorkRequest(PhotoCheckWorker.class, myData, 10, TimeUnit.SECONDS);
        photoCheckRequestWorkeId = workRequest.getId();
        return workRequest;
    }

    private static UUID photoCheckRequestWorkeId;

    public static UUID getPhotoCheckRequestWorkeId() {
        return photoCheckRequestWorkeId;
    }


    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

}
