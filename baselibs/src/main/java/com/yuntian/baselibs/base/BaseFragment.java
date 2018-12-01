package com.yuntian.baselibs.base;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.yuntian.baselibs.di.component.AppComponent;

public abstract class BaseFragment extends Fragment implements LifecycleOwner {

    protected Activity activity;
    protected Context context;


    protected AppComponent getApplicationComponent(Activity context) {
        return ((BaseApp) context.getApplication()).component();
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
