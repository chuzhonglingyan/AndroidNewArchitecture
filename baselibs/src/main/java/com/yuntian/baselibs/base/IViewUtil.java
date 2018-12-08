package com.yuntian.baselibs.base;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

public class IViewUtil {


    public static <T extends ViewModel> T getViewModule(IView iView, Class<T> modelClass) {
        if (iView instanceof Fragment) {
            return ViewModelProviders.of((Fragment) iView).get(modelClass);
        } else if (iView instanceof AppCompatActivity) {
            return ViewModelProviders.of((AppCompatActivity) iView).get(modelClass);
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
