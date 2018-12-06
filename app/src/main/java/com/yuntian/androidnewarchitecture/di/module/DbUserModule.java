package com.yuntian.androidnewarchitecture.di.module;

import com.yuntian.androidnewarchitecture.base.App;
import com.yuntian.androidnewarchitecture.repository.DbUserRepository;
import com.yuntian.androidnewarchitecture.viewmodel.DbViewModel;
import com.yuntian.baselibs.base.IView;
import com.yuntian.baselibs.base.IViewUtil;

import dagger.Module;
import dagger.Provides;

/**
 * 实例化工厂
 */
@Module
public class DbUserModule {


    private IView iView;

    public DbUserModule(IView iView) {
        this.iView = iView;
    }

    @Provides
    public DbUserRepository provideDbUserRepository() {
        return new DbUserRepository(App.getAppDatabase().userDao());
    }

    @Provides
    public DbViewModel provideDbViewModel(DbUserRepository repository) {
        DbViewModel viewModule=IViewUtil.getViewModule(iView,DbViewModel.class);
        viewModule.setRepo(repository);
        return viewModule;
    }


}
