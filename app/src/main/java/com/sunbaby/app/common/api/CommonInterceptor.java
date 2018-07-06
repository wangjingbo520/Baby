package com.sunbaby.app.common.api;

import android.util.Log;


import com.sunbaby.app.common.sign.SecretConstains;
import com.sunbaby.app.common.sign.SignCore;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * 拦截器－添加公用字段
 * 作者：liuyuanqi on 17/7/18 10:11
 * 邮箱：liuyuanqi@eims.com.cn
 */
public class CommonInterceptor implements Interceptor {

    private SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
    /**
     * 接口参数
     * @param request
     * @return
     */
    private Map<String, String> getParams(Request request){
        Map<String, String> map = new HashMap<>();
        if (request.body() instanceof FormBody) {
            FormBody body = (FormBody) request.body();
            for (int i = 0; i < body.size(); i++) {
                map.put(body.encodedName(i), body.encodedValue(i));
            }
        }
        return map;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        // 添加新的参数
        Request.Builder requestBuilder = request.newBuilder();
        String method = request.method();
        String qTime=format.format(System.currentTimeMillis());
        Map<String, String> params=getParams(request);
      //  params.put("deviceId", DeviceUtils.getDeviceId(MyApplication.context));
        params.put("deviceId", "77777777777");
        params.put("qTime",qTime);
        params.put("appkey", SecretConstains.APP_KEY);
        String sign= SignCore.getSignString(params);
        if ("POST".equals(method)) {
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            formBodyBuilder.add("deviceId", "77777777777");//此处添加deviceId参数
            formBodyBuilder.add("appkey", SecretConstains.APP_KEY);//此处添加appkey参数
            formBodyBuilder.add("qTime", qTime);//此处添加qTime参数
            formBodyBuilder.add("sign", sign);//此处添加sign参数
            RequestBody formBody = formBodyBuilder.build();
            String postBodyString = bodyToString(request.body());
            postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
            requestBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString));
            String fullUrl = request.url()+"?"+postBodyString;
            Log.d("RequestClient", "发送请求 "+fullUrl);
        }
        request=requestBuilder.build();
        return chain.proceed(request);
    }
    private String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null){
                copy.writeTo(buffer);
                return buffer.readUtf8();
            } else{
                return "";
            }
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
