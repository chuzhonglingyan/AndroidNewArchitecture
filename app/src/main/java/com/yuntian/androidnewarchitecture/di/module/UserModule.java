package com.yuntian.androidnewarchitecture.di.module;

import com.yuntian.androidnewarchitecture.net.service.GankService;
import com.yuntian.androidnewarchitecture.net.service.GitHubservice;
import com.yuntian.androidnewarchitecture.repository.net.GankRepository;
import com.yuntian.androidnewarchitecture.repository.net.GitHubRepository;
import com.yuntian.androidnewarchitecture.viewmodel.GankViewModel;
import com.yuntian.androidnewarchitecture.viewmodel.GitHubViewModel;
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
    public GitHubRepository provideGitUserRepository(GitHubservice service) {
        return new GitHubRepository(service);
    }

    @Provides
    public GankRepository provideGankRepository(GankService service) {
        return new GankRepository(service);
    }


    @Provides
    public GitHubViewModel provideGitHubViewModel(GitHubRepository  repository) {
        GitHubViewModel viewModule=IViewUtil.getViewModule(iView,GitHubViewModel.class);
        viewModule.setRepo(repository);
        return viewModule;
    }



    @Provides
    public GankViewModel provideGankViewModel(GankRepository  repository) {
        GankViewModel viewModule=IViewUtil.getViewModule(iView,GankViewModel.class);
        viewModule.setRepo(repository);
        return viewModule;
    }


}
