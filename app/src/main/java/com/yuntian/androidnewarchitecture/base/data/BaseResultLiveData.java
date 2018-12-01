package com.yuntian.androidnewarchitecture.base.data;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

public  class BaseResultLiveData<T> extends MutableLiveData<BaseResult<T>> {


    public static <T>  BaseResultLiveData<T>  newIntance(){
        return  new BaseResultLiveData<>();
    }



    public void observe2(@NonNull LifecycleOwner owner,SucessCallBack<T> sucessCallBack ){
        observe2(owner,sucessCallBack,null);
    }


    public void observe2(@NonNull LifecycleOwner owner,SucessCallBack<T> sucessCallBack,FauileCallBack fauileCallBack ){
            observe(owner, tBaseResult-> {
                if (tBaseResult==null){
                    return;
                }
                if (tBaseResult.getCode()==BaseResult.SUCCEED_CODE&&sucessCallBack!=null){
                    sucessCallBack.onSucess(tBaseResult.getData());
                }else {
                    if (fauileCallBack!=null){
                        fauileCallBack.onFauile(tBaseResult.getMsg(),tBaseResult.getCode());
                    }
                }
            });
    }



    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<BaseResult<T>> observer) {
        super.observe(owner, observer);
    }


    public  interface SucessCallBack<T>{
        void  onSucess(T data);
    }

    public  interface FauileCallBack{
        void  onFauile(String msg,int code);
    }

}
