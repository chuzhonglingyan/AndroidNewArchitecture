package com.yuntian.baselibs.lifecycle;

import android.arch.lifecycle.ViewModel;

import com.blankj.utilcode.util.LogUtils;

public abstract class BaseViewModle extends ViewModel {



    @Override
    public void onCleared() {
        super.onCleared();
        LogUtils.d("onCleared");
    }
}
