package com.yuntian.baselibs.exception;

public class ServerException extends Exception {

    private int code;
    private String  msg;


    public ServerException(String msg) {
        super(msg);
        this.msg = msg;
    }


    public ServerException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
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
}
