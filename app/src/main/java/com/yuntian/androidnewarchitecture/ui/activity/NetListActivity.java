package com.yuntian.androidnewarchitecture.ui.activity;

import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.yuntian.androidnewarchitecture.R;
import com.yuntian.androidnewarchitecture.bean.GankInfo;
import com.yuntian.androidnewarchitecture.di.component.DaggerDbListComponent;
import com.yuntian.androidnewarchitecture.di.component.DaggerUserComponent;
import com.yuntian.androidnewarchitecture.di.module.DbUserModule;
import com.yuntian.androidnewarchitecture.di.module.UserModule;
import com.yuntian.androidnewarchitecture.ui.adapter.GankAdapter;
import com.yuntian.androidnewarchitecture.ui.adapter.UserAdapter;
import com.yuntian.androidnewarchitecture.viewmodel.DbViewModel;
import com.yuntian.androidnewarchitecture.viewmodel.GankViewModel;
import com.yuntian.baselibs.base.BaseActivity;
import com.yuntian.baselibs.di.component.AppComponent;
import com.yuntian.baselibs.util.GsonUtil;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class NetListActivity extends BaseActivity {

    @Inject
    GankViewModel gankViewModel;

    private SwipeRefreshLayout mSwRefresh;
    private RecyclerView mRv;
    private GankAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    public void inject(AppComponent appComponent) {
        DaggerUserComponent.builder()
                .userModule(new UserModule(this))
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        mRv = findViewById(R.id.rv);
        mSwRefresh = findViewById(R.id.sw_refresh);
        mSwRefresh.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwRefresh.setProgressViewOffset(true, 0, 100);//设置加载圈是否有缩放
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mSwRefresh.setRefreshing(true);
        mAdapter = new GankAdapter();
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        //下拉刷新
        mSwRefresh.setOnRefreshListener(this::refreshData);
        mAdapter.setOnItemClickListener((view, user, position) -> {
            ToastUtils.showShort("点击了" + position + "," + user.get_id());
        });
    }

    @Override
    protected void initData(boolean isInit, @Nullable Bundle savedInstanceState) {
        refreshData();

    }

    public void refreshData() {
        gankViewModel.getGankInfoPageList("福利").observe(this, gankInfos -> {
            LogUtils.d(GsonUtil.toJson(gankInfos));
            mAdapter.submitList(gankInfos);
            mSwRefresh.setRefreshing(false);
        });
    }

}
