package com.dream.work.campushelp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dream.work.campushelp.R;
import com.dream.work.campushelp.base.BaseFragment;
import com.dream.work.campushelp.entity.HelpInfo;
import com.dream.work.campushelp.network.HelpApiManager;
import com.dream.work.campushelp.network.HttpData;
import com.dream.work.campushelp.network.MySubscriber;
import com.dream.work.campushelp.network.bean.DataBean;
import com.dream.work.campushelp.utils.ParseJsonUtils;
import com.dream.work.campushelp.utils.RuntimeInfo;
import com.dream.work.campushelp.view.adapter.HelpItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dream on 2017/5/8.
 */

public class NearByFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private HelpItemAdapter adapter;

    @Override
    public void bindView(View mRootView) {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        adapter = new HelpItemAdapter(new ArrayList<HelpInfo>(), thisActivity);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_help_info;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        thisActivity.setTitle(R.string.nearby_help);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(thisActivity));
        mRecyclerView.setAdapter(adapter);
        sendRequestForNearByHelp();
    }

    private void sendRequestForNearByHelp() {
        MySubscriber<DataBean> ss = new MySubscriber<DataBean>() {
            @Override
            public void onNext(DataBean dataBean) {
                super.onNext(dataBean);
                List<HelpInfo> helpInfos = ParseJsonUtils.getDataFromList(dataBean.getData(), HelpInfo.class);
                adapter.setData(helpInfos);
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
            }
        };
        HelpApiManager.getInstance().sendRequest(ss, HttpData.GET_NEARBY_HELP_INFO, RuntimeInfo.getInstance().uid, RuntimeInfo.getInstance().longitude, RuntimeInfo.getInstance().latitude);
    }
}
