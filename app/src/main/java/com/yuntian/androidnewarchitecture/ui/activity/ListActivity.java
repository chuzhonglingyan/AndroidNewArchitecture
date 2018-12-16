package com.yuntian.androidnewarchitecture.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.yuntian.androidnewarchitecture.R;
import com.yuntian.androidnewarchitecture.db.entity.User;
import com.yuntian.androidnewarchitecture.di.component.DaggerDbListComponent;
import com.yuntian.androidnewarchitecture.di.module.DbUserModule;
import com.yuntian.androidnewarchitecture.ui.adapter.OnItemClickListener;
import com.yuntian.androidnewarchitecture.ui.adapter.UserAdapter;
import com.yuntian.androidnewarchitecture.viewmodel.DbViewModel;
import com.yuntian.baselibs.base.BaseActivity;
import com.yuntian.baselibs.di.component.AppComponent;
import com.yuntian.baselibs.util.GsonUtil;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class ListActivity extends BaseActivity {

    @Inject
    DbViewModel dbViewModel;

    private SwipeRefreshLayout mSwRefresh;
    private RecyclerView mRv;
    private UserAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    public void inject(AppComponent appComponent) {
        DaggerDbListComponent.builder()
                .dbUserModule(new DbUserModule(this))
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        mRv =  findViewById(R.id.rv);
        mSwRefresh = findViewById(R.id.sw_refresh);
        mSwRefresh.setColorSchemeResources(android.R.color.holo_blue_light,android.R.color.holo_red_light,android.R.color.holo_orange_light,android.R.color.holo_green_light);
        mSwRefresh.setProgressViewOffset(true, 0, 100);//设置加载圈是否有缩放
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mSwRefresh.setRefreshing(true);
        mAdapter = new UserAdapter();
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        //下拉刷新
        mSwRefresh.setOnRefreshListener(this::refreshData);
        mAdapter.setOnItemClickListener((view, user, position) -> {
            ToastUtils.showShort("点击了"+position+","+user.getId());
        });
    }

    @Override
    protected void initData(boolean isInit, @Nullable Bundle savedInstanceState) {

        refreshData();
    }

    public void  refreshData(){
        dbViewModel.getUserPageList(10).observe2(this, result -> {
            LogUtils.d(result.size());
            LogUtils.d(GsonUtil.toJson(result));

            mAdapter.submitList(result);
            mSwRefresh.setRefreshing(false);
        }, ((msg, code) -> {
            LogUtils.d( "code:" + code + ",msg:" + msg);
        }));
    }

}
