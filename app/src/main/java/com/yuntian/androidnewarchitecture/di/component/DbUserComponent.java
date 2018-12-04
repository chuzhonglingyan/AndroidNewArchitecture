package com.yuntian.androidnewarchitecture.di.component;

import com.yuntian.androidnewarchitecture.di.module.DbUserModule;
import com.yuntian.androidnewarchitecture.ui.DbTestActivity;
import com.yuntian.baselibs.di.component.AppComponent;
import com.yuntian.baselibs.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {DbUserModule.class })
public interface DbUserComponent {

    void inject(DbTestActivity activity);

}
