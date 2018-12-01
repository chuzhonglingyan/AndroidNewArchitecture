package com.yuntian.androidnewarchitecture.repository;

import com.yuntian.androidnewarchitecture.base.data.BaseResultLiveData;
import com.yuntian.androidnewarchitecture.bean.Repo;

import java.util.List;

public interface IUserData {


    BaseResultLiveData<List<Repo>> getRepoList(String userId);


}
