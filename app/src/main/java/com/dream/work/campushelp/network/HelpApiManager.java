package com.dream.work.campushelp.network;

import android.util.Log;

import com.dream.work.campushelp.network.Interceptor.ResponseInterceptor;
import com.dream.work.campushelp.network.bean.DataBean;
import com.dream.work.campushelp.network.netconvert.CampusConverterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by Administrator on 2017/3/14.
 */

public class HelpApiManager {

    private static HelpApiManager Instant;
    private HelpApi helpApi;

    private HelpApiManager() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .addInterceptor(new ResponseInterceptor())
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpData.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(CampusConverterFactory.create())
                .client(mClient)
                .build();
        helpApi = retrofit.create(HelpApi.class);
    }

    public static synchronized HelpApiManager getInstance() {
        if (Instant == null) {
            Instant = new HelpApiManager();
        }
        return Instant;
    }

    public void sendRequest(MySubscriber<DataBean> ss, String url, String... parameter) {
        Flowable<DataBean> flowable = null;
        String[] params = parameter;
        switch (url) {
            case HttpData.GET_CAPTCHA:
                flowable = helpApi.getCaptcha(parameter[0]);
                break;
            case HttpData.USER_LOGIN:
                flowable = helpApi.userLogin(parameter[0], parameter[1]);
                break;
            case HttpData.GET_USER_INFO:
                flowable = helpApi.getUserInfo(parameter[0]);
                break;
            case HttpData.UPDATE_USER_INFO:
                flowable = helpApi.updateUserInfo(parameter[0], parameter[1], parameter[2]);
                break;
            case HttpData.PUBLISH_HELP:
                flowable = helpApi.publishHelp(parameter[0], parameter[1], parameter[2], parameter[3], parameter[4], parameter[5], parameter[6], parameter[7]);
                break;
            case HttpData.CHANGE_HELP_INFO:
                flowable = helpApi.changeHelpInfo(parameter[0], parameter[1], parameter[2], parameter[3]);
                break;
            case HttpData.GET_ALL_HELP_INFO:
                flowable = helpApi.getAllHelpInfo();
                break;
            case HttpData.GET_TAG_HELP_INFO:
                flowable = helpApi.getHelpInfoByTag(parameter[0]);
                break;
            case HttpData.GET_ID_HELP_INFO:
                flowable = helpApi.getHelpInfoById(parameter[0]);
                break;
            case HttpData.UPDATE_USER_LOCATION:
                flowable = helpApi.updateLocation(parameter[0], parameter[1], parameter[2]);
                break;
            case HttpData.UPDATE_AVATAR:
                flowable = helpApi.updateAvatar(parameter[0], parameter[1]);
                break;
            case HttpData.GET_NEARBY_HELP_INFO:
                flowable = helpApi.getNearByHelpInfo(parameter[0], parameter[1], parameter[2]);
                break;
            default:
                flowable = Flowable.just(new DataBean());
                break;
        }
        flowable
                .map(new Function<DataBean, DataBean>() {
                    @Override
                    public DataBean apply(@NonNull DataBean dataBean) throws Exception {
                        Log.v("tag", dataBean.toString());
                        return dataBean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ss);
    }

    public interface HelpApi {

        @POST("/login/getCaptcha/{mobile}")
        Flowable<DataBean> getCaptcha(@Path("mobile") String mobile);

        @POST("/login/userLogin/{mobile}/{captcha}")
        Flowable<DataBean> userLogin(@Path("mobile") String mobile, @Path("captcha") String captcha);

        @POST("/user/getUserInfo/{uid}")
        Flowable<DataBean> getUserInfo(@Path("uid") String uid);

        @POST("/user/updateLocation/{uid}/{longitude}/{latitude}}")
        Flowable<DataBean> updateLocation(@Path("uid") String uid, @Path("longitude") String longitude, @Path("latitude") String latitude);

        @POST("/user/updateUserInfo/{uid}/{dataName}/{data}")
        Flowable<DataBean> updateUserInfo(@Path("uid") String uid, @Path("dataName") String dataName, @Path("data") String data);

        @FormUrlEncoded
        @POST("/user/updateAvatar/{uid}")
        Flowable<DataBean> updateAvatar(@Path("uid") String uid, @Field("base64") String base64);

        @POST("/help/publishHelp/{uid}/{uname}/{title}/{content}/{date}/{longitude}/{latitude}/{tag}")
        Flowable<DataBean> publishHelp(
                @Path("uid") String uid,
                @Path("uname") String uName,
                @Path("title") String title,
                @Path("content") String content,
                @Path("date") String date,
                @Path("longitude") String longitude,
                @Path("latitude") String latitude,
                @Path("tag") String tag
        );

        @POST("help/changeHelpInfo/{info_id}/{uid}/{dataName}/{data}")
        Flowable<DataBean> changeHelpInfo(@Path("info_id") String infoId,
                                          @Path("uid") String uid,
                                          @Path("dataName") String dataName,
                                          @Path("data") String data);

        @POST("help/getAllHelpInfo")
        Flowable<DataBean> getAllHelpInfo();


        @POST("help/getTagHelpInfo/{tag}")
        Flowable<DataBean> getHelpInfoByTag(@Path("tag") String tag);

        @POST("help/getIdHelpInfo/{id}")
        Flowable<DataBean> getHelpInfoById(@Path("id") String id);

        @POST("help/getNearByHelpInfo/{id}/{longitude}/{latitude}")
        Flowable<DataBean> getNearByHelpInfo(@Path("id") String id, @Path("longitude") String longitude, @Path("latitude") String latitude);
    }
}
