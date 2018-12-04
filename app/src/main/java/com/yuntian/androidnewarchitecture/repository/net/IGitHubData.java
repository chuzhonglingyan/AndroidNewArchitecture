package com.yuntian.androidnewarchitecture.repository.net;

import com.yuntian.androidnewarchitecture.bean.Repo;
import com.yuntian.baselibs.lifecycle.BaseResultLiveData;

import java.util.List;

public interface IGitHubData {


    BaseResultLiveData<List<Repo>> getRepoList(String userId);


}
