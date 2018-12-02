package com.yuntian.androidnewarchitecture.di.component;

import com.yuntian.androidnewarchitecture.di.module.ApiServiceModule;
import com.yuntian.androidnewarchitecture.di.module.UserModule;
import com.yuntian.baselibs.di.component.AppComponent;
import com.yuntian.baselibs.di.scope.ActivityScope;

import dagger.Component;

/**
 * 混合多个业务请求Module
 */
@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {ApiServiceModule.class,UserModule.class })
public interface MixtureComponent {


}
