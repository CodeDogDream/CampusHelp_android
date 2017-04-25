package com.dream.work.campushelp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/3/13.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public BaseActivity thisActivity;


    public abstract int getLayoutId();

    public abstract void event();

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        thisActivity = this;
        event();
    }
}
