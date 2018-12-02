package com.yuntian.baselibs.data;

public class BaseResultUtil{

    public  static <T>  BaseResult<T> createSuccessResult(T data,String msg){
        return  createResult(data,BaseResult.SUCCEED_CODE,msg);
    }

    public  static <T>  BaseResult<T> createFailureResult(String msg){
        return  createResult(null,0,msg);
    }

    public  static <T>  BaseResult<T> createResult(T data, int code, String msg){
        BaseResult<T> baseResult=new BaseResult<T>();
        baseResult.setCode(code);
        baseResult.setMsg(msg);
        baseResult.setData(data);
        return  baseResult;
    }

}
