package com.yuntian.androidnewarchitecture.contract;

import com.yuntian.androidnewarchitecture.bean.GankInfo;
import com.yuntian.baselibs.lifecycle.BaseResultLiveData;

import java.util.List;

public interface GankContract {


    BaseResultLiveData<List<GankInfo>> getGankInfoList(String dataType,int page);

}
