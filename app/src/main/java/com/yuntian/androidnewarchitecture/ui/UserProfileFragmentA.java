package com.yuntian.androidnewarchitecture.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuntian.androidnewarchitecture.R;
import com.yuntian.androidnewarchitecture.di.component.DaggerUserComponent;
import com.yuntian.androidnewarchitecture.di.module.ApiServiceModule;
import com.yuntian.androidnewarchitecture.di.module.UserModule;
import com.yuntian.androidnewarchitecture.viewmodel.UserViewModel;
import com.yuntian.baselibs.base.BaseFragment;
import com.yuntian.baselibs.base.IView;
import com.yuntian.baselibs.di.component.AppComponent;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class UserProfileFragmentA extends BaseFragment implements IView {


    private static final String UID_KEY = "uid";

    private String userId = "";


    @Inject
    UserViewModel viewModel;

    public static final String TAG = "UserProfileFragmentA";


    private TextView tvData;

    public static UserProfileFragmentA newIntance(String userId) {
        UserProfileFragmentA fragment = new UserProfileFragmentA();
        Bundle bundle = new Bundle();
        bundle.putString(UID_KEY, userId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: " + context);
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
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");

        return inflater.inflate(R.layout.user_profile, container, false);
    }


    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvData = view.findViewById(R.id.tv_getData);

        Log.d(TAG, "onViewCreated: " + viewModel.toString());

        tvData.setOnClickListener(v -> {
            //        一旦用户数据更新，onChanged回调将被调用然后UI会被刷新。
            viewModel.getRepoList(userId).observe2(this, repoList -> {
                Log.d(TAG, new Gson().toJson(repoList));
            }, ((msg, code) -> {

                Log.d(TAG, "code:" + code + ",msg:" + msg);
            }));
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            userId = getArguments().getString(UID_KEY);
            viewModel.init("octocat");
        }

    }

}
