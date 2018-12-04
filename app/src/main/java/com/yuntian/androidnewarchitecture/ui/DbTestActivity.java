package com.yuntian.androidnewarchitecture.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.yuntian.androidnewarchitecture.R;
import com.yuntian.androidnewarchitecture.db.entity.User;
import com.yuntian.androidnewarchitecture.di.component.DaggerDbUserComponent;
import com.yuntian.androidnewarchitecture.di.component.DaggerUserComponent;
import com.yuntian.androidnewarchitecture.di.component.DbUserComponent;
import com.yuntian.androidnewarchitecture.di.module.DbUserModule;
import com.yuntian.androidnewarchitecture.di.module.UserModule;
import com.yuntian.androidnewarchitecture.viewmodel.DbViewModel;
import com.yuntian.baselibs.base.BaseActivity;
import com.yuntian.baselibs.di.component.AppComponent;
import com.yuntian.baselibs.util.GsonUtil;

import java.util.Random;
import java.util.UUID;

import javax.inject.Inject;

public class DbTestActivity extends BaseActivity {


    @Inject
    DbViewModel dbViewModel;

    private TextView tvAdd;
    private TextView tvDelete;
    private TextView tvUpdate;
    private TextView tvQuery;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_db_test;
    }

    @Override
    public void inject(AppComponent appComponent) {
        DaggerDbUserComponent.builder()
                .dbUserModule(new DbUserModule(this))
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        tvAdd=findViewById(R.id.tv_add);
        tvDelete=findViewById(R.id.tv_delete);
        tvUpdate=findViewById(R.id.tv_update);
        tvQuery=findViewById(R.id.tv_query);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

//        [10,40)不包括300就是 rand.nextInt(40-10)+10
        Random random=new Random();
        tvAdd.setOnClickListener(v->{
            User user=new User();
            user.setUserName("用户"+ UUID.randomUUID().toString().substring(0,4));
            user.setAge(random.nextInt(40-10)+10);
            dbViewModel.insertAll(user);
        });

        tvDelete.setOnClickListener(v->{

        });

        tvUpdate.setOnClickListener(v->{

        });

        tvQuery.setOnClickListener(v->{
            dbViewModel.getUserList().observe2(this, result -> {
                LogUtils.d(GsonUtil.toJson(result));
            }, ((msg, code) -> {
                LogUtils.d( "code:" + code + ",msg:" + msg);
            }));
        });
    }


}
