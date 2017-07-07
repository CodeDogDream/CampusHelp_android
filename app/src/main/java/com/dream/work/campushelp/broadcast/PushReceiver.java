package com.dream.work.campushelp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Dream on 2017/6/8.
 */

public class PushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String date=intent.toString();
        Log.v("tag","sss");
    }
}
