package com.yuntian.androidnewarchitecture.base;

import com.facebook.stetho.Stetho;
import com.yuntian.androidnewarchitecture.db.AppDatabase;
import com.yuntian.baselibs.base.BaseApp;

public class App extends BaseApp {


    private static AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        appDatabase=AppDatabase.getInstance(this);
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

}
