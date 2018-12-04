package com.yuntian.androidnewarchitecture.repository.db;

import android.annotation.SuppressLint;

import com.yuntian.androidnewarchitecture.db.dao.UserDao;
import com.yuntian.androidnewarchitecture.db.entity.User;
import com.yuntian.baselibs.data.BaseResult;
import com.yuntian.baselibs.lifecycle.BaseRepository;
import com.yuntian.baselibs.lifecycle.BaseResultLiveData;
import com.yuntian.baselibs.rxjava.DbCustomSubscriber;
import com.yuntian.baselibs.rxjava.RxHandleResult;

import java.util.List;

import io.reactivex.Flowable;

public class DbUserRepository extends BaseRepository<UserDao> implements IDbUserData {

    private static final String TAG = "DbUserRepository";

    public DbUserRepository(UserDao service) {
        super(service);
    }

    @SuppressLint("CheckResult")
    @Override
    public BaseResultLiveData<List<User>> getUserList() {
        BaseResultLiveData<List<User>> baseResultLiveData = BaseResultLiveData.newIntance();
        Flowable<List<User>> flowable = service.getAll().compose(RxHandleResult.handleDbFlowableResult());
        flowable.subscribe(new DbCustomSubscriber<List<User>>() {
            @Override
            public void onResponse(BaseResult<List<User>> baseResult) {
                baseResultLiveData.setValue(baseResult);
            }
        });
        return baseResultLiveData;
    }

    @Override
    public void insertAll(List<User> list) {
        service.insertAll(list);
    }

    @Override
    public void insertAll(User... users) {
        service.insertAll(users);
    }

}
