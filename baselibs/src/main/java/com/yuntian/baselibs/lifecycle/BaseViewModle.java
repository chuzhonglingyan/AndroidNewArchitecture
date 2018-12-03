package com.yuntian.baselibs.lifecycle;

import android.arch.lifecycle.ViewModel;

import com.blankj.utilcode.util.LogUtils;

public abstract class BaseViewModle<T> extends ViewModel {


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
        LogUtils.d("onCleared");
    }
}
