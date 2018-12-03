package com.yuntian.androidnewarchitecture.repository;

import com.yuntian.androidnewarchitecture.bean.GankInfo;
import com.yuntian.androidnewarchitecture.bean.Repo;
import com.yuntian.androidnewarchitecture.db.entity.User;
import com.yuntian.baselibs.lifecycle.BaseResultLiveData;

import java.util.List;

import retrofit2.http.Path;

public interface IGankData {


    BaseResultLiveData<List<GankInfo>> getGankInfoList(String dataType,int page);


}
