package com.yuntian.androidnewarchitecture.viewmodel;


import com.blankj.utilcode.util.LogUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
