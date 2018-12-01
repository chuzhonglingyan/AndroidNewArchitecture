package com.yuntian.androidnewarchitecture.net;

import android.support.annotation.NonNull;

import com.yuntian.androidnewarchitecture.base.data.BaseResult;
import com.yuntian.androidnewarchitecture.base.exception.BusinessException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public abstract class NetCallback<T> implements Callback<T> {


    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (call.isCanceled()) {
            onFailure(call, new BusinessException(0, "请求被取消了"));
            return;
        }
        BaseResult<T> baseResult = new BaseResult<>();
        //可以处理业务异常
        baseResult.setData(response.body());
        baseResult.setCode(BaseResult.SUCCEED_CODE);
        baseResult.setMsg(response.message());
        onResponse(baseResult);
    }


    @Override
    public void onFailure(@NonNull Call call, @NonNull Throwable t) {
        String msg;
        int code;
        if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            msg = httpException.getMessage();
            code = httpException.code();
        } else {
            msg = "服务繁忙，请稍后重试";
            code = 500;
        }
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(code);
        baseResult.setMsg(msg);

        onResponse(baseResult);
    }

    public abstract void onResponse(BaseResult<T> baseResult);


}
