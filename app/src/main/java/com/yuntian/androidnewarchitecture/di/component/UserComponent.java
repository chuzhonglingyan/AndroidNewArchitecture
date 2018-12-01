package com.yuntian.androidnewarchitecture.di.component;

import com.yuntian.androidnewarchitecture.di.module.HttpModule;
import com.yuntian.androidnewarchitecture.di.module.UserModule;
import com.yuntian.androidnewarchitecture.ui.UserProfileFragmentA;
import com.yuntian.androidnewarchitecture.ui.UserProfileFragmentB;

import dagger.Component;

@Component(modules = {UserModule.class, HttpModule.class})
public interface UserComponent {

    void inject(UserProfileFragmentA fragment);


    void inject(UserProfileFragmentB fragment);
}
