package com.yuntian.androidnewarchitecture.repository;

import android.util.Log;

import com.blankj.utilcode.util.ThreadUtils;
import com.yuntian.androidnewarchitecture.base.App;
import com.yuntian.androidnewarchitecture.bean.Repo;
import com.yuntian.androidnewarchitecture.db.entity.User;
import com.yuntian.androidnewarchitecture.net.service.GitHubservice;
import com.yuntian.baselibs.data.BaseResult;
import com.yuntian.baselibs.data.BaseResultUtil;
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
        getRequestManager().add(call);
        return baseResultLiveData;
    }

    @Override
    public BaseResultLiveData<List<User>> getUserList() {
        BaseResultLiveData<List<User>> baseResultLiveData=BaseResultLiveData.newIntance();
        try {
            ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<List<User>>() {
                @Override
                public List<User> doInBackground() throws Throwable {
                    return App.getAppDatabase().userDao().getAll();
                }
                @Override
                public void onSuccess(List<User> result) {
                    Log.d(TAG, "回调一次" + Thread.currentThread().getName());
                    baseResultLiveData.setValue(BaseResultUtil.createSuccessResult(result,"查询成功"));
                }

                @Override
                public void onFail(Throwable t) {
                    super.onFail(t);
                    baseResultLiveData.setValue(BaseResultUtil.createFailureResult(t.getMessage()));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseResultLiveData;
    }


}
