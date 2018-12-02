package com.yuntian.androidnewarchitecture.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.yuntian.androidnewarchitecture.base.App;
import com.yuntian.androidnewarchitecture.bean.GitHubUser;
import com.yuntian.androidnewarchitecture.db.AppDatabase;
import com.yuntian.androidnewarchitecture.db.entity.User;
import com.yuntian.baselibs.data.BaseResult;
import com.yuntian.baselibs.data.BaseResultUtil;
import com.yuntian.baselibs.lifecycle.BaseRepository;
import com.yuntian.baselibs.lifecycle.BaseResultLiveData;
import com.yuntian.androidnewarchitecture.bean.Repo;
import com.yuntian.baselibs.net.NetCallback;
import com.yuntian.androidnewarchitecture.net.service.Webservice;
import com.yuntian.baselibs.util.GsonUtil;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository extends BaseRepository implements IUserData {

    private static final String TAG = "UserRepository";

    private Webservice webservice;




    @Inject
    public UserRepository(Webservice webservice) {
        this.webservice = webservice;
    }

    // ...
    public LiveData<GitHubUser> getUser(String userId) {
        // This is not an optimal implementation, we'll fix it below
        final MutableLiveData<GitHubUser> data = new MutableLiveData<>();
        webservice.getUser(userId).enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(@NonNull Call<GitHubUser> call, @NonNull Response<GitHubUser> response) {
                // error case is left out for brevity
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<GitHubUser> call, @NonNull Throwable t) {

            }
        });
        return data;
    }

    private BaseResultLiveData<List<Repo>> baseResultLiveData;



    @Override
    public BaseResultLiveData<List<Repo>> getRepoList(String userId) {
        if (baseResultLiveData == null) {
            baseResultLiveData = BaseResultLiveData.newIntance();
        }
        Call<List<Repo>> call = webservice.listRepos(userId);
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
