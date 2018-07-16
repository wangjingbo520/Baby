package com.sunbaby.app.common.api;

public class HttpResult<T> {

    public int code;
    public String msg;
    public T data;

    @Override
    public String toString() {
        return "HttpResult{" +
                "type='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", returnMap=" + data +
                '}';
    }
}
