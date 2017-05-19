package com.dream.work.campushelp.helper;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.dream.work.campushelp.CampusApplication;

/**
 * Created by Administrator on 2017/3/26.
 */

/***
 *
 */
public class ToastHelper {

    public static void showToast(Context context, @StringRes int resId, int duration) {
        Toast.makeText(context, resId, duration).show();
    }

    public static void showToast(@StringRes int resId, int duration) {
        Toast.makeText(CampusApplication.getInstance().getApplicationContext(), resId, duration).show();
    }

    public static void showToast(String text) {
        Toast.makeText(CampusApplication.getInstance().getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(@StringRes int resId) {
        Toast.makeText(CampusApplication.getInstance().getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }

}
