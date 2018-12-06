package com.yuntian.baselibs.work;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

import java.util.UUID;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;

/**
 * @author chulingyan
 * @time 2018/12/05 23:43
 * @describe
 */
public class WorkManagerUtil {

    public static  <T extends Worker> OneTimeWorkRequest startOneTimeWorkRequest(Class<T> tClass,@NonNull Data inputData){
        OneTimeWorkRequest workRequest =
                new OneTimeWorkRequest.Builder(tClass).setInputData(inputData)
                        .build();
        WorkManager.getInstance().enqueue(workRequest);
        return workRequest;
    }

    public static void  observe(@NonNull LifecycleOwner owner,@NonNull UUID id, @NonNull Observer<WorkInfo> observer ){
        WorkManager.getInstance().getWorkInfoByIdLiveData(id)
                .observe(owner,observer);
    }


}
