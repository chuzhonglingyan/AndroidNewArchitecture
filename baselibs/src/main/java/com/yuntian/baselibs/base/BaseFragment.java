package com.yuntian.baselibs.base;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuntian.baselibs.di.component.AppComponent;

public abstract class BaseFragment extends Fragment implements LifecycleOwner {

    protected Activity activity;
    protected Context context;
    protected View parentView;


    protected AppComponent getApplicationComponent(Activity context) {
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
    }

    protected abstract int getLayoutId();


    protected  <T extends View> T findViewById(@IdRes int id){
        return  parentView.findViewById(id);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
        this.context = context;
        inject(getApplicationComponent(activity));
    }

    public abstract void inject(AppComponent appComponent);



}
