package com.yuntian.baselibs.rxjava;

import com.yuntian.baselibs.data.BaseResult;
import com.yuntian.baselibs.exception.ServerException;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * @author guangleilei
 * @explain 对服务器返回数据成功和失败处理
 * @time 2017/2/22 13:43.
 */
public class RxHandleResult {


    /**
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> handleDbFlowableResult() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .to((Function<Flowable<T>, Publisher<T>>) baseResultFlowable -> {
                    return baseResultFlowable.flatMap((Function<T, Publisher<T>>) RxHandleResult::createFlowableData);
                });
    }


    /**
     * 对服务器返回数据进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<BaseResult<T>, T> handleFlowableResult() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .to((Function<Flowable<BaseResult<T>>, Publisher<T>>) baseResultFlowable -> {
                    return baseResultFlowable.flatMap((Function<BaseResult<T>, Publisher<T>>) result -> {
                        if (result.isSucess()) {
                            return createFlowableData(result.getData());
                        } else {
                            return Flowable.error(new ServerException(result.getCode(), result.getMsg()));
                        }
                    });
                });
    }

    /**
     * 接受数据完成
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Flowable<T> createFlowableData(final T data) {
        return Flowable.create(emitter -> {
            try {
                emitter.onNext(data);
                emitter.onComplete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, BackpressureStrategy.BUFFER);
    }


    /**
     * 对服务器返回数据进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseResult<T>, T> handleResult() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .to((Function<Observable<BaseResult<T>>, ObservableSource<T>>) baseResultObservable -> {
                    return baseResultObservable.flatMap((Function<BaseResult<T>, ObservableSource<T>>) result -> {
                        if (result.isSucess()) {
                            return createData(result.getData());
                        } else {
                            return Observable.error(new ServerException(result.getCode(), result.getMsg()));
                        }
                    });
                });
    }

    /**
     * 对服务器返回数据进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseResult<T>, T> handleResult1() {
        return new ObservableTransformer<BaseResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResult<T>> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .to(new Function<Observable<BaseResult<T>>, ObservableSource<T>>() {
                            @Override
                            public ObservableSource<T> apply(Observable<BaseResult<T>> BaseResultObservable) throws Exception {
                                return BaseResultObservable.flatMap(new Function<BaseResult<T>, ObservableSource<T>>() {
                                    @Override
                                    public ObservableSource<T> apply(BaseResult<T> result) throws Exception {
                                        if (result.isSucess()) {
                                            return createData(result.getData());
                                        } else {
                                            return Observable.error(new ServerException(result.getCode(), result.getMsg()));
                                        }
                                    }
                                });
                            }
                        });
            }
        };
    }


    /**
     * 对服务器返回数据进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseResult<T>, T> handleResultWork() {
        return new ObservableTransformer<BaseResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResult<T>> upstream) {
                return upstream.to(new Function<Observable<BaseResult<T>>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(Observable<BaseResult<T>> BaseResultObservable) throws Exception {
                        return BaseResultObservable.flatMap(new Function<BaseResult<T>, ObservableSource<T>>() {
                            @Override
                            public ObservableSource<T> apply(BaseResult<T> result) throws Exception {
                                if (result.isSucess()) {
                                    return createData(result.getData());
                                } else {
                                    return Observable.error(new ServerException(result.getCode(), result.getMsg()));
                                }
                            }
                        });
                    }
                });
            }
        };
    }


    /**
     * 接受数据完成
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> ObservableSource<T> createData(final T data) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(data);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }


}