package com.yuntian.baselibs.base;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.yuntian.baselibs.di.component.AppComponent;
import com.yuntian.baselibs.di.component.DaggerAppComponent;
import com.yuntian.baselibs.di.module.AppModule;

public class BaseApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        appComponent = DaggerAppComponent.builder() //DaggerAppComponent具体实现类
                .appModule(new AppModule(this))
                .build();
    }


    private AppComponent appComponent;


    public AppComponent component() {
        return appComponent;
    }


}
