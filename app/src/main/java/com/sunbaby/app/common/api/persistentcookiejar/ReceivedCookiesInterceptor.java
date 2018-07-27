package com.sunbaby.app.common.api.persistentcookiejar;


import com.sunbaby.app.common.constains.Constains;
import com.sunbaby.app.common.utils.Preferences;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * author     王静波
 * date       2018/5/18
 * describe
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();
            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            Preferences.putStringSet(Constains.COOKIE, cookies);
        }
        return originalResponse;
    }

}

