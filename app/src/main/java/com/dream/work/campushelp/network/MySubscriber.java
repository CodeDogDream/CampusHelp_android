package com.dream.work.campushelp.network;

import android.util.Log;

import com.dream.work.campushelp.helper.ToastHelper;
import com.dream.work.campushelp.network.bean.DataBean;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by Administrator on 2017/3/14.
 */

public abstract class MySubscriber<T> implements Subscriber<T> {

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
        Log.v("Subscription", "Subscription");
    }


    @Override
    public void onNext(T t) {
        DataBean bean = (DataBean) t;
        if (bean.getCode() != 0) {
            ToastHelper.showToast(bean.getMsg());
        }
        Log.v("onNext", "onNext");
    }

    @Override
    public void onError(Throwable t) {
        Log.v("onError", "onError");
    }

    @Override
    public void onComplete() {
        Log.v("onComplete", "onComplete");
    }
}
