package com.sunbaby.app.common.api;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
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
        Request request = chain.request();
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        String action = "";
        HttpUrl httpUrl = request.url();
        List<String> paths = httpUrl.pathSegments();
        if (paths != null && paths.size() > 0) {
            action = paths.get(paths.size() - 1);
        }
        Log.e("RequestClient", String.format("%s: %s", action, responseBody.string()));
        return response;
    }

}
