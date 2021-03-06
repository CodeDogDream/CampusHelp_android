package com.dream.work.campushelp;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.baidu.mapapi.SDKInitializer;
import com.dream.work.campushelp.utils.RuntimeInfo;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/3/15.
 */

public class CampusApplication extends MultiDexApplication {
    private static CampusApplication campusApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        campusApplication = this;
        SDKInitializer.initialize(getApplicationContext());
        JPushInterface.init(getApplicationContext());
        JPushInterface.setDebugMode(false);
        RuntimeInfo.getInstance().updateInfo();
    }

    public static CampusApplication getInstance() {
        return campusApplication;
    }
}
