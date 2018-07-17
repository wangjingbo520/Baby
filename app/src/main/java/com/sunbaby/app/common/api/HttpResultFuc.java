package com.sunbaby.app.common.api;


import com.sunbaby.app.common.utils.LogUtil;

import rx.functions.Func1;


public class HttpResultFuc<T> implements Func1<HttpResult<T>, T> {
    @Override
    public T call(HttpResult<T> httpResult) {
        if (1 != httpResult.code) {
            LogUtil.e("---->" + httpResult.data);
            //非正常返回结构处理
            throw new ApiException(httpResult.code + "", httpResult.msg);
        }
        return httpResult.data;
    }
}

