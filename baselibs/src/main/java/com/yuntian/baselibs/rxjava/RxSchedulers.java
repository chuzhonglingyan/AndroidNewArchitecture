package com.yuntian.baselibs.rxjava;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/1.
 */
public class RxSchedulers {

    public static <T> ObservableTransformer<T, T> io_main() {
        new ObservableTransformer<T,T>(){
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
        return null;
    }

    /**
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> ioMain() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                ;
    }


}