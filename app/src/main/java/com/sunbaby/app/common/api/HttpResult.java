package com.sunbaby.app.common.api;

public class HttpResult<T> {

    public String status;
    public String msg;
    public T data;

    @Override
    public String toString() {
        return "HttpResult{" +
                "type='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", returnMap=" + data +
                '}';
    }
}
