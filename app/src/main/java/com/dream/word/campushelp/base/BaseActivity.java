package com.dream.word.campushelp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/3/13.
 */

public class BaseActivity extends AppCompatActivity {
    public Activity thisActivity;


    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisActivity=this;
    }
}
