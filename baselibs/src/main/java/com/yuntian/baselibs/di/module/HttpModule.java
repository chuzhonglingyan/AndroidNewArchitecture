package com.yuntian.baselibs.di.module;

import com.yuntian.baselibs.BuildConfig;
import com.yuntian.baselibs.net.cache.CacheStrategy;
import com.yuntian.baselibs.net.interceptor.CacheControlInterceptor;
import com.yuntian.baselibs.net.interceptor.HeaderInterceptor;
import com.yuntian.baselibs.util.HttpsUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpModule {

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

        CacheControlInterceptor cacheControlInterceptor = new CacheControlInterceptor();
        Interceptor logInterceptor;
        //处理网络请求的日志拦截输出
        if (BuildConfig.DEBUG) {
            //只显示基础信息
            logInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

        builder.connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(logInterceptor)
                .addNetworkInterceptor(cacheControlInterceptor)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .cache(CacheStrategy.getCache());

        return builder.build();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    //信任所有的域名服务器
    HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

}
