package com.dream.work.campushelp.utils;

import android.util.Log;

/**
 * Created by Administrator on 2017/3/14.
 */

public class MyLog {
    public static void v(String tag, String msg) {
        Log.v(tag, msg);
    }

    public static void v(String msg) {
        Log.v("CampusHelp", msg);
    }

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void d(String msg) {
        Log.d("CampusHelp", msg);
    }

    public static void w(String tag, String msg) {
        Log.w(tag, msg);
    }

    public static void w(String msg) {
        Log.w("CampusHelp", msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void e(String msg) {
        Log.e("CampusHelp", msg);
    }

    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void i(String msg) {
        Log.i("CampusHelp", msg);
    }
}
