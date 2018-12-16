package com.yuntian.androidnewarchitecture.ui.activity;

import android.content.Intent;
import android.os.Bundle;


import com.blankj.utilcode.util.LogUtils;
import com.yuntian.androidnewarchitecture.R;
import com.yuntian.androidnewarchitecture.ui.fragment.UserProfileFragmentA;
import com.yuntian.androidnewarchitecture.ui.fragment.UserProfileFragmentB;
import com.yuntian.baselibs.base.BaseActivity;
import com.yuntian.baselibs.di.component.AppComponent;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends BaseActivity {

    private UserProfileFragmentA userProfileFragmentA;
    private UserProfileFragmentB userProfileFragmentB;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void inject(AppComponent appComponent) {

    }

    @Override
    protected void initView() {
        findViewById(R.id.tv_delete).setOnClickListener(v->{

            LogUtils.d("替换userProfileFragmentB");
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fl_b,UserProfileFragmentA.newIntance("octocat"));
            fragmentTransaction.commit();
        });
        findViewById(R.id.tv_goto_db).setOnClickListener(v->{
            startActivity(new Intent(context,ListActivity.class));
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(boolean isInit,@Nullable Bundle savedInstanceState) {
        initFrament();
    }


    public void  initFrament(){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        userProfileFragmentA = (UserProfileFragmentA) getSupportFragmentManager().findFragmentByTag(UserProfileFragmentA.TAG);
        if (userProfileFragmentA ==null){
            userProfileFragmentA = UserProfileFragmentA.newIntance("octocat");
        }
        userProfileFragmentB = (UserProfileFragmentB) getSupportFragmentManager().findFragmentByTag(UserProfileFragmentA.TAG);
        if (userProfileFragmentB ==null){
            userProfileFragmentB = UserProfileFragmentB.newIntance("octocat");
        }

        fragmentTransaction.replace(R.id.fl_a, userProfileFragmentA);
        fragmentTransaction.replace(R.id.fl_b, userProfileFragmentB);

        fragmentTransaction.commit();
    }
}
