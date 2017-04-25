package com.dream.work.campushelp.network.Interceptor;

import com.dream.work.campushelp.CampusApplication;
import com.dream.work.campushelp.helper.SharePreferenceHelper;
import com.dream.work.campushelp.utils.MyLog;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/3/14.
 */

public class ResponseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        String token = "";
        try {
            token = SharePreferenceHelper.getSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "token", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Request request = original.newBuilder()
                .addHeader("accept-type", "campus")
                .addHeader("token", token)
                .method(original.method(), original.body())
                .build();
        MyLog.v(request.toString());
        return chain.proceed(request);
    }
}
