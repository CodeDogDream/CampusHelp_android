package com.dream.work.campushelp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dream.work.campushelp.R;
import com.dream.work.campushelp.base.BaseFragment;

/**
 * Created by Dream on 2017/5/8.
 */

public class MyHelpInfoFragment extends BaseFragment {
    @Override
    public void bindView(View mRootView) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_help_info;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        thisActivity.setTitle(R.string.my_help);
    }
}
