package com.yuntian.androidnewarchitecture.ui.activity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.yuntian.androidnewarchitecture.R;
import com.yuntian.androidnewarchitecture.db.entity.User;
import com.yuntian.androidnewarchitecture.di.component.DaggerDbUserComponent;
import com.yuntian.androidnewarchitecture.di.module.DbUserModule;
import com.yuntian.androidnewarchitecture.viewmodel.DbViewModel;
import com.yuntian.baselibs.work.MathWorker;
import com.yuntian.baselibs.base.BaseActivity;
import com.yuntian.baselibs.di.component.AppComponent;
import com.yuntian.baselibs.util.GsonUtil;
import com.yuntian.baselibs.work.WorkManagerUtil;

import java.util.Random;
import java.util.UUID;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.work.Data;
import androidx.work.WorkRequest;

public class DbTestActivity extends BaseActivity {


    @Inject
    DbViewModel dbViewModel;

    private TextView tvAdd;
    private TextView tvDelete;
    private TextView tvUpdate;
    private TextView tvQuery;
    private TextView testRestore;
    private TextView tvQueryResult;

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


    /**
     * android8.0调用在onStop之后，之前在onStp之前，onPause前后不确定 默认保存有id的焦点视图
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState); //保存少量数据
        LogUtils.d(dbViewModel.toString());//activity恢复重建,但是viewModel仍然保持在内存
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtils.d(dbViewModel.toString()); //activity恢复重建,但是viewModel仍然保持在内存
    }

    @Override
    protected void initView() {
        tvAdd=findViewById(R.id.tv_add);
        tvDelete=findViewById(R.id.tv_delete);
        tvUpdate=findViewById(R.id.tv_update);
        tvQuery=findViewById(R.id.tv_query);
        testRestore=findViewById(R.id.test_restore);
        tvQueryResult=findViewById(R.id.tv_query_result);
    }

    @Override
    protected void initListener() {
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
            testRestore.setTextColor(Color.BLUE);
            Data myData = new Data.Builder()
                    .putLong(MathWorker.KEY_X_ARG, 42)
                    .putLong(MathWorker.KEY_Y_ARG, 421)
                    .putLong(MathWorker.KEY_Z_ARG, 5666)
                    .build();
            WorkRequest compressWorkerRequest= WorkManagerUtil.startOneTimeWorkRequest(MathWorker.class,myData);

            WorkManagerUtil.observe(this,compressWorkerRequest.getId(), workInfo -> {
                // Do something with the status
                if (workInfo != null && workInfo.getState().isFinished()) {
                    // ...
                    LogUtils.d( "workInfo:" + workInfo.getOutputData().getInt(MathWorker.KEY_RESULT,0));
                }
            });
        });
    }

    @Override
    protected void initData(boolean isInit,@Nullable Bundle savedInstanceState) {

        if (isInit){
            dbViewModel.initData();//只要初始化数据的地方
        }
        testRestore.setText("数据");
        dbViewModel.getShowList().observe2(this, result -> {
            tvQueryResult.setText(GsonUtil.toJson(result));
            LogUtils.d(GsonUtil.toJson(result));
        }, ((msg, code) -> {
            LogUtils.d( "code:" + code + ",msg:" + msg);
        }));

        LogUtils.d(this.toString());
        LogUtils.d(dbViewModel.toString());


    }



}
