package com.sunbaby.app.common.api;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 作者：liuyuanqi on 17/7/18 15:29
 * 邮箱：liuyuanqi@eims.com.cn
 */
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();
        //完整的请求
        String action = "";
        Response response = chain.proceed(request);
        //个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        //Log.d("RequestClient", String.format("接收响应: [%s] %n返回json:【%s】 %.1fms %n%s", response.request().url(), responseBody.string(), (t2 - t1) / 1e6d, response.headers()));
        String temp=request.url().toString();
        action=temp.substring(temp.lastIndexOf("/")+1);
        Log.d("RequestClient", String.format("%s: %s", action, responseBody.string()));
        return response;
    }

}
