package com.yuntian.androidnewarchitecture.di.module;

import com.yuntian.androidnewarchitecture.net.service.Webservice;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiServiceModule {

    @Provides
    public Webservice provideWebservice(Retrofit retrofit) {
        return retrofit.create(Webservice.class);
    }

}
