package com.yuntian.androidnewarchitecture.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.blankj.utilcode.util.LogUtils;

public class CommunicateViewModel extends ViewModel {

    private static final String TAG = "CommunicateViewModel";
    private MutableLiveData<String> mNameLiveData;

    public LiveData<String> getName() {
        if (mNameLiveData == null) {
            mNameLiveData = new MutableLiveData<>();
        }
        return mNameLiveData;
    }

    public void setName(String name) {
        if (mNameLiveData != null) {
            LogUtils.d(TAG, "setName: " + name);
            mNameLiveData.setValue(name);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mNameLiveData = null;
        LogUtils.d(TAG, "onCleared");
    }

}
