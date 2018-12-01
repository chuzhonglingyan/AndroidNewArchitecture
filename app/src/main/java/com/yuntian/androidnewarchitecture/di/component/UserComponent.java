package com.yuntian.androidnewarchitecture.di.component;

import com.yuntian.androidnewarchitecture.di.module.ApiServiceModule;
import com.yuntian.androidnewarchitecture.di.module.UserModule;
import com.yuntian.androidnewarchitecture.repository.UserRepository;
import com.yuntian.androidnewarchitecture.ui.UserProfileFragmentA;
import com.yuntian.androidnewarchitecture.ui.UserProfileFragmentB;
import com.yuntian.baselibs.di.component.AppComponent;
import com.yuntian.baselibs.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {ApiServiceModule.class,UserModule.class })
public interface UserComponent {

    void inject(UserProfileFragmentA fragment);

    void inject(UserProfileFragmentB fragment);

}
