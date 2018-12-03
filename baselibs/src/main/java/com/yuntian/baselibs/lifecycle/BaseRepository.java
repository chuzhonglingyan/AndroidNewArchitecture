package com.yuntian.baselibs.lifecycle;

import com.yuntian.baselibs.net.RequestManager;
import com.yuntian.baselibs.rxjava.RxManager;

public abstract class BaseRepository<T> {


    protected T service;

    public BaseRepository(T service) {
        this.service = service;
        requestManager=new RequestManager();
        rxManager=new RxManager();
    }

    private RequestManager requestManager;
    private RxManager rxManager;


    public RequestManager getRequestManager() {
        return requestManager;
    }

    public RxManager getRxManager() {
        return rxManager;
    }


}
