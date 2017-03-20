package com.dream.word.campushelp.network;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by Administrator on 2017/3/14.
 */

public class MySubscriber<T> implements Subscriber<T> {

    @Override
    public void onSubscribe(Subscription s) {
        Log.v("Subscription", "Subscription");
    }

    @Override
    public void onNext(T t) {
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
