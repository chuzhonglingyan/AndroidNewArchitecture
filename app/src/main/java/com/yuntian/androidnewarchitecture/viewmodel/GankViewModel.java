package com.yuntian.androidnewarchitecture.viewmodel;

import com.yuntian.androidnewarchitecture.bean.GankInfo;
import com.yuntian.androidnewarchitecture.repository.net.GankRepository;
import com.yuntian.androidnewarchitecture.repository.net.IGankData;
import com.yuntian.baselibs.lifecycle.BaseResultLiveData;
import com.yuntian.baselibs.lifecycle.BaseViewModle;

import java.util.List;


public class GankViewModel extends BaseViewModle<GankRepository> implements IGankData{



    @Override
    public BaseResultLiveData<List<GankInfo>> getGankInfoList(String dataType, int page) {
        return repo.getGankInfoList(dataType,page);
    }



}
