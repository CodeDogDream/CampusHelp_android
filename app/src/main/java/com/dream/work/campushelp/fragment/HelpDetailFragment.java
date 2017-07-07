package com.dream.work.campushelp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.work.campushelp.R;
import com.dream.work.campushelp.base.BaseFragment;
import com.dream.work.campushelp.entity.HelpInfo;
import com.dream.work.campushelp.network.HelpApiManager;
import com.dream.work.campushelp.network.HttpData;
import com.dream.work.campushelp.network.MySubscriber;
import com.dream.work.campushelp.network.bean.DataBean;
import com.dream.work.campushelp.utils.ParseJsonUtils;

/**
 * Created by Dream on 2017/6/4.
 */

public class HelpDetailFragment extends BaseFragment {
    private ImageView avatar;
    private TextView name;
    private TextView info;
    private TextView title;
    private TextView content;

    @Override
    public void bindView(View mRootView) {
        avatar = (ImageView) mRootView.findViewById(R.id.avatar);
        name = (TextView) mRootView.findViewById(R.id.user_name);
        info = (TextView) mRootView.findViewById(R.id.detail_info);
        title = (TextView) mRootView.findViewById(R.id.my_title);
        content = (TextView) mRootView.findViewById(R.id.content);
    }

    @Override
    public int getLayoutId() {
        return R.layout.frgament_help_detail;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String info_id = getArguments().getString("info_id");
        thisActivity.setTitle(R.string.help_detail);
        sendRequestForHelpDetail(info_id);
    }

    private void sendRequestForHelpDetail(String info_id) {
        MySubscriber<DataBean> ss = new MySubscriber<DataBean>() {
            @Override
            public void onNext(DataBean dataBean) {
                super.onNext(dataBean);
                HelpInfo helpInfo = ParseJsonUtils.getData(dataBean.getData(), HelpInfo.class);
                Glide.with(thisActivity).load(helpInfo.getUavatar()).into(avatar);
                name.setText(helpInfo.getUname());
                info.setText(String.valueOf(helpInfo.getDate()));
                title.setText(helpInfo.getTitle());
                content.setText(helpInfo.getContent());
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
            }
        };
        HelpApiManager.getInstance().sendRequest(ss, HttpData.GET_HELP_INFO_BY_INFO_ID, info_id);
    }
}
