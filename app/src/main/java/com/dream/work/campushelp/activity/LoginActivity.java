package com.dream.work.campushelp.activity;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.work.campushelp.R;
import com.dream.work.campushelp.base.BaseActivity;
import com.dream.work.campushelp.entity.UserLogin;
import com.dream.work.campushelp.helper.SharePreferenceHelper;
import com.dream.work.campushelp.helper.ToastHelper;
import com.dream.work.campushelp.network.HelpApiManager;
import com.dream.work.campushelp.network.HttpData;
import com.dream.work.campushelp.network.MySubscriber;
import com.dream.work.campushelp.network.bean.DataBean;
import com.dream.work.campushelp.utils.IntentUtils;
import com.dream.work.campushelp.utils.ParseJsonUtils;
import com.dream.work.campushelp.utils.RuntimeInfo;

/**
 * Created by Dream on 2017/3/21.
 */

public class LoginActivity extends BaseActivity {
    private LinearLayout mobile_layout;
    private LinearLayout captcha_layout;
    private TextView login_text;
    private TextView send_captcha;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void event() {
        mobile_layout = (LinearLayout) findViewById(R.id.activity_login_first);
        captcha_layout = (LinearLayout) findViewById(R.id.activity_login_second);
        login_text = (TextView) findViewById(R.id.activity_login_button);
        send_captcha = (TextView) captcha_layout.getChildAt(2);

        send_captcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(getPhoneNumber())) {
                    ToastHelper.showToast(thisActivity, R.string.mobile_null, Toast.LENGTH_SHORT);
                    return;
                }
                sendRequestForCaptcha(getPhoneNumber());
            }
        });
        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(getPhoneNumber())) {
                    ToastHelper.showToast(thisActivity, R.string.mobile_null, Toast.LENGTH_SHORT);
                    return;
                }
                if (TextUtils.isEmpty(getCaptcha())) {
                    ToastHelper.showToast(thisActivity, R.string.captcha_null, Toast.LENGTH_SHORT);
                    return;
                }
                sendRequestForUserLogin(getPhoneNumber(), getCaptcha());
            }
        });
    }

    private String getPhoneNumber() {
        return ((EditText) mobile_layout.getChildAt(1)).getText().toString().trim();
    }

    private String getCaptcha() {
        return ((EditText) captcha_layout.getChildAt(1)).getText().toString().trim();
    }

    private void sendRequestForCaptcha(String mobile) {
        MySubscriber<DataBean> ss = new MySubscriber<DataBean>() {
            @Override
            public void onNext(DataBean dataBean) {
                super.onNext(dataBean);
                ToastHelper.showToast(R.string.sent_captcha);
                countDownTimer.start();
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
            }
        };
        HelpApiManager.getInstance().sendRequest(ss, HttpData.LOGIN_GET_CAPTCHA, mobile);
    }

    private void sendRequestForUserLogin(String mobile, String captcha) {
        MySubscriber<DataBean> ss = new MySubscriber<DataBean>() {
            @Override
            public void onNext(DataBean dataBean) {
                super.onNext(dataBean);
                UserLogin userLogin = ParseJsonUtils.getData(dataBean.getData(), UserLogin.class);
                if (dataBean.getCode() == 0) {
                    RuntimeInfo.getInstance().saveUserLogin(userLogin);
                    ToastHelper.showToast(R.string.login_success);
                    IntentUtils.Builder().activity(thisActivity).target(MapActivity.class).build();
                }
                Log.v("tag", userLogin.toString());
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
            }
        };
        HelpApiManager.getInstance().sendRequest(ss, HttpData.LOGIN_USER_LOGIN, mobile, captcha);
    }

    private CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            send_captcha.setText(String.format("%s秒", String.valueOf(millisUntilFinished / 1000)));
            send_captcha.setEnabled(false);
        }

        @Override
        public void onFinish() {
            send_captcha.setText(getText(R.string.get_telephone_captcha));
            send_captcha.setEnabled(true);
        }
    };
}
