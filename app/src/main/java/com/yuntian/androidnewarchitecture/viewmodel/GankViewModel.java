package com.yuntian.androidnewarchitecture.viewmodel;

import com.yuntian.androidnewarchitecture.bean.GankInfo;
import com.yuntian.androidnewarchitecture.repository.GankRepository;
import com.yuntian.androidnewarchitecture.contract.GankContract;
import com.yuntian.baselibs.lifecycle.BaseResultLiveData;
import com.yuntian.baselibs.lifecycle.BaseViewModle;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;


public class GankViewModel extends BaseViewModle<GankRepository> implements GankContract {


    @Override
    public BaseResultLiveData<List<GankInfo>> getGankInfoList(String dataType, int page) {
        return repo.getGankInfoList(dataType, page);
    }


    public LiveData<PagedList<GankInfo>> getGankInfoPageList(String dataType) {
        return repo.getGankInfoPageList(dataType);
    }
}
