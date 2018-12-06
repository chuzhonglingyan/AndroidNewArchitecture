package com.yuntian.baselibs.work;

import android.content.Context;
import android.support.annotation.NonNull;


import com.blankj.utilcode.util.LogUtils;

import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * @author chulingyan
 * @time 2018/12/05 23:41
 * @describe
 */
public class CompressWorker extends Worker {

    // Define the parameter keys:
    public static final String KEY_X_ARG = "X";
    public static final String KEY_Y_ARG = "Y";
    public static final  String KEY_Z_ARG = "Z";

    // The result key:
    public static final String KEY_RESULT = "result";



    public CompressWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Worker.Result doWork() {

        // Fetch the arguments (and specify default values):
        long x = getInputData().getLong(KEY_X_ARG, 0);
        long y =getInputData().getLong(KEY_Y_ARG, 0);
        long z = getInputData().getLong(KEY_Z_ARG, 0);
        LogUtils.d(Thread.currentThread().getName());
        long timeToSleep = x  + y + z;
        try {
            Thread.sleep(timeToSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //...set the output, and we're done!
        Data output = new Data.Builder()
                .putInt(KEY_RESULT, (int) timeToSleep)
                .build();

        setOutputData(output);

        // Indicate success or failure with your return value.
        return Worker.Result.SUCCESS;
    }

}

