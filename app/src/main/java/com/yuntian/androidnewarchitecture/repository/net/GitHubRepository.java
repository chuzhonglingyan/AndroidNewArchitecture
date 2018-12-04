package com.yuntian.androidnewarchitecture.repository.net;

import android.util.Log;

import com.yuntian.androidnewarchitecture.bean.Repo;
import com.yuntian.androidnewarchitecture.net.service.GitHubservice;
import com.yuntian.baselibs.data.BaseResult;
import com.yuntian.baselibs.lifecycle.BaseRepository;
import com.yuntian.baselibs.lifecycle.BaseResultLiveData;
import com.yuntian.baselibs.net.NetCallback;

import java.util.List;

import retrofit2.Call;

public class GitHubRepository extends BaseRepository<GitHubservice> implements IGitHubData {

    private static final String TAG = "GitHubRepository";

    public GitHubRepository(GitHubservice service) {
        super(service);
    }


    private BaseResultLiveData<List<Repo>> baseResultLiveData;


    @Override
    public BaseResultLiveData<List<Repo>> getRepoList(String userId) {
        if (baseResultLiveData == null) {
            baseResultLiveData = BaseResultLiveData.newIntance();
        }
        Call<List<Repo>> call = service.listRepos(userId);
        call.enqueue(new NetCallback<List<Repo>>() {
            @Override
            public void onResponse(BaseResult<List<Repo>> baseResult) {
                Log.d(TAG, "回调一次" + Thread.currentThread().getName());
                baseResultLiveData.setValue(baseResult);
            }
        });
        return baseResultLiveData;
    }


}
