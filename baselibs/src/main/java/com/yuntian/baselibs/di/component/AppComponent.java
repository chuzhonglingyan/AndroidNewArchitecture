package com.yuntian.baselibs.di.component;

import android.app.Application;

import com.google.gson.Gson;
import com.yuntian.baselibs.di.module.AppModule;
import com.yuntian.baselibs.di.module.HttpModule;

import javax.inject.Singleton;


import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/1/3.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    Application application();

    Gson gson();

    OkHttpClient getClient();

    Retrofit getRetrofit();

}
