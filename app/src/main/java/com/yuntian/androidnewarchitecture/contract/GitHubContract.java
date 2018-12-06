package com.yuntian.androidnewarchitecture.contract;

import com.yuntian.androidnewarchitecture.bean.Repo;
import com.yuntian.baselibs.lifecycle.BaseResultLiveData;

import java.util.List;

public interface GitHubContract {


    BaseResultLiveData<List<Repo>> getRepoList(String userId);


}
