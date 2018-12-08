package com.yuntian.baselibs.rxjava;

import com.blankj.utilcode.util.LogUtils;
import com.yuntian.baselibs.data.BaseResult;
import com.yuntian.baselibs.data.BaseResultUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.HttpException;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/1.
 */

public abstract class CustomSubscriber<T> extends DisposableSubscriber<T> {

    private RxManager rxManager;

    public CustomSubscriber() {
        this(null);
    }
    public CustomSubscriber(RxManager rxManager) {
        this.rxManager=rxManager;
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (rxManager!=null){
            rxManager.add(this);
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(T t) {
        BaseResult<T> baseResult = BaseResultUtil.createSuccessResult(t, BaseResult.SUCCEED_CODE_MSG);
        onResponse(baseResult);
    }

    @Override
    public void onError(Throwable e) {
        String msg;
        int code;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            msg = httpException.getMessage();
            code = httpException.code();
        } else {
            msg = "服务繁忙，请稍后重试";
            code = 500;
        }
        LogUtils.d(e.getMessage());
        BaseResult<T> baseResult = BaseResultUtil.createFailureResult(code, msg);
        onResponse(baseResult);
    }


    public abstract void onResponse(BaseResult<T> baseResult);

}