package com.yuntian.baselibs.net.interceptor;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.yuntian.baselibs.net.cache.CacheStrategy.*;

/**
 * description 缓存拦截器.
 * Created by ChuYingYan on 2018/5/1.
 */
public class CacheControlInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        //请求
        Request request = chain.request();
        String cacheControl = request.cacheControl().toString();
        if (NetworkUtils.isConnected()) {
            request = request.newBuilder()
                    .cacheControl(request.cacheControl())
                    .build();
            LogUtils.d("has netWork");
        } else {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            LogUtils.d("not netWork");
        }

        //响应
        Response originalResponse = chain.proceed(request);
        if (NetworkUtils.isConnected()) {
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public," + CACHE_CONTROL_CACHE)
                    .removeHeader("Pragma")
                    .build();
        }
    }


}
