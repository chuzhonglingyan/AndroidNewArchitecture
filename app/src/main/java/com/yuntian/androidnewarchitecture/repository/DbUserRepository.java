package com.yuntian.androidnewarchitecture.repository;

import android.annotation.SuppressLint;

import com.yuntian.androidnewarchitecture.contract.DbUserContract;
import com.yuntian.androidnewarchitecture.db.dao.UserDao;
import com.yuntian.androidnewarchitecture.db.entity.User;
import com.yuntian.baselibs.data.BaseResult;
import com.yuntian.baselibs.lifecycle.BaseRepository;
import com.yuntian.baselibs.lifecycle.BaseResultLiveData;
import com.yuntian.baselibs.rxjava.DbCustomSubscriber;
import com.yuntian.baselibs.rxjava.RxHandleResult;
import com.yuntian.baselibs.rxjava.RxSchedulers;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Arrays;
import java.util.List;

import androidx.paging.PagedList;
import androidx.paging.RxPagedListBuilder;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;

public class DbUserRepository extends BaseRepository<UserDao> implements DbUserContract {

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
    public BaseResultLiveData<PagedList<User>> getUserPageList(int pageSize) {
        BaseResultLiveData<PagedList<User>> baseResultLiveData = BaseResultLiveData.newIntance();
        PagedList.Config config=new PagedList.Config.Builder().setPageSize(pageSize).setInitialLoadSizeHint(pageSize).build();
        Flowable<PagedList<User>> flowable = new RxPagedListBuilder<>(service.getUserPageList(), config)
                .buildFlowable(BackpressureStrategy.LATEST).compose(RxHandleResult.handleDbFlowableResultTest());
        flowable.subscribe(new DbCustomSubscriber<PagedList<User>>() {
            @Override
            public void onResponse(BaseResult<PagedList<User>> baseResult) {
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
        Flowable.create((FlowableOnSubscribe<Void>) emitter -> insertAll(Arrays.asList(users)),
                BackpressureStrategy.LATEST).compose(RxSchedulers.ioMain()).subscribe(new Subscriber<Void>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(Void aVoid) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


}
