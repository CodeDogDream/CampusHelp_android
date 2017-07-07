package com.dream.work.campushelp.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.dream.work.campushelp.Interface.OnGetMyLocation;
import com.dream.work.campushelp.R;
import com.dream.work.campushelp.base.BaseFragment;
import com.dream.work.campushelp.helper.ToastHelper;
import com.dream.work.campushelp.network.HelpApiManager;
import com.dream.work.campushelp.network.HttpData;
import com.dream.work.campushelp.network.MySubscriber;
import com.dream.work.campushelp.network.bean.DataBean;
import com.dream.work.campushelp.utils.RuntimeInfo;

/**
 * Created by Dream on 2017/5/15.
 */

public class AddHelpInfoFragment extends BaseFragment {
    private EditText title;
    private EditText content;
    private EditText tag;

    @Override
    public void bindView(View mRootView) {
        title = (EditText) mRootView.findViewById(R.id.my_title);
        content = (EditText) mRootView.findViewById(R.id.content);
        tag = (EditText) mRootView.findViewById(R.id.tag);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_add_help;
    }

    public String getTitle() {
        return title.getText().toString().trim();
    }

    public String getContent() {
        return content.getText().toString().trim();
    }

    public String getTagText() {
        String text = tag.getText().toString().trim();
//        if (TextUtils.isEmpty(text)) {
//            return "default";
//        }
        return text;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        thisActivity.setTitle(R.string.send_help_info);
        TextView text = (TextView) thisActivity.getSendText();
        text.setVisibility(View.VISIBLE);
        text.setText(R.string.publish);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestForPublishHelpInfo(String.valueOf(System.currentTimeMillis() / 1000L));
            }
        });
    }

    public void sendRequestForPublishHelpInfo(String time) {
        MySubscriber<DataBean> ss = new MySubscriber<DataBean>() {
            @Override
            public void onNext(DataBean dataBean) {
                super.onNext(dataBean);
                if(dataBean.getCode() == 0){
                    thisActivity.finish();
                }
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
            }
        };
        HelpApiManager.getInstance().sendRequest(ss, HttpData.PUBLISH_HELP, RuntimeInfo.getInstance().uid, RuntimeInfo.getInstance().uname, getTitle(), getContent(), time, RuntimeInfo.getInstance().longitude, RuntimeInfo.getInstance().latitude, getTagText());
    }
}
