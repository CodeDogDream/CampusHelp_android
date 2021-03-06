package com.dream.work.campushelp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.work.campushelp.activity.MainActivity;

/**
 * Created by Administrator on 2017/3/14.
 */

public abstract class BaseFragment extends Fragment {
    public MainActivity thisActivity;
    public Fragment thisFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        thisFragment = this;
        thisActivity = (MainActivity) thisFragment.getActivity();
        View view = inflater.inflate(getLayoutId(), container, false);
        bindView(view);
        return view;
    }

    /****
     * view的绑定
     * @param mRootView
     */
    public abstract void bindView(View mRootView);

    /***
     * 获取布局
     * @return
     */
    public abstract int getLayoutId();


}
