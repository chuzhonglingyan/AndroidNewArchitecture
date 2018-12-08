package com.yuntian.baselibs.base;

import android.app.Application;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ProcessUtils;
import com.blankj.utilcode.util.Utils;
import com.yuntian.baselibs.di.component.AppComponent;
import com.yuntian.baselibs.di.component.DaggerAppComponent;
import com.yuntian.baselibs.di.module.AppModule;

public abstract class BaseApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    public void init() {
        if (ProcessUtils.isMainProcess()) {
            Utils.init(this);
            appComponent = DaggerAppComponent.builder() //DaggerAppComponent具体实现类
                    .appModule(new AppModule(this))
                    .build();
            initMain();
        }
    }

   protected abstract  void  initMain();


    private AppComponent appComponent;


    public AppComponent component() {
        return appComponent;
    }


}
