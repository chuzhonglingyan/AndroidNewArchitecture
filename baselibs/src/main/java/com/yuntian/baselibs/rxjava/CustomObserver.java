package com.yuntian.baselibs.rxjava;

import com.yuntian.baselibs.data.BaseResult;
import com.yuntian.baselibs.data.BaseResultUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/1.
 */
public abstract class CustomObserver<T> implements Observer<T> {


    private RxManager rxManager;


    public CustomObserver() {
        this(null);
    }


    public CustomObserver(RxManager rxManager) {
        this.rxManager = rxManager;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (rxManager != null) {
            rxManager.add(d);
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
        BaseResult<T> baseResult = BaseResultUtil.createFailureResult(code, msg);
        onResponse(baseResult);
    }


    public abstract void onResponse(BaseResult<T> baseResult);

}