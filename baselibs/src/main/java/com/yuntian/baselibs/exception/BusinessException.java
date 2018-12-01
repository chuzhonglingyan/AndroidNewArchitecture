package com.yuntian.baselibs.exception;

public class BusinessException extends Exception {

    private int code;
    private String  msg;


    public BusinessException( int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
