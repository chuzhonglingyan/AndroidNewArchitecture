package com.yuntian.androidnewarchitecture.di.component;

import com.yuntian.androidnewarchitecture.di.module.DbUserModule;
import com.yuntian.androidnewarchitecture.ui.activity.DbTestActivity;
import com.yuntian.androidnewarchitecture.ui.activity.ListActivity;
import com.yuntian.baselibs.di.component.AppComponent;
import com.yuntian.baselibs.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {DbUserModule.class })
public interface DbListComponent {

    void inject(ListActivity activity);

}
