package com.yuntian.baselibs.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuntian.baselibs.di.component.AppComponent;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

public abstract class BaseFragment extends Fragment implements LifecycleOwner,IView {

    protected AppCompatActivity activity;
    protected Context context;
    protected View parentView;


    protected AppComponent getApplicationComponent(AppCompatActivity context) {
        return ((BaseApp) context.getApplication()).component();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView=inflater.inflate(getLayoutId(), container, false);
        return parentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListener();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState==null,savedInstanceState);
    }

    protected abstract int getLayoutId();


    protected  <T extends View> T findViewById(@IdRes int id){
        return  parentView.findViewById(id);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (AppCompatActivity) context;
        this.context = context;
        inject(getApplicationComponent(activity));
    }

    public abstract void inject(AppComponent appComponent);


    protected abstract void  initView();

    protected abstract void initListener();

    protected abstract void initData(boolean isInit,@Nullable Bundle savedInstanceState);



}
