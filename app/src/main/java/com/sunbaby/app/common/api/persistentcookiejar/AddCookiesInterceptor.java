package com.sunbaby.app.common.api.persistentcookiejar;

import android.util.Log;


import com.sunbaby.app.common.constains.Constains;
import com.sunbaby.app.common.utils.Preferences;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author     王静波
 * date       2018/5/18
 * describe
 */
public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet) Preferences.getStringSet(Constains.COOKIE);
        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
            Log.v("OkHttp", "Adding Header: " + cookie);
        }

        return chain.proceed(builder.build());
    }
}
