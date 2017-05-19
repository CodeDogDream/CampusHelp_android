package com.dream.work.campushelp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


/**
 * Created by Dream on 2017/4/6.
 */

public class IntentUtils {
    private static IntentUtils build;


    public static IntentUtils Builder() {
        if (build == null) {
            build = new IntentUtils();
        }
        return build;
    }

    private Activity activity;
    private Context context;
    private Class classOf;
    private Bundle bundle;
    //默认请求码
    private int requestCode = 2233;

    private IntentUtils IntentUtils;

    public IntentUtils activity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public IntentUtils context(Context context) {
        this.context = context;
        return this;
    }


    public IntentUtils target(Class<? extends Activity> classOf) {
        this.classOf = classOf;
        return this;
    }

    public IntentUtils Bundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

    public IntentUtils requestCode(int requestCode) {
        this.requestCode = requestCode;
        return this;
    }

    public void build() {
        Intent intent = new Intent(context == null ? activity : context, classOf);
        intent.putExtra("bundle", bundle);
        if (activity == null) {
            context.startActivity(intent);
        } else {
            activity.startActivityForResult(intent, requestCode);
        }
    }
}
