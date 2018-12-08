package com.yuntian.baselibs.lifecycle;

import com.yuntian.baselibs.rxjava.RxManager;

public abstract class BaseRepository<T> {


    protected T service;

    public BaseRepository(T service) {
        this.service = service;
        rxManager=new RxManager();
    }

    private RxManager rxManager;


    public RxManager getRxManager() {
        return rxManager;
    }

    public void  onCleared(){
        if (rxManager!=null){
            rxManager.clear();
        }
    }

}
