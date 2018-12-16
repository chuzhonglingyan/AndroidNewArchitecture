package com.yuntian.androidnewarchitecture.ui.page;

import com.blankj.utilcode.util.LogUtils;
import com.yuntian.androidnewarchitecture.bean.GankInfo;
import com.yuntian.androidnewarchitecture.repository.GankRepository;
import com.yuntian.baselibs.data.BaseResult;
import com.yuntian.baselibs.rxjava.CustomSubscriber;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import io.reactivex.Flowable;

/**
 * @author chulingyan
 * @time 2018/12/16 18:24
 * @describe
 */
public class NetPageKeyedDataSource extends PageKeyedDataSource<Integer, GankInfo> {

    private GankRepository repository;

    private String dataType;

    public NetPageKeyedDataSource(String dataType, GankRepository repository) {
        this.repository = repository;
        this.dataType = dataType;
    }

    /**
     * 初始加载数据
     *
     * @param params
     * @param callback
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, GankInfo> callback) {
        LogUtils.d("size : ${params.requestedLoadSize} ");
        Flowable<List<GankInfo>> flowable = repository.getGankInfoPageList(dataType, params.requestedLoadSize, 1);
        flowable.subscribe(new CustomSubscriber<List<GankInfo>>() {
            @Override
            public void onResponse(BaseResult<List<GankInfo>> baseResult) {
                if (!baseResult.isSucess()) {
                    return;
                }
                if (baseResult.getData() == null || baseResult.getData().size() <= 0) {
                    return;
                }
                callback.onResult(baseResult.getData(), null, 2);
            }
        });
    }

    /**
     * 向前分页加载数据
     *
     * @param params
     * @param callback
     */
    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, GankInfo> callback) {
        LogUtils.d("size : ${params.requestedLoadSize}  page:${params.key}");
        Flowable<List<GankInfo>> flowable = repository.getGankInfoPageList(dataType, params.requestedLoadSize, params.key);
        flowable.subscribe(new CustomSubscriber<List<GankInfo>>() {
            @Override
            public void onResponse(BaseResult<List<GankInfo>> baseResult) {
                if (!baseResult.isSucess()) {
                    return;
                }
                if (baseResult.getData() == null || baseResult.getData().size() <= 0) {
                    return;
                }
                callback.onResult(baseResult.getData(), params.key - 1);
            }
        });
    }

    /**
     * 向后分页加载数据
     *
     * @param params
     * @param callback
     */
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, GankInfo> callback) {
        LogUtils.d("size : ${params.requestedLoadSize}  page:${params.key}");
        Flowable<List<GankInfo>> flowable = repository.getGankInfoPageList("福利", params.requestedLoadSize, params.key);
        flowable.subscribe(new CustomSubscriber<List<GankInfo>>() {
            @Override
            public void onResponse(BaseResult<List<GankInfo>> baseResult) {
                if (!baseResult.isSucess()) {
                    return;
                }
                if (baseResult.getData() == null || baseResult.getData().size() <= 0) {
                    return;
                }
                callback.onResult(baseResult.getData(), params.key + 1);
            }
        });
    }


}
