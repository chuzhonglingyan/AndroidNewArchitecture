package com.yuntian.baselibs.lifecycle;

import com.yuntian.baselibs.net.RequestManager;

public abstract class BaseRepository {


    private RequestManager requestManager;

    public BaseRepository() {
        requestManager=new RequestManager();
    }

    public RequestManager getRequestManager() {
        return requestManager;
    }

}
