package com.yuntian.baselibs.net;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class RequestManager {


    private List<Call> calls = new ArrayList<>();


    public void add(Call call) {
        if (call != null) {
            calls.add(call);
        }
    }

    public void cancelCalls() {
        LogUtils.d("cancelCalls");
        for (Call call : calls) {
            if (call!=null){
                call.cancel();
            }
        }
    }

}
