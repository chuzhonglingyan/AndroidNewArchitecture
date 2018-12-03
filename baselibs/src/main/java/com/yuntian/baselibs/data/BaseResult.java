package com.yuntian.baselibs.data;

import com.google.gson.annotations.SerializedName;

public class BaseResult<T> {

    public static final int SUCCEED_CODE = 99;
    public static final String SUCCEED_CODE_MSG = "成功";

    @SerializedName(value = "code", alternate = {"status"})
    private int code;

    @SerializedName(value = "msg", alternate = {"info", "errMsg"})
    private String msg;

    @SerializedName(value = "data", alternate = {"results", "result"})
    private T data;


    private boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }


    public boolean isSucess() {
        return code == SUCCEED_CODE || !error;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
