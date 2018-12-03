package com.yuntian.androidnewarchitecture.repository;

import com.yuntian.androidnewarchitecture.db.entity.User;
import com.yuntian.baselibs.lifecycle.BaseResultLiveData;
import com.yuntian.androidnewarchitecture.bean.Repo;

import java.util.List;

public interface IGitHubData {


    BaseResultLiveData<List<Repo>> getRepoList(String userId);

    BaseResultLiveData<List<User>> getUserList();

}
