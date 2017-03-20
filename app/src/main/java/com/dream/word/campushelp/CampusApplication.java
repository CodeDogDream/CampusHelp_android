package com.dream.word.campushelp;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by Administrator on 2017/3/15.
 */

public class CampusApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
