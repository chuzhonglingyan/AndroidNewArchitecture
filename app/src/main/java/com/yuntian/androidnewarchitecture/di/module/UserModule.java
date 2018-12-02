package com.yuntian.androidnewarchitecture.di.module;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.yuntian.androidnewarchitecture.net.service.Webservice;
import com.yuntian.androidnewarchitecture.repository.UserRepository;
import com.yuntian.androidnewarchitecture.viewmodel.UserViewModel;
import com.yuntian.baselibs.base.IView;
import com.yuntian.baselibs.base.IViewUtil;

import dagger.Module;
import dagger.Provides;

/**
 * 实例化工厂
 */
@Module
public class UserModule {


    private IView iView;

    public UserModule(IView iView) {
        this.iView = iView;
    }


    @Provides
    public UserRepository provideUserRepository(Webservice webservice) {
        return new UserRepository(webservice);
    }


    @Provides
    public UserViewModel provideUserViewModel(UserRepository userRepository) {
        UserViewModel userViewModel=IViewUtil.getViewModule(iView,UserViewModel.class);
        userViewModel.setUserRepo(userRepository);
        return userViewModel;
    }


}
