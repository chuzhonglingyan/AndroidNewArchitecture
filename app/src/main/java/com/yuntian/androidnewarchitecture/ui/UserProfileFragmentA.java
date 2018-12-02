package com.yuntian.androidnewarchitecture.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuntian.androidnewarchitecture.R;
import com.yuntian.androidnewarchitecture.di.component.DaggerUserComponent;
import com.yuntian.androidnewarchitecture.di.module.UserModule;
import com.yuntian.androidnewarchitecture.viewmodel.CommunicateViewModel;
import com.yuntian.androidnewarchitecture.viewmodel.UserViewModel;
import com.yuntian.baselibs.base.BaseFragment;
import com.yuntian.baselibs.base.IView;
import com.yuntian.baselibs.di.component.AppComponent;
import com.yuntian.baselibs.util.GsonUtil;

import javax.inject.Inject;

public class UserProfileFragmentA extends BaseFragment {

    public static final String TAG = "UserProfileFragmentA";

    private static final String UID_KEY = "uid";
    private String userId = "";

    private TextView tvData;

    private CommunicateViewModel mCommunicateViewModel;
    @Inject
    UserViewModel viewModel;



    public static UserProfileFragmentA newIntance(String userId) {
        UserProfileFragmentA fragment = new UserProfileFragmentA();
        Bundle bundle = new Bundle();
        bundle.putString(UID_KEY, userId);
        fragment.setArguments(bundle);
        return fragment;
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
        tvData = findViewById(R.id.tv_getData);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tvData.setOnClickListener(v -> {
            mCommunicateViewModel.setName("Jane");
            //        一旦用户数据更新，onChanged回调将被调用然后UI会被刷新。
            viewModel.getRepoList(userId).observe2(this, repoList -> {
                Log.d(TAG, GsonUtil.toJson(repoList));
            }, ((msg, code) -> {

                Log.d(TAG, "code:" + code + ",msg:" + msg);
            }));
        });
        mCommunicateViewModel = ViewModelProviders.of((FragmentActivity) activity).get(CommunicateViewModel.class);

        if (getArguments() != null) {
            userId = getArguments().getString(UID_KEY);
            viewModel.init(userId);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.user_profile;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + viewModel.toString());
    }
}
