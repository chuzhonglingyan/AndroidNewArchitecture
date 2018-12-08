package com.yuntian.baselibs.lifecycle;


import com.blankj.utilcode.util.LogUtils;

import androidx.lifecycle.ViewModel;

public abstract class BaseViewModle<T extends BaseRepository> extends ViewModel {


    protected T repo;


    public T getRepo() {
        return repo;
    }

    public void setRepo(T repo) {
        this.repo = repo;
    }

    @Override
    public void onCleared() {
        super.onCleared();
        repo.onCleared();
        LogUtils.d("onCleared");
    }
}
