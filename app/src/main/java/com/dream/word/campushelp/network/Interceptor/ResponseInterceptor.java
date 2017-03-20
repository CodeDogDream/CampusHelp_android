package com.dream.word.campushelp.network.Interceptor;

import android.text.TextUtils;
import android.util.Log;

import com.dream.word.campushelp.CampusApplication;
import com.dream.word.campushelp.helper.SharePreferenceHelper;
import com.dream.word.campushelp.utils.MyLog;

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
            token = SharePreferenceHelper.getSharePreferenceFromString(new CampusApplication().getApplicationContext(), "token", "");
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
