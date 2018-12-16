package com.yuntian.androidnewarchitecture.repository;

import com.yuntian.androidnewarchitecture.bean.GankInfo;
import com.yuntian.androidnewarchitecture.db.entity.User;
import com.yuntian.androidnewarchitecture.net.service.GankService;
import com.yuntian.androidnewarchitecture.contract.GankContract;
import com.yuntian.androidnewarchitecture.ui.page.NetPageKeyedDataSource;
import com.yuntian.baselibs.data.BaseResult;
import com.yuntian.baselibs.lifecycle.BaseRepository;
import com.yuntian.baselibs.lifecycle.BaseResultLiveData;
import com.yuntian.baselibs.rxjava.CustomSubscriber;
import com.yuntian.baselibs.rxjava.DbCustomSubscriber;
import com.yuntian.baselibs.rxjava.RxHandleResult;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.paging.RxPagedListBuilder;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

public class GankRepository extends BaseRepository<GankService> implements GankContract {


    private final static int PAGE_SIZE = 10;

    private static final String TAG = "GankRepository";

    private PagedList.Config config;

    public GankRepository(GankService service) {
        super(service);
        config = new PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .build();
    }


    @Override
    public BaseResultLiveData<List<GankInfo>> getGankInfoList(String dataType, int page) {
        final BaseResultLiveData<List<GankInfo>> data = BaseResultLiveData.newIntance();
        Flowable<List<GankInfo>> flowable = service.getGankInfoList(dataType, page).compose(RxHandleResult.handleFlowableResult());
        flowable.subscribe(new CustomSubscriber<List<GankInfo>>(getRxManager()) {
            @Override
            public void onResponse(BaseResult<List<GankInfo>> baseResult) {
                data.setValue(baseResult);
            }
        });
        return data;
    }

    public Flowable<List<GankInfo>> getGankInfoPageList(String dataType, int pageSize, int page) {
        return service.getGankInfoPageList(dataType, pageSize, page).compose(RxHandleResult.handleFlowableResult());
    }


    public LiveData<PagedList<GankInfo>> getGankInfoPageList(String dataType) {
        return new LivePagedListBuilder<>(new DataSource.Factory<Integer, GankInfo>() {
            @NonNull
            @Override
            public DataSource<Integer, GankInfo> create() {
                return new NetPageKeyedDataSource(dataType,GankRepository.this);
            }
        }, config).build();
    }

}
