package com.yuntian.baselibs.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class IViewUtil {


    public static <T extends ViewModel> T getViewModule(IView iView, Class<T> modelClass) {
        if (iView instanceof Fragment) {
            return ViewModelProviders.of((Fragment) iView).get(modelClass);
        } else if (iView instanceof FragmentActivity) {
            return ViewModelProviders.of((FragmentActivity) iView).get(modelClass);
        } else {
            return create(modelClass);
        }
    }


    @SuppressWarnings("ClassNewInstance")
    @NonNull
    public static <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection TryWithIdenticalCatches
        try {
            return modelClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        }
    }

}
