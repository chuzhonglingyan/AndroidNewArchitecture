package com.yuntian.baselibs.base;

import android.content.Context;
import android.os.Bundle;

import com.yuntian.baselibs.di.component.AppComponent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

public abstract class BaseActivity extends AppCompatActivity implements LifecycleOwner, IView {

    protected Context context;


    protected AppComponent getApplicationComponent(AppCompatActivity context) {
        return ((BaseApp) context.getApplication()).component();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        this.context = this;
        inject(getApplicationComponent(this));
        initView();
        initListener();
        initData(savedInstanceState==null,savedInstanceState);
    }


    protected abstract int getLayoutId();


    public abstract void inject(AppComponent appComponent);


    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData(boolean isInit,@Nullable Bundle savedInstanceState);




}
