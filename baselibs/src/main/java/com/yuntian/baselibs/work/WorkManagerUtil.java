package com.yuntian.baselibs.work;


import java.util.UUID;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;

/**
 * @author chulingyan
 * @time 2018/12/05 23:43
 * @describe
 * @see {https://blog.csdn.net/guiying712/article/details/80386338}
 */
public class WorkManagerUtil {

    /**
     * 一次性任务
     * @param tClass
     * @param inputData
     * @param <T>
     * @return
     */
    public static <T extends Worker> WorkRequest startOneTimeWorkRequest(Class<T> tClass, @NonNull Data inputData) {
        OneTimeWorkRequest workRequest =
                new OneTimeWorkRequest.Builder(tClass).setInputData(inputData)
                        .build();
        WorkManager.getInstance().enqueue(workRequest);
        return workRequest;
    }


    /**
     * 周期性任务
     * @param tClass
     * @param inputData
     * @param repeatInterval
     * @param repeatIntervalTimeUnit
     * @param <T>
     * @return
     */
    public static <T extends Worker> WorkRequest startPeriodicWorkRequest(Class<T> tClass, @NonNull Data inputData, long repeatInterval,
                                                                           @NonNull TimeUnit repeatIntervalTimeUnit) {
        PeriodicWorkRequest.Builder workBuilder = new PeriodicWorkRequest.Builder(tClass, repeatInterval, repeatIntervalTimeUnit)
                .setInputData(inputData);
        // ...if you want, you can apply constraints to the builder here...
        // Create the actual work object:
        PeriodicWorkRequest workRequest = workBuilder.build(); // Then enqueue the recurring task:
        WorkManager.getInstance().enqueue(workRequest);
        return workRequest;
    }


    public static void observe(@NonNull LifecycleOwner owner, @NonNull UUID id, @NonNull Observer<WorkInfo> observer) {
        WorkManager.getInstance().getWorkInfoByIdLiveData(id)
                .observe(owner, observer);
    }



}
